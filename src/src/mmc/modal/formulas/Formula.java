package mmc.modal.formulas;

import mmc.modal.visitors.FormulaVisitor;

public interface Formula {
    void accept(FormulaVisitor visitor);
}
