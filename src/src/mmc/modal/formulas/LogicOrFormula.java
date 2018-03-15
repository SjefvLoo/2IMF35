package mmc.modal.formulas;

import mmc.modal.visitors.FormulaVisitor;

public class LogicOrFormula extends LogicFormula implements Formula {
    @Override
    public void accept(FormulaVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    protected String getSymbol() {
        return "||";
    }
}
