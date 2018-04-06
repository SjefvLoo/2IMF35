package pgs.pgsolver;

public class SyntaxException extends RuntimeException implements CharSequenceLocation {
    private final CharSequenceLocation charSequenceLocation;

    public SyntaxException(String message, CharSequenceLocation charSequenceLocation) {
        super(message + String.format(" [around %s]", charSequenceLocation.location()));
        this.charSequenceLocation = charSequenceLocation;
    }

    @Override
    public int getIndex() {
        return this.charSequenceLocation.getIndex();
    }

    @Override
    public int getLine() {
        return this.charSequenceLocation.getLine();
    }

    @Override
    public int getColumn() {
        return this.charSequenceLocation.getColumn();
    }
}
