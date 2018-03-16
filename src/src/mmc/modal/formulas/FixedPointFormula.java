package mmc.modal.formulas;

import mmc.modal.visitors.VariableMatcher;

import java.util.Objects;
import java.util.Set;

public abstract class FixedPointFormula implements Formula {
    private final RecursionVariable recursionVariable;
    private final Formula formula;
    private final Set<RecursionVariable> freeVariables;

    public FixedPointFormula(RecursionVariable recursionVariable, Formula formula) {
        this.recursionVariable = recursionVariable;
        this.formula = formula;
        this.freeVariables = VariableMatcher.findFreeVariables(this);
    }

    public RecursionVariable getRecursionVariable() {
        return this.recursionVariable;
    }

    public Formula getFormula() {
        return this.formula;
    }

    public Set<RecursionVariable> getFreeVariables() {
        return this.freeVariables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FixedPointFormula that = (FixedPointFormula) o;
        return Objects.equals(this.recursionVariable, that.recursionVariable) &&
                Objects.equals(this.formula, that.formula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.recursionVariable, this.formula);
    }

    public abstract String toString();
}
