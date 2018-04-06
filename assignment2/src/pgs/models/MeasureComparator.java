package pgs.models;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class MeasureComparator implements Comparator<Measure> {
    private final int length;

    public MeasureComparator(final int length) {
        this.length = length;
    }

    public final boolean lt(final Measure left, final Measure right) {
        return this.compare(left, right) < 0;
    }

    public final boolean le(final Measure left, final Measure right) {
        return this.compare(left, right) <= 0;
    }

    public final boolean gt(final Measure left, final Measure right) {
        return this.compare(left, right) > 0;
    }

    public final boolean ge(final Measure left, final Measure right) {
        return this.compare(left, right) >= 0;
    }

    public final boolean eq(final Measure left, final Measure right) {
        return this.compare(left, right) == 0;
    }

    public final boolean ne(final Measure left, final Measure right) {
        return this.compare(left, right) != 0;
    }

    @Override
    public final int compare(final Measure left, final Measure right) {
        if (left.isTop() && right.isTop()) {
            return 0;
        } else if (left.isTop()) {
            return 1;
        } else if (right.isTop()) {
            return -1;
        }

        int[] leftComponents = this.getComponents(left, this.length);
        int[] rightComponents = this.getComponents(right, this.length);

        for (int i = 0; i < length; i++) {
            int comparison = Integer.compare(leftComponents[i], rightComponents[i]);
            if (comparison != 0) {
                return comparison;
            }
        }

        return 0;
    }

    private int[] getComponents(final Measure measure, final int length) {
        int[] components = measure.getComponents();
        if (components.length != length) {
            components = Arrays.copyOf(components, length);
        }

        return components;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || super.getClass() != other.getClass()) {
            return false;
        }
        MeasureComparator that = (MeasureComparator) other;

        return this.length == that.length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.length);
    }

    @Override
    public String toString() {
        return "MeasureComparator{" +
            "length=" + this.length +
            '}';
    }
}