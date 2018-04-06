package pgs.models;

import java.util.Objects;
import java.util.Set;

public class ParityGameResult {
    private final Set<Vertex> even;
    private final Set<Vertex> odd;

    public ParityGameResult(final Set<Vertex> even, final Set<Vertex> odd) {
        Objects.requireNonNull(even);
        this.even = even;

        Objects.requireNonNull(odd);
        this.odd = odd;
    }

    public Set<Vertex> getEven() {
        return this.even;
    }

    public Set<Vertex> getOdd() {
        return this.odd;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || super.getClass() != other.getClass()) {
            return false;
        }
        ParityGameResult that = (ParityGameResult) other;

        return Objects.equals(this.even, that.even) &&
            Objects.equals(this.odd, that.odd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.even, this.odd);
    }

    @Override
    public String toString() {
        return "ParityGameResult{" +
            "even=" + this.even +
            ", odd=" + this.odd +
            '}';
    }
}
