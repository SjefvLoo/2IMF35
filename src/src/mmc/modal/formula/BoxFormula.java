package mmc.modal.formula;

public class BoxFormula extends Formula {
    protected String actionname;
    protected Formula f;
    public BoxFormula(String actionname, Formula f)
    {
        this.actionname = actionname;
        this.f = f;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", this.actionname, this.f);
    }
}
