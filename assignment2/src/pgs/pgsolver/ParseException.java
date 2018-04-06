package pgs.pgsolver;

public class ParseException extends SyntaxException {
    public ParseException(Parser parser) {
        this("Unexpected token", parser);
    }

    public ParseException(String message, Parser parser) {
        super(message, parser);
    }
}
