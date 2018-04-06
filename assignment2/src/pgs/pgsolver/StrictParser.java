package pgs.pgsolver;

import pgs.models.NodeSpec;
import pgs.models.ParityGame;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class StrictParser extends Parser {
    @Override
    protected ParityGame parseParityGame() {
        int maxIdentifier = this.parseHeader();
        Map<Integer, NodeSpec> nodeSpecs = new LinkedHashMap<>(
            (int) Math.ceil(maxIdentifier / ParityGame.DEFAULT_LOAD_FACTOR));

        do {
            NodeSpec nodeSpec = this.parseNodeSpec();
            nodeSpec = nodeSpecs.put(nodeSpec.getVertex().getId(), nodeSpec);
            if (nodeSpec != null) {
                throw new StrictParseException("Duplicate node specification", this);
            }
        } while (this.peakToken(TokenType.NUMBER));

        if (!nodeSpecs.containsKey(0) || nodeSpecs.size() - 1 != maxIdentifier) {
            throw new StrictParseException(
                String.format("Expected %d nodes, but found %d", maxIdentifier + 1, nodeSpecs.size()), this);
        }

        ParityGame parityGame = new ParityGame(new LinkedHashSet<>(nodeSpecs.values()));

        return parityGame;
    }

    @Override
    protected int parseHeader() {
        int maxIdentifier = super.parseHeader();
        super.eatToken(TokenType.NEWLINE);

        return maxIdentifier;
    }

    protected NodeSpec parseNodeSpec() {
        NodeSpec nodeSpec = super.parseNodeSpec();
        super.eatToken(TokenType.NEWLINE);

        return nodeSpec;
    }

    @Override
    protected int parseNumber() {
        String data = this.nextTokenData(TokenType.NUMBER);
        int number = Integer.parseInt(data);

        if (number != 0 && data.charAt(0) == '0') {
            throw new StrictParseException("Number has leading a leading 0", this);
        }

        return number;
    }

    @Override
    protected boolean isLayout(Token token) {
        return token != null && token.getType() == TokenType.LAYOUT;
    }
}
