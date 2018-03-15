package mmc.modal.visitors;

import mmc.modal.formulas.*;

public class EmersonLeiFormulaVisitor implements FormulaVisitor {
    @Override
    public void visit(Formula formula) {
        System.out.println("emerson lei fomula");
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
    public void visit(LogicFormula formula) {
        System.out.println("emerson lei logic");
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
}
