package mmc.modal;

public class DiamondFormula extends Formula {
    protected String actionname;
    protected Formula f;

    public DiamondFormula(String actionname, Formula f)
    {
        this.actionname = actionname;
        this.f = f;
    }
}
