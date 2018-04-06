package pgs.pgsolver;

import pgs.models.NodeSpec;
import pgs.models.ParityGame;
import pgs.models.Player;
import pgs.models.Vertex;

import java.util.*;

public class Parser implements CharSequenceLocation {
    private final Player[] players;
    private Lexer lexer;
    // Can be null if there no new token
    private Token peakToken;

    public Parser() {
        this.players = Player.values();
    }

    public ParityGame parse(final Lexer lexer) {
        Objects.requireNonNull(lexer);
        this.reset(lexer);

        ParityGame parityGame = parseParityGame();
        this.eatToken(TokenType.EOF);

        return parityGame;
    }

    @Override
    public int getIndex() {
        return this.lexer.getIndex();
    }

    @Override
    public int getLine() {
        return this.lexer.getLine();
    }

    @Override
    public int getColumn() {
        return this.lexer.getColumn();
    }

    protected ParityGame parseParityGame() {
        Map<Integer, NodeSpec> nodeSpecs;
        if(this.peakToken(TokenType.PARITY)) {
            int maxIdentifier = this.parseHeader();
            nodeSpecs = new LinkedHashMap<>((int) Math.ceil(maxIdentifier / ParityGame.DEFAULT_LOAD_FACTOR));
        } else {
            nodeSpecs = new LinkedHashMap<>();
        }

        do {
            NodeSpec nodeSpec = this.parseNodeSpec();
            nodeSpecs.put(nodeSpec.getVertex().getId(), nodeSpec);
        } while (this.peakToken(TokenType.NUMBER));

        ParityGame parityGame = new ParityGame(new LinkedHashSet<>(nodeSpecs.values()));

        // TODO: Remove this check?
        if (parityGame.isTotal()) {
            throw new ParseException("The parity game has no total edge relation", this);
        }

        return parityGame;
    }

    protected int parseHeader() {
        this.eatToken(TokenType.PARITY);

        int maxIdentifier = this.parseIdentifier();

        this.eatToken(TokenType.SEMICOLON);

        return maxIdentifier;
    }

    protected NodeSpec parseNodeSpec() {
        int id = this.parseIdentifier();
        int priority = this.parsePriority();
        Player player = this.parseOwner();
        Set<Integer> successors = this.parseSuccessors();

        String name = "";
        if (this.peakToken(TokenType.STRING)) {
            name = this.parseName();
        }
        this.eatToken(TokenType.SEMICOLON);

        Vertex vertex = new Vertex(id, priority, player, name);

        return new NodeSpec(vertex, successors);
    }

    protected int parseIdentifier() {
        int id = this.parseNumber();
        if (id < 0) {
            throw new ParseException("Identifier must be a natural number", this);
        }

        return id;
    }

    protected int parsePriority() {
        int priority = this.parseNumber();
        if (priority < 0) {
            throw new ParseException("Priority must be a natural number", this);
        }

        return priority;
    }

    protected Player parseOwner() {
        int number = parseNumber();

        if(number >= this.players.length) {
            throw new ParseException("Owner should be 0 or 1", this);
        }

        return this.players[number];
    }

    protected int parseNumber() {
        String data = this.nextTokenData(TokenType.NUMBER);
        int number = Integer.parseInt(data);

        return number;
    }

    protected Set<Integer> parseSuccessors() {
        Set <Integer> successors = new HashSet<>();

        int id = this.parseIdentifier();
        successors.add(id);

        while (this.peakToken(TokenType.COMMA)) {
            this.eatToken(TokenType.COMMA);

            id = this.parseIdentifier();
            successors.add(id);
        }

        return successors;
    }

    protected String parseName() {
        Token token = this.nextToken();
        String name = token.getData();
        switch (token.getType()) {
            case PARITY:
                break;
            case STRING:
                if (name.charAt(0) == '"' && name.charAt(name.length() - 1) == '"') {
                    name = name.substring(1, name.length() - 1);
                }
                break;
            default:
                throw new ParseException(this);
        }

        return name;
    }

    protected boolean isLayout(final Token token) {
        return token != null && (token.getType() == TokenType.LAYOUT || token.getType() == TokenType.NEWLINE);
    }

    protected final boolean peakToken(final TokenType tokenType) {
        return this.peakToken != null && this.peakToken.getType() == tokenType;
    }

    protected final Token nextToken() {
        Token token = this.peakToken;
        if (token == null) {
            throw new ParseException("Did not find a new token", this);
        }
        this.advance();

        return token;
    }

    protected final String nextTokenData(final TokenType tokenType) {
        Token token = this.nextToken();
        if (token.getType() != tokenType) {
            throw new ParseException(this);
        }

        return token.getData();
    }

    protected final void eatToken(final TokenType tokenType) {
        this.nextTokenData(tokenType);  // Just eat the data. Nom nom nom!
    }

    private void reset(final Lexer lexer) {
        this.lexer = lexer;

        // Set initial peak token
        this.advance();
    }

    private void advance() {
        Token token = null;
        if (this.lexer.hasNext()) {
            do {
                token = this.lexer.next();
            } while (this.isLayout(token) && this.lexer.hasNext());

            if (this.isLayout(token)) {
                token = null;
            }
        }

        this.peakToken = token;
    }
}
