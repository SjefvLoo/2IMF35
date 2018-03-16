package mmc.modal.visitors;

import mmc.modal.formulas.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class VariableMatcher implements FormulaVisitor {
    private final RecursionVariable variable;
    private final Set<RecursionVariable> free;

    private VariableMatcher(RecursionVariable variable) {
        Objects.requireNonNull(variable);
        this.variable = variable;
        this.free = new HashSet<>();
    }

    public static Set<RecursionVariable> findFreeVariables(FixedPointFormula formula) {
        VariableMatcher matcher = new VariableMatcher(formula.getRecursionVariable());
        formula.accept(matcher);

        return matcher.free;
    }

    @Override
    public void visit(BoxFormula formula) {
        formula.getFormula().accept(this);
    }

    @Override
    public void visit(DiamondFormula formula) {
        formula.getFormula().accept(this);
    }

    @Override
    public void visit(LiteralFalse formula) {
        return;  // Do nothing.
    }

    @Override
    public void visit(LiteralTrue formula) {
        return;  // Do nothing.
    }

    @Override
    public void visit(LogicAndFormula formula) {
        formula.getLeft().accept(this);
        formula.getRight().accept(this);
    }

    @Override
    public void visit(LogicOrFormula formula) {
        formula.getLeft().accept(this);
        formula.getRight().accept(this);
    }

    @Override
    public void visit(MuFormula formula) {
        formula.getFreeVariables().stream()
                .filter(other -> !this.variable.equals(other))
                .map(this.free::add);
    }

    @Override
    public void visit(NuFormula formula) {
        formula.getFreeVariables().stream()
                .filter(other -> !this.variable.equals(other))
                .map(this.free::add);
    }

    @Override
    public void visit(RecursionVariable formula) {
        if(!this.variable.equals(formula)) {
            this.free.add(formula);
        }
    }
}
