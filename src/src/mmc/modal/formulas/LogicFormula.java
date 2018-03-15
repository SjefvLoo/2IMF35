package mmc.modal.formulas;

import mmc.modal.visitors.FormulaVisitor;

public abstract class LogicFormula implements Formula {
    protected Formula l,r;

    public Formula getL() {
        return l;
    }

    public void setL(Formula l) {
        this.l = l;
    }

    public Formula getR() {
        return r;
    }

    public void setR(Formula r) {
        this.r = r;
    }

    protected abstract String getSymbol();

    @Override
    public void accept(FormulaVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return String.format("(%s %s %s)", this.l, this.getSymbol(), this.r);
    }
}
