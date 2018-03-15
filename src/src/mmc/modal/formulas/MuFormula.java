package mmc.modal.formulas;

import mmc.modal.visitors.FormulaVisitor;

public class MuFormula implements Formula {
    protected RecursionVariable r;
    protected Formula f;

    public MuFormula(RecursionVariable r, Formula f)
    {
        this.r = r;
        this.f = f;
    }

    public RecursionVariable getR() {
        return r;
    }

    public Formula getF() {
        return f;
    }

    @Override
    public void accept(FormulaVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return String.format("mu %s.%s", this.r, this.f);
    }
}
