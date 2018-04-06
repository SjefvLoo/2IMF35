package pgs.models;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ParityGame {
    // Taken from HashMap
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private final Map<Integer, Vertex> vertices;
    private final Map<Vertex, Set<Vertex>> successors;
    private final Map<Integer, Set<Vertex>> priorities;
    private final int maxPriority;

    public ParityGame(final Set<NodeSpec> nodeSpecs) {
        Objects.requireNonNull(nodeSpecs);
        this.vertices = Collections.unmodifiableMap(
            nodeSpecs.stream()
                .map(NodeSpec::getVertex)
                .collect(Collectors.toMap(Vertex::getId, Function.identity())));
        this.successors = Collections.unmodifiableMap(
            nodeSpecs.stream()
                .collect(Collectors.toMap(
                    NodeSpec::getVertex,
                    nodeSpec -> nodeSpec.getSuccessors().stream()
                        .map(this.vertices::get)
                        .collect(Collectors.toSet()))));
        if (this.successors.values()
                .stream()
                .map(Set::size)
                .anyMatch(x -> x == 0)) {
            throw new IllegalArgumentException("Each vertex must have a successor");
        }
        this.priorities = Collections.unmodifiableMap(
                nodeSpecs.stream()
                        .map(NodeSpec::getVertex)
                        .collect(Collectors.groupingBy(Vertex::getPriority, Collectors.toSet())));


        this.maxPriority = priorities.keySet().stream()
            .reduce(Integer::max)
            .orElse(0);
    }

    public Map<Integer, Vertex> getVertices() {
        return this.vertices;
    }

    public Map<Vertex, Set<Vertex>> getSuccessors() {
        return this.successors;
    }

    public Map<Integer, Set<Vertex>> getPriorities() {
        return this.priorities;
    }

    public int getMaxPriority() {
        return this.maxPriority;
    }

    public boolean isTotal() {
        return this.successors.values().stream()
            .allMatch(vertexSuccessors -> vertexSuccessors.size() == this.successors.size());
    }

    public boolean containsEdge(Vertex from, Vertex to) {
        return this.successors.containsKey(from) && this.successors.get(from).contains(to);
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || super.getClass() != other.getClass()) {
            return false;
        }
        ParityGame that = (ParityGame) other;

        return this.maxPriority == that.maxPriority &&
            Objects.equals(this.vertices, that.vertices) &&
            Objects.equals(this.successors, that.successors) &&
            Objects.equals(this.priorities, that.priorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.vertices, this.successors, this.priorities, this.maxPriority);
    }

    @Override
    public String toString() {
        return "ParityGame{" +
            "vertices=" + this.vertices +
            ", successors=" + this.successors +
            ", priorities=" + this.priorities +
            ", maxPriority=" + this.maxPriority +
            '}';
    }
}
