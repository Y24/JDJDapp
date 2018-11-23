package com.Y24;

public class line {
    private int firstId;
    private int secondId;
    private int charge;

    public line(int firstId, int secondId, int charge) {
        this.firstId = firstId;
        this.secondId = secondId;
        this.charge = charge;
    }

    public int getCharge() {
        return charge;
    }

    public int getFirst() {
        return firstId;
    }

    public int getSecond() {
        return secondId;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public boolean equals(int first,int second) {
        return firstId == first && secondId == second || firstId == second && secondId == first;
    }
}
