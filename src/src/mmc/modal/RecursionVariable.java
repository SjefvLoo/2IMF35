package mmc.modal;

import mmc.modal.formulas.Formula;

public class RecursionVariable extends Formula {
    protected char n;

    public RecursionVariable(char n)
    {
        this.n = n;
    }

    @Override
    public String toString() {
        return String.valueOf(this.n);
    }
}
