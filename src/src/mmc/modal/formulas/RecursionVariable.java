package mmc.modal.formulas;

import mmc.modal.visitors.FormulaVisitor;

import java.util.Objects;

public class RecursionVariable implements Formula {
    protected char n;

    public RecursionVariable(char n)
    {
        this.n = n;
    }

    @Override
    public void accept(FormulaVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return String.valueOf(this.n);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecursionVariable that = (RecursionVariable) o;
        return n == that.n;
    }

    @Override
    public int hashCode() {

        return Objects.hash(n);
    }
}
