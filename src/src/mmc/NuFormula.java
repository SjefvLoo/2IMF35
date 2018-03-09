package mmc;

public class NuFormula extends Formula {
    protected RecursionVariable r;
    protected Formula f;

    public NuFormula(RecursionVariable r, Formula f)
    {
        this.r = r;
        this.f = f;
    }
}
