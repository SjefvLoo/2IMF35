package mmc.modal.formula;

import mmc.modal.RecursionVariable;

public class MuFormula extends Formula {
    protected RecursionVariable r;
    protected Formula f;

    public MuFormula(RecursionVariable r, Formula f)
    {
        this.r = r;
        this.f = f;
    }

    @Override
    public String toString() {
        return String.format("mu %s.%s", this.r, this.f);
    }
}
