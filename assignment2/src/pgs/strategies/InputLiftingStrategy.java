package pgs.strategies;

import pgs.models.ParityGame;
import pgs.models.Vertex;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InputLiftingStrategy extends LiftingStrategy {
    private final List<Vertex> vertexOrder;
    private Iterator<Vertex> vertexIterator;

    public InputLiftingStrategy(final ParityGame parityGame) {
        super(parityGame);
        this.vertexOrder = new ArrayList<>(this.getParityGame().getVertices().values());
        if (this.vertexOrder.size() < 1) {
            throw new IllegalStateException("Require at least one state in the parity game");
        }
        this.vertexIterator = this.vertexOrder.iterator();
    }

    public List<Vertex> getVertexOrder() {
        return this.vertexOrder;
    }

    @Override
    public boolean hasNext() {
        // This is a circular Iterator.
        return true;
    }

    @Override
    public Vertex next() {
        if (!this.vertexIterator.hasNext()) {
            this.vertexIterator = this.vertexOrder.iterator();
        }

        return this.vertexIterator.next();
    }
}
