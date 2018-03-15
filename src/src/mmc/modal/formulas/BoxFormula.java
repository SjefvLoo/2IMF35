package mmc.modal.formulas;

import mmc.modal.visitors.FormulaVisitor;

public class BoxFormula implements Formula {
    protected String actionname;
    protected Formula f;
    public BoxFormula(String actionname, Formula f)
    {
        this.actionname = actionname;
        this.f = f;
    }

    @Override
    public void accept(FormulaVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", this.actionname, this.f);
    }
}
