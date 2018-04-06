package pgs.pgsolver;

import pgs.models.ParityGame;

public class PGSolverBuilder {
    public ParityGame buildLenient(CharSequence input) {
        return this.build(input, new Parser());
    }

    public ParityGame buildStrict(CharSequence input) {
        return this.build(input, new StrictParser());
    }

    public ParityGame build(CharSequence input) {
        return this.buildStrict(input);
    }

    public ParityGame build(CharSequence input, Parser parser) {
        Lexer lexer = new Lexer(input);
        ParityGame parityGame = parser.parse(lexer);

        return parityGame;
    }
}
