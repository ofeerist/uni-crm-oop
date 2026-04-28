package PartB.model;

import java.util.Objects;

import PartB.enums.MarkType;

public class Mark {

    private final int markId;
    private double value;
    private MarkType markType;

    public Mark(int markId, double value, MarkType markType) {
        this.markId = markId;
        this.value = value;
        this.markType = markType;
    }

    public int getMarkId() {
        return markId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public MarkType getMarkType() {
        return markType;
    }

    public void setMarkType(MarkType markType) {
        this.markType = markType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mark)) return false;
        Mark mark = (Mark) o;
        return markId == mark.markId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(markId);
    }

    @Override
    public String toString() {
        return "Mark{" +
                "markId=" + markId +
                ", value=" + value +
                ", markType=" + markType +
                '}';
    }
}