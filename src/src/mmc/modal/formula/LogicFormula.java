package mmc.modal.formula;

public abstract class LogicFormula extends Formula {
    protected Formula l,r;

    public Formula getL() {
        return l;
    }

    public void setL(Formula l) {
        this.l = l;
    }

    public Formula getR() {
        return r;
    }

    public void setR(Formula r) {
        this.r = r;
    }

    protected abstract String getSymbol();

    @Override
    public String toString() {
        return String.format("(%s %s %s)", this.l, this.getSymbol(), this.r);
    }
}
