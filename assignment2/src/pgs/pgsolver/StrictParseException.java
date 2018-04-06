package pgs.pgsolver;

public class StrictParseException extends ParseException {
    public StrictParseException(StrictParser parser) {
        super(parser);
    }

    public StrictParseException(String message, StrictParser parser) {
        super(message, parser);
    }
}
