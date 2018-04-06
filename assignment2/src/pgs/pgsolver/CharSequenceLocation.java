package pgs.pgsolver;

public interface CharSequenceLocation {
    int getIndex();
    int getLine();
    int getColumn();

    default String location() {
        return String.format("%d:%d (%d)", this.getLine(), this.getColumn(), this.getIndex());
    }
}
