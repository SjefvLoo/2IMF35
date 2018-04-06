package pgs.strategies;

import pgs.models.ParityGame;
import pgs.models.Vertex;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InputLiftingStrategy extends LiftingStrategy {
    private final List<Vertex> vertexOrder;
    private Iterator<Vertex> vertexIterator;
    private Map<Vertex, Boolean> lifted;
    private int liftedCount;

    @Override
    public void didLift(Vertex v) {
        Boolean oldValue = this.lifted.put(v, false);
        if (oldValue != null && oldValue) {
            this.liftedCount--;
        }
    }

    @Override
    public void didNotLift(Vertex v) {
        Boolean oldValue = this.lifted.put(v, true);
        if (oldValue != null && !oldValue) {
            this.liftedCount++;
        }
    }

    public InputLiftingStrategy(final ParityGame parityGame) {
        super(parityGame);
        this.vertexOrder = new ArrayList<>(this.getParityGame().getVertices().values());
        if (this.vertexOrder.size() < 1) {
            throw new IllegalStateException("Require at least one state in the parity game");
        }
        this.initializeIterator();

        this.lifted = parityGame.getVertices()
                .values()
                .stream()
                .collect(Collectors.toMap(Function.identity(), k -> false));
        this.liftedCount = 0;
    }

    protected void initializeIterator()
    {
        this.vertexIterator = this.vertexOrder.iterator();
    }

    public List<Vertex> getVertexOrder() {
        return this.vertexOrder;
    }

    @Override
    public boolean hasNext() {
        return this.liftedCount < super.getParityGame().getVertices().size();
    }

    @Override
    public Vertex next() {
        if (!this.vertexIterator.hasNext()) {
            this.vertexIterator = this.vertexOrder.iterator();
        }

        return this.vertexIterator.next();
    }
}
