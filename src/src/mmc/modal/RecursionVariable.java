package mmc.modal;

public class RecursionVariable extends Formula {
    protected char n;

    public RecursionVariable(char n)
    {
        this.n = n;
    }

    @Override
    public String toString() {
        return String.valueOf(this.n);
    }
}