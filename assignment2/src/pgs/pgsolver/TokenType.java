package pgs.pgsolver;

import java.util.regex.Pattern;

public enum TokenType {
    EOF("$"),
    PARITY("parity"),
    COMMA(","),
    SEMICOLON(";"),
    NUMBER("\\d+"),
    STRING("\"(\\.|[^\"])*\"|[A-Za-z]\\w*|\\*"),
    NEWLINE("\\n"),
    LAYOUT("[ \\t\\x0B\\f\\r]+"),
    ;

    private final Pattern pattern;

    TokenType(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public Pattern getPattern() {
        return this.pattern;
    }
}
