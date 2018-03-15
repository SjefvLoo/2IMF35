package mmc.modal.formulas;

import mmc.modal.visitors.FormulaVisitor;

public class LogicAndFormula extends LogicFormula implements Formula {
    @Override
    public void accept(FormulaVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    protected String getSymbol() {
        return "&&";
    }
}
