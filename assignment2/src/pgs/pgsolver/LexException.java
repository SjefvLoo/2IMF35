package pgs.pgsolver;

public class LexException extends SyntaxException {
    public LexException(Lexer lexer) {
        super("Token not recognized", lexer);
    }
}
