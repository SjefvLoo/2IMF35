package mmc;

public class BoxFormula extends Formula {
    protected String actionname;
    protected Formula f;
    public BoxFormula(String actionname, Formula f)
    {
        this.actionname = actionname;
        this.f = f;
    }
}
