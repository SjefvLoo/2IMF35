package mmc;

public class LogicFormula extends Formula {
    protected Formula l,r;
    protected Operator o;

    public LogicFormula(Formula l, Formula r, Operator o)
    {
        this.l = l;
        this.r = r;
        this.o = o;
    }
}
