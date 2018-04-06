package pgs.models;

import java.util.Arrays;
import java.util.Objects;

public class Measure implements Comparable<Measure> {
    private static final Measure top;
    private final boolean isTop;
    private final int[] components;
    private final MeasureComparator measureComparator;

    static {
        top = new Measure(true);
    }

    public Measure(final int... components) {
        this(false, components);
        if (components.length < 1) {
            throw new IllegalArgumentException("Must have at least one component");
        }
    }

    private Measure(final boolean isTop, final int... components) {
        this.isTop = isTop;
        Objects.requireNonNull(components);
        this.components = components;
        this.measureComparator = new MeasureComparator(this.components.length);
    }

    public static Measure top() {
        return Measure.top;
    }

    public boolean isTop() {
        return isTop;
    }

    public int[] getComponents() {
        return this.components;
    }

    public final boolean lt(final Measure other) {
        return this.compareTo(other) < 0;
    }

    public final boolean le(final Measure other) {
        return this.compareTo(other) <= 0;
    }

    public final boolean gt(final Measure other) {
        return this.compareTo(other) > 0;
    }

    public final boolean ge(final Measure other) {
        return this.compareTo(other) >= 0;
    }

    public final boolean eq(final Measure other) {
        return this.compareTo(other) == 0;
    }

    public final boolean ne(final Measure other) {
        return this.compareTo(other) != 0;
    }

    @Override
    public int compareTo(final Measure other) {
        if (!this.isTop && !other.isTop && this.components.length != other.components.length) {
            throw new IllegalArgumentException("Measures with different number of components");
        }

        return this.measureComparator.compare(this, other);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || super.getClass() != o.getClass()) {
            return false;
        }
        Measure measure = (Measure) o;

        return this.isTop == measure.isTop &&
            Arrays.equals(this.components, measure.components) &&
            Objects.equals(this.measureComparator, measure.measureComparator);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(this.isTop, this.measureComparator);
        result = 31 * result + Arrays.hashCode(this.components);

        return result;
    }

    @Override
    public String toString() {
        return "Measure{" +
            "isTop=" + this.isTop +
            ", components=" + Arrays.toString(this.components) +
            ", measureComparator=" + this.measureComparator +
            '}';
    }
}
