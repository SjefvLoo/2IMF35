package pgs;

import pgs.models.ParityGame;
import pgs.models.ParityGameResult;
import pgs.models.Vertex;
import pgs.pgsolver.PGSolverBuilder;
import pgs.pgsolver.SyntaxException;
import pgs.strategies.InputLiftingStrategy;
import pgs.strategies.LiftingStrategy;
import pgs.strategies.RandomLiftingStrategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public final static int ARGS = 2;

    public enum Strategy {
        INPUT,
        RANDOM,
        ;
    }

    public static void main(String[] args) {
        if(args.length != Main.ARGS) {
            Main.help();
            return;
        }
        Strategy strategy;
        try {
            strategy = Strategy.valueOf(args[0].toUpperCase());
        } catch(IllegalArgumentException e) {
            Main.help();
            return;
        }
        ParityGame parityGame = Main.loadPGSolver(args[1]);
        LiftingStrategy liftingStrategy;
        switch (strategy) {
            case INPUT:
                liftingStrategy = new InputLiftingStrategy(parityGame);
                break;
            case RANDOM:
                liftingStrategy = new RandomLiftingStrategy(parityGame);
                break;
            default: // TODO: add more
                Main.help();
                return;
        }

        SmallProgressMeasures smallProgressMeasures = new SmallProgressMeasures();
        ParityGameResult parityGameResult = smallProgressMeasures.calculate(parityGame, liftingStrategy);

        System.out.println(String.format("Even: %s", Main.verticesToInts(parityGameResult.getEven())));
        System.out.println(String.format("Odd: %s", Main.verticesToInts(parityGameResult.getOdd())));
    }

    private static Set<Integer> verticesToInts(Set<Vertex> vertices) {
        return vertices.stream()
            .map(Vertex::getId)
            .collect(Collectors.toSet());
    }

    private static ParityGame loadPGSolver(String filename) {
        // http://www.win.tue.nl/~timw/downloads/amc2017/pgsolver.pdf#Section%203.5
        String pgSolverText = Main.readFile(filename);
        PGSolverBuilder pgSolverBuilder = new PGSolverBuilder();
        try {
            return pgSolverBuilder.build(pgSolverText);
        } catch (SyntaxException e) {
            e.printStackTrace();
            System.err.println(String.format("Unable to parse PGSolver parity game from: %s", filename));
            System.exit(2);
            return null;
        }
    }

    private static String readFile(String filename) {
        try {
            return new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            System.err.println(String.format("Unable to read input file: %s", filename));
            System.exit(2);
            return null;
        }
    }

    private static void help() {
        System.err.println(
            String.format("%d arguments required: (%s) <PGSolver file>",
            Main.ARGS,
            String.join(
        " | ",
                Arrays.stream(Strategy.values())
                    .map(Strategy::name)
                    .map(String::toLowerCase)
                    .collect(Collectors.toList())
        )));
        System.exit(1);
    }
}
