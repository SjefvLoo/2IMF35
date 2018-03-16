package mmc.modal.formulas;

import mmc.modal.visitors.FormulaVisitor;

public class MuFormula extends FixedPointFormula implements Formula {
    public MuFormula(RecursionVariable recursionVariable, Formula formula) {
        super(recursionVariable, formula);
    }

    @Override
    public void accept(FormulaVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return String.format("mu %s.%s", this.getRecursionVariable(), this.getFormula());
    }
}
