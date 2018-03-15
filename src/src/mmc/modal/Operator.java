package mmc.modal;

public enum Operator {
    AND_OP("&&"),
    OR_OP("||"),
    ;

    private final String symbol;

    Operator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol(){
        return this.symbol;
    }
}
