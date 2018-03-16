package mmc.modal.formulas;

import mmc.modal.visitors.FormulaVisitor;

public class NuFormula extends FixedPointFormula implements Formula {
    public NuFormula(RecursionVariable recursionVariable, Formula formula) {
        super(recursionVariable, formula);
    }

    @Override
    public void accept(FormulaVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return String.format("nu %s.%s", this.getRecursionVariable(), this.getFormula());
    }
}
