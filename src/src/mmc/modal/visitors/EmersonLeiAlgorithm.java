package mmc.modal.visitors;

import mmc.modal.formulas.*;
import mmc.models.Lts;
import mmc.models.State;

import java.util.Objects;
import java.util.Set;

public class EmersonLeiAlgorithm implements FormulaCalculator, FormulaVisitor {
    private final Lts lts;

    public EmersonLeiAlgorithm(Lts lts) {
        Objects.requireNonNull(lts);
        this.lts = lts;
    }

    @Override
    public void visit(BoxFormula formula) {
        System.out.println("emerson lei box");
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void visit(DiamondFormula formula) {
        System.out.println("emerson lei diamond");
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void visit(LiteralFalse formula) {
        System.out.println("emerson lei false");
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void visit(LiteralTrue formula) {
        System.out.println("emerson lei true");
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void visit(LogicAndFormula formula) {
        System.out.println("emerson lei and");
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void visit(LogicOrFormula formula) {
        System.out.println("emerson lei or");
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void visit(MuFormula formula) {
        System.out.println("emerson lei mu");
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void visit(NuFormula formula) {
        System.out.println("emerson lei nu");
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void visit(RecursionVariable formula) {
        System.out.println("emerson lei recursion");
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Set<State> calculate(Formula formula) {
        return null;
    }
}
