package pgs.models;

import java.util.Objects;

public class Vertex {
    private final int id;
    private final int priority;
    private final Player player;
    private final String name;

    public Vertex(final int id, final int priority, final Player player, final String name) {
        if (id < 0) {
            throw new IllegalArgumentException("id must be a natural number");
        }
        this.id = id;

        if (priority < 0) {
            throw new IllegalArgumentException("priority must be a natural number");
        }

        this.priority = priority;

        Objects.requireNonNull(player);
        this.player = player;

        this.name = name;
    }

    public Vertex(final int id, final int priority, final Player player) {
        this(id, priority, player, null);
    }

    public int getId() {
        return this.id;
    }

    public int getPriority() {
        return this.priority;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || super.getClass() != other.getClass()) {
            return false;
        }
        Vertex vertex = (Vertex) other;

        return this.id == vertex.id &&
            this.priority == vertex.priority &&
            this.player == vertex.player &&
            Objects.equals(this.name, vertex.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.priority, this.player, this.name);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "id=" + this.id +
                ", priority=" + this.priority +
                ", player=" + this.player +
                ", name='" + this.name + '\'' +
                '}';
    }
}
