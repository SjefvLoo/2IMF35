package mmc.modal.visitors;

import mmc.modal.formulas.*;
import mmc.models.Lts;
import mmc.models.State;

import java.util.*;

public class TrivialFormulaVisitor implements FormulaVisitor {
    private final Map<Formula, Set<State>> results;
    private final Map<RecursionVariable, Set<State>> fixedPointResults;
    private final Lts lts;
    private final Set<State> states;

    public TrivialFormulaVisitor(Lts lts){
        this.results = new HashMap<>();
        Objects.requireNonNull(lts);
        this.states = new HashSet<>(Arrays.asList(lts.getStates()));
        this.lts = lts;
        this.fixedPointResults = new HashMap<>();
    }

    private Set<State> getFormulaResult(Formula f) {
        f.accept(this);
        return results.get(f);
    }

    private void storeResult(Formula formula, Set<State> result)
    {
        results.put(formula, result);
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
        storeResult(formula, new HashSet<>());
    }

    @Override
    public void visit(LiteralTrue formula) {
        storeResult(formula, states);
    }

    @Override
    public void visit(LogicAndFormula formula) {
        Set<State> leftResult = getFormulaResult(formula.getL());
        Set<State> rightResult = getFormulaResult(formula.getR());
        HashSet<State> result = new HashSet<>(leftResult);
        result.retainAll(rightResult);
        storeResult(formula, result);
    }

    @Override
    public void visit(LogicFormula formula) {
        System.out.println("trivial logic");
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void visit(LogicOrFormula formula) {
        Set<State> leftResult = getFormulaResult(formula.getL());
        Set<State> rightResult = getFormulaResult(formula.getR());
        HashSet<State> result = new HashSet<>(leftResult);
        result.addAll(rightResult);
        storeResult(formula, result);
    }

    @Override
    public void visit(MuFormula formula) {
        RecursionVariable r = formula.getR();
        Formula subformula = formula.getF();
        fixedPointResults.put(r, new HashSet<>());
        storeResult(formula, fixedPoint(subformula, r));
    }

    @Override
    public void visit(NuFormula formula) {
        RecursionVariable r = formula.getR();
        Formula subformula = formula.getF();
        fixedPointResults.put(r, new HashSet<>(states));
        storeResult(formula, fixedPoint(subformula, r));
    }

    private Set<State> fixedPoint(Formula subformula, RecursionVariable r)
    {
        int i = 0;
        Boolean equalibrium;
        do {
            subformula.accept(this);
            Set<State> result = results.get(subformula);
            Set<State> previousresult = fixedPointResults.put(r, result);
            equalibrium = (result.containsAll(previousresult) && result.size() == previousresult.size());
        } while(!equalibrium);

        return fixedPointResults.get(r);
    }

    @Override
    public void visit(RecursionVariable formula) {
        this.storeResult(formula, fixedPointResults.get(formula));
    }

    @Override
    public Set<State> calculate(Formula formula) {
        return getFormulaResult(formula);
    }

    public void clear() {
        results.clear();
    }
}
