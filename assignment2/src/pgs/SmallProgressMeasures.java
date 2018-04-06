package pgs;

import pgs.models.*;
import pgs.strategies.LiftingStrategy;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SmallProgressMeasures {
    private ParityGame parityGame;
    private LiftingStrategy liftingStrategy;
    private int d;
    private int[] priorityCount;
    private int[] maxComponents;
    private Measure maxMeasure;
    private Map<Vertex, Measure> varrho;
    private Map<Vertex, Boolean> lifted;
    private int liftedCount;
    private MeasureComparator[] measureComparators;

    public SmallProgressMeasures() {

    }

    public ParityGameResult calculate(ParityGame parityGame, LiftingStrategy liftingStrategy) {
        this.parityGame = parityGame;
        this.liftingStrategy = liftingStrategy;
        this.d = 1 + this.parityGame.getMaxPriority();

        this.priorityCount = new int[this.d];
        for (int i = 0; i < this.priorityCount.length; i++) {
            this.priorityCount[i] = this.parityGame.getPriorities()
                .getOrDefault(i, new HashSet<>(0,1 ))
                .size();
        }
        // TODO: must prioritycount[0] == 0?

        this.maxComponents = new int[this.d];
        for (int i = 0; i < this.maxComponents.length; i++) {
            if(i % 2 != 0) {
                this.maxComponents[i] = this.priorityCount[i];
            }
        }
        this.maxMeasure = new Measure(this.maxComponents);

        this.measureComparators = new MeasureComparator[this.d];
        for (int i = 0; i < this.measureComparators.length; i++) {
            this.measureComparators[i] = new MeasureComparator(i);
        }

        this.lifted = this.parityGame.getVertices()
            .values()
            .stream()
            .collect(Collectors.toMap(Function.identity(), k -> false));
        this.liftedCount = 0;


        // Algorithm (26/49)
        this.varrho = this.parityGame.getVertices()
            .values()
            .stream()
            .collect(Collectors.toMap(Function.identity(), v -> new Measure(new int[this.d])));
        while (this.isLiftable()) {
            // If this raises a NoSuchElementException, then there is a problem with the strategy.
            Vertex v = this.liftingStrategy.next();
            Measure vMeasure = this.varrho.get(v);
            Measure vLiftedMeasure = this.lift(v);

            // TODO: is this correct way if maintaining?
            if (vMeasure.lt(vLiftedMeasure)) {
                Boolean oldValue = this.lifted.put(v, false);
                // TODO: the following or this?
                this.resetLifted();
                //if (oldValue != null && oldValue) {
                //    this.liftedCount--;
                //}
            } else {
                Boolean oldValue = this.lifted.put(v, true);
                if (oldValue != null && !oldValue) {
                    this.liftedCount++;
                }
            }
            this.varrho.put(v, vLiftedMeasure);
        }

        return this.buildResult();
    }

    private void resetLifted() {
        this.lifted.forEach((k, v) -> this.lifted.put(k, false));
        this.liftedCount = 0;
    }

    private boolean isLiftable() {
        return this.liftedCount < this.parityGame.getVertices().size();
    }

    private Measure lift(Vertex v) {
        // XXX: Lift depends on Prog depends on varrho(w)
        // Lift (25/49)
        Measure measure;
        switch (v.getPlayer()) {
            case EVEN:
                measure = this.liftEven(v);
                break;
            case ODD:
                measure = this.liftOdd(v);
                break;
            default:
                assert false;
                throw new IllegalStateException("Unknown player");
        }
        assert measure != null;

        return measure;
    }

    private Measure liftEven(Vertex v) {
        Measure minimized = Measure.top();
        for (Vertex w: this.parityGame.getSuccessors().get(v)) {
            Measure current = this.prog(v, w);
            if (current.lt(minimized)) {
                minimized = current;
            }
        }

        return minimized;
//        return this.parityGame.getSuccessors()
//            .get(v)
//            .stream()
//            .map(w -> this.prog(v, w))
//            .reduce(
//                Measure.top(),
//                BinaryOperator.minBy(this.measureComparators[this.d]));
    }

    private Measure liftOdd(Vertex v) {
        Measure maximized = new Measure(new int[this.d]);
        for (Vertex w: this.parityGame.getSuccessors().get(v)) {
            Measure current = this.prog(v, w);
            if (current.gt(maximized)) {
                maximized = current;
            }
        }

        return maximized;
//        return this.parityGame.getSuccessors()
//            .get(v)
//            .stream()
//            .map(w -> this.prog(v, w))
//            .reduce(
//                new Measure(new int[this.d]),
//                BinaryOperator.maxBy(this.measureComparators[this.d]));

    }

    private Measure prog(Vertex v, Vertex w) {
        // Prog (21/49)
        int vPriority = v.getPriority();
        // TODO: assert vPriority is not 0?
        Measure wMeasure = this.varrho.get(w);
        if (wMeasure == null) {
            throw new IllegalStateException("Measure for vertex not found");
        }

        Measure measure;
        if (vPriority % 2 == 0) {
            measure = ProgEven(vPriority, wMeasure);
        } else {
            measure = ProgOdd(vPriority, wMeasure);
        }

        return measure;
    }

    private Measure ProgEven(int vPriority, Measure wMeasure) {
        // XXX: Repeated calls for same input will return the same result
        int[] components = this.computeComponents(vPriority, wMeasure);
        Measure measure = new Measure(components);
        // TODO: no further checking?

        return measure;
    }

    private Measure ProgOdd(int vPriority, Measure wMeasure) {
        // XXX: Repeated calls for same input will return increased result (till top)
        if (wMeasure.isTop()) {
            // TODO: correct fast return?
            return Measure.top();
        }

        int[] components = this.computeComponents(vPriority, wMeasure);
        // TODO: what if vPriority==0?
        boolean incremented = false;
        for (int i = vPriority; i >= 0; i--) {
            if (this.maxComponents[i] > components[i]) {
                components[i]++;
                incremented = true;
                break;
            } else {
                components[i] = 0;
            }
        }
        if(!incremented) {
            // TODO: did we find top?
            return Measure.top();
        }

        Measure measure = new Measure(components);
        // TODO: no further checking?

        return measure;
    }

    private int[] computeComponents(int vPriority, Measure wMeasure) {
        int[] components = new int[this.d];
        int[] wComponents = wMeasure.isTop() ? this.maxComponents : wMeasure.getComponents();
        for (int i = 0; i <= vPriority; i++) {
            components[i] = wComponents[i];
        }
        // TODO: trivial for loop
        for (int i = vPriority + 1; i < components.length; i++) {
            components[i] = 0;
        }

        return components;
    }

    private ParityGameResult buildResult() {
        // TODO: check if odd should get top.
        Set<Vertex> even = this.varrho.entrySet()
            .stream()
            .filter(entry -> !entry.getValue().isTop())
            .map(Map.Entry::getKey)
            .collect(Collectors.toSet());
        Set<Vertex> odd = this.varrho.entrySet()
            .stream()
            .filter(entry -> entry.getValue().isTop())
            .map(Map.Entry::getKey)
            .collect(Collectors.toSet());

        return new ParityGameResult(even, odd);
    }
}
