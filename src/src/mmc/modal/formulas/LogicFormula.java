package mmc.modal.formulas;

import java.util.Objects;

public abstract class LogicFormula implements Formula {
    private Formula left;
    private Formula right;

    public Formula getLeft() {
        return this.left;
    }

    public void setLeft(Formula left) {
        this.left = left;
    }

    public Formula getRight() {
        return this.right;
    }

    public void setRight(Formula right) {
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogicFormula that = (LogicFormula) o;
        return Objects.equals(this.left, that.left) &&
                Objects.equals(this.right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.left, this.right);
    }

    public abstract String toString();
}
