package mmc.modal;

public class MuFormula extends Formula {
    protected RecursionVariable r;
    protected Formula f;

    public MuFormula(RecursionVariable r, Formula f)
    {
        this.r = r;
        this.f = f;
    }
}
