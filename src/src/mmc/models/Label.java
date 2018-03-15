package mmc.models;

import java.util.Objects;

public class Label {
    private final String text;

    public Label(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Label label1 = (Label) o;
        return Objects.equals(this.text, label1.text);
    }

    @Override
    public int hashCode() {

        return Objects.hash(this.text);
    }

    @Override
    public String toString() {
        return "Label{" +
            "label='" + this.text + '\'' +
            '}';
    }
}
