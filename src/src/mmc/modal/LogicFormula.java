package mmc.modal;

public class LogicFormula extends Formula {
    protected Formula l,r;
    protected Operator o;

    public LogicFormula(Formula l, Formula r, Operator o)
    {
        this.l = l;
        this.r = r;
        this.o = o;
    }

    @Override
    public String toString() {
        return String.format("(%s %s %s)", this.l, this.o.getSymbol(), this.r);
    }
}
