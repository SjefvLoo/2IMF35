package mmc.modal.formulas;

import mmc.modal.visitors.FormulaVisitor;

public class LiteralTrue implements Formula {
    @Override
    public void accept(FormulaVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "true";
    }
}
