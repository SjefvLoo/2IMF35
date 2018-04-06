package pgs;

import pgs.models.ParityGame;
import pgs.models.ParityGameResult;
import pgs.models.Vertex;
import pgs.pgsolver.PGSolverBuilder;
import pgs.pgsolver.SyntaxException;
import pgs.strategies.InputLiftingStrategy;
import pgs.strategies.LiftingStrategy;
import pgs.strategies.RandomLiftingStrategy;
import pgs.strategies.ShortestSelfCycleStrategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public final static int ARGS = 2;

    public enum Strategy {
        INPUT,
        RANDOM,
        SSC,
        ALL,
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
        List<LiftingStrategy> liftingStrategies;
        switch (strategy) {
            case INPUT:
                liftingStrategies = Arrays.asList(new InputLiftingStrategy(parityGame));
                break;
            case RANDOM:
                liftingStrategies = Arrays.asList(new RandomLiftingStrategy(parityGame));
                break;
            case SSC:
                liftingStrategies = Arrays.asList(new ShortestSelfCycleStrategy(parityGame));
                break;
            case ALL:
                liftingStrategies = Arrays.asList(new InputLiftingStrategy(parityGame),
                new RandomLiftingStrategy(parityGame),
                        new ShortestSelfCycleStrategy(parityGame));
                break;
            default:
                Main.help();
                return;
        }

        Set<Vertex> gEven = null;
        Set<Vertex> gOdd = null;
        for(LiftingStrategy liftingStrategy : liftingStrategies) {
            System.out.println("Using strategy: " + liftingStrategy.toString());
            SmallProgressMeasures smallProgressMeasures = new SmallProgressMeasures();
            ParityGameResult parityGameResult = smallProgressMeasures.calculate(parityGame, liftingStrategy);

            Set<Vertex> even = parityGameResult.getEven();
            Set<Vertex> odd = parityGameResult.getOdd();
            System.out.println(String.format("Even: %s", Main.verticesToInts(even)));
            System.out.println(String.format("Odd: %s", Main.verticesToInts(odd)));
            if(gEven == null)
            {
                gEven = even;
                gOdd = odd;
            } else {
                if (!(even.containsAll(gEven) && gEven.containsAll(even) && odd.containsAll(gOdd) && gOdd.containsAll(odd)))
                {
                    System.out.println("ERROR: Results not equal!!!");
                }
            }
            System.out.println("Attempted lifts: " + smallProgressMeasures.getLiftingAttempts());
            System.out.println("Actual lifts: " + smallProgressMeasures.getLifts());
        }
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
