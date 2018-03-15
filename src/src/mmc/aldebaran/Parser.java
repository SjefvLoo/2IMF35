package mmc.aldebaran;

import mmc.models.Label;
import mmc.models.Lts;
import mmc.models.State;

public class Parser {
    private boolean strict;
    private Lexer lexer;
    private int transitions;
    private int states;
    private Lts lts;

    public Parser() {
        this(false);
    }

    public Parser(boolean strict) {
        this.strict = strict;
        this.reset();
    }

    public boolean isStrict() {
        return this.strict;
    }

    public void setStrict(boolean strict) {
        this.strict = strict;
    }

    public Lts parse(Lexer lexer) {
        this.reset();
        this.lexer = lexer;

        this.parseHeader();
        for (int i = 0; i < this.transitions; i++) {
            if (this.strict) {
                eatToken(TokenType.NEWLINE);
            }
            this.parseTransition();
        }
        this.eatToken(TokenType.EOF);

        return this.lts;
    }

    private void parseHeader() {
        this.eatToken(TokenType.DES);

        this.eatToken(TokenType.LPAREN);

        int startIndex = this.parseStartIndex();

        this.eatToken(TokenType.COMMA);

        this.transitions = this.parseNumber();

        this.eatToken(TokenType.COMMA);

        this.states = this.parseNumber();

        this.eatToken(TokenType.RPAREN);

        this.lts = new Lts(this.states, startIndex);
    }

    private int parseStartIndex() {
        int startIndex = this.parseNumber();
        if (this.strict && startIndex != 0) {
            // http://cadp.inria.fr/man/aut.html
            throw new StrictParseException("Initial state must be 0 according to specification", this.lexer.getIndex());
        }

        return startIndex;
    }

    private int parseNumber() {
        Token token = this.nextToken(TokenType.NUMBER);
        int number = Integer.parseInt(token.getData());

        if (this.strict && number != 0 && token.getData().charAt(0) == '0') {
            throw new StrictParseException("Number has leading a leading 0", this.lexer.getIndex());
        }

        return number;
    }

    private void parseTransition() {
        this.eatToken(TokenType.LPAREN);

        State start = this.parseState();

        this.eatToken(TokenType.COMMA);

        Label label = this.parseLabel();

        this.eatToken(TokenType.COMMA);

        State end = this.parseState();

        this.eatToken(TokenType.RPAREN);

        boolean success = start.addTransition(label, end);
        if (!success) {
            throw new ParseException("Duplicate transition encountered", this.lexer.getIndex());
        }
    }

    private Label parseLabel() {
        Token token = this.nextToken();
        String labelText = token.getData();
        switch (token.getType()) {
            case DES:
                break;
            case STRING:
                if (labelText.charAt(0) == '"' && labelText.charAt(labelText.length() - 1) == '"') {
                    labelText = labelText.substring(1, labelText.length() - 1);
                }
                break;
            default:
                throw new ParseException(this.lexer.getIndex());

        }
        if (this.strict && labelText.length() > 5000) {
            throw new StrictParseException("A label can contain at most 5000 characters", this.lexer.getIndex());
        }

        return new Label(labelText);
    }

    private State parseState() {
        int number = this.parseNumber();
        if (number >= this.states) {
            throw new ParseException(String.format("Unknown state with number %s", number), this.lexer.getIndex());
        }

        return this.lts.getState(number);
    }

    private boolean isLayout(Token token) {
        return (token.getType() == TokenType.LAYOUT)
            || (!this.strict && (token.getType() == TokenType.NEWLINE));
    }

    private Token nextToken() {
        Token token = this.lexer.next();
        while (this.isLayout(token)) {
            token = this.lexer.next();
        }

        return token;
    }

    private Token nextToken(TokenType tokenType) {
        Token token = this.nextToken();
        if (token.getType() != tokenType) {
            throw new ParseException(this.lexer.getIndex());
        }

        return token;
    }

    private void eatToken(TokenType tokenType) {
        this.nextToken(tokenType);
    }

    private void reset() {
        this.lexer = null;
        this.states = 0;
        this.transitions = 0;
        this.lts = null;
    }
}
