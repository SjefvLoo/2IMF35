package mmc.modal.visitors;

import mmc.modal.formulas.*;
import mmc.models.Lts;
import mmc.models.State;

import java.util.*;

public class TrivialFormulaVisitor implements FormulaVisitor {
    private final Map<Formula, Set<State>> results;
    private final Lts lts;
    private final Set<State> states;

    public TrivialFormulaVisitor(Lts lts){
        this.results = new HashMap<>();
        Objects.requireNonNull(lts);
        this.states = new HashSet<>(Arrays.asList(lts.getStates()));
        this.lts = lts;
    }

    private Set<State> getFormulaResult(Formula f) {
        if (results.get(f) == null) {
            f.accept(this);
        }
        return results.get(f);
    }

    private void storeResult(Formula formula, Set<State> result)
    {
        if(results.put(formula, result) != null)
        {
            throw new IllegalStateException(formula.toString() + " already computed.");
        }
    }


    @Override
    public void visit(Formula formula) {
        System.out.println("trivial fomula");
    }

    @Override
    public void visit(BoxFormula formula) {
        System.out.println("trivial box");
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void visit(DiamondFormula formula) {
        System.out.println("trivial diamond");
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void visit(LiteralFalse formula) {
        System.out.println("trivial false");
        storeResult(formula, new HashSet<>());
    }

    @Override
    public void visit(LiteralTrue formula) {
        System.out.println("trivial true");
        storeResult(formula, this.states);
    }

    @Override
    public void visit(LogicAndFormula formula) {
        System.out.println("trivial and");
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void visit(LogicFormula formula) {
        System.out.println("trivial logic");
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void visit(LogicOrFormula formula) {
        System.out.println("trivial or");
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void visit(MuFormula formula) {
        System.out.println("trivial mu");
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void visit(NuFormula formula) {
        System.out.println("trivial nu");
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void visit(RecursionVariable formula) {
        System.out.println("trivial recursion");
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Set<State> calculate(Formula formula) {
        return getFormulaResult(formula);
    }

    public void clear() {
        results.clear();
    }
}
