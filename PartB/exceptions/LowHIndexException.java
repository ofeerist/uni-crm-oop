package PartB.exceptions;

public class LowHIndexException extends Exception {

    private final int hIndex;

    public LowHIndexException(int hIndex) {
        super("Supervisor h-index must be at least 3, but was: " + hIndex);
        this.hIndex = hIndex;
    }

    public int getHIndex() {
        return hIndex;
    }
}