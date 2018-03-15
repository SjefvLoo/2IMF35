package mmc.modal.formulas;

import mmc.modal.visitors.FormulaVisitor;

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
}
