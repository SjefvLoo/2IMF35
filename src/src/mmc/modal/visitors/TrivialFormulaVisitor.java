package mmc.modal.visitors;

import mmc.modal.formulas.*;

public class TrivialFormulaVisitor implements FormulaVisitor  {
    @Override
    public void visit(Formula formula) {
        System.out.println("trivial fomula");
    }

    @Override
    public void visit(BoxFormula formula) {
        System.out.println("trivial box");
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void visit(DiamondFormula formula) {
        System.out.println("trivial diamond");
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void visit(LiteralFalse formula) {
        System.out.println("trivial false");
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void visit(LiteralTrue formula) {
        System.out.println("trivial true");
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void visit(LogicAndFormula formula) {
        System.out.println("trivial and");
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void visit(LogicFormula formula) {
        System.out.println("trivial logic");
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void visit(LogicOrFormula formula) {
        System.out.println("trivial or");
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void visit(MuFormula formula) {
        System.out.println("trivial mu");
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void visit(NuFormula formula) {
        System.out.println("trivial nu");
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void visit(RecursionVariable formula) {
        System.out.println("trivial recursion");
        throw new UnsupportedOperationException("Not yet implemented!");
    }
}
