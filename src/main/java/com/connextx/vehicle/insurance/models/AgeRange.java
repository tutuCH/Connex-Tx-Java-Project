package com.connextx.vehicle.insurance.models;

public enum AgeRange {
    UNDER_25(1.3),
    BETWEEN_25_AND_40(1.0),
    BETWEEN_40_AND_70(0.9),
    OVER_70(0.0);

    private final double factor;

    AgeRange(double factor) {
        this.factor = factor;
    }

    public double getFactor() {
        return factor;
    }
}