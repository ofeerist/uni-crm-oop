package PartB.model;

import PartB.enums.MarkType;

public class Mark {

    private double value;
    private MarkType markType;

    public Mark() {
    }

    public Mark(double value, MarkType markType) {
        this.value = value;
        this.markType = markType;
    }

    public double calculateTotal() {
        return value;
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
    public String toString() {
        return "Mark{" +
                "value=" + value +
                ", markType=" + markType +
                '}';
    }
}