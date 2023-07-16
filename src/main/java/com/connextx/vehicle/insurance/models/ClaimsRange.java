package com.connextx.vehicle.insurance.models;

public enum ClaimsRange {
    UNDER_1(0.9),
    UNDER_2(1.2),
    BETWEEN_2_AND_3(1.5),
    OVER_3(0.0);

    private final double factor;

    ClaimsRange(double factor) {
        this.factor = factor;
    }

    public double getFactor() {
        return factor;
    }
}