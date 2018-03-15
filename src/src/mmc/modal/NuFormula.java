package mmc.modal;

public class NuFormula extends Formula {
    protected RecursionVariable r;
    protected Formula f;

    public NuFormula(RecursionVariable r, Formula f)
    {
        this.r = r;
        this.f = f;
    }

    @Override
    public String toString() {
        return String.format("nu %s.%s", this.r, this.f);
    }
}
