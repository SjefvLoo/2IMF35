package pgs.models;

import java.util.Objects;
import java.util.Set;

public class NodeSpec {
    private final Vertex vertex;
    private final Set<Integer> successors;

    public NodeSpec(final Vertex vertex, final Set<Integer> successors) {
        Objects.requireNonNull(vertex);
        this.vertex = vertex;

        Objects.requireNonNull(vertex);
        this.successors = successors;
    }

    public Vertex getVertex() {
        return this.vertex;
    }

    public Set<Integer> getSuccessors() {
        return this.successors;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        NodeSpec nodeSpec = (NodeSpec) other;

        return Objects.equals(this.vertex, nodeSpec.vertex) &&
            Objects.equals(this.successors, nodeSpec.successors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.vertex, this.successors);
    }

    @Override
    public String toString() {
        return "NodeSpec{" +
            "vertex=" + this.vertex +
            ", successors=" + this.successors +
            '}';
    }
}
