package com.connextx.vehicle.insurance.models;

public enum CarValueRange {
    UNDER_30k(0.8),
    BETWEEN_30k_AND_60k(1),
    BETWEEN_60k_AND_100k(1.2),
    BETWEEN_100k_AND_150k(1.5),
    BETWEEN_150k_AND_200k(1.2),
    OVER_200k(0.0);

    private final double factor;

    CarValueRange(double factor) {
        this.factor = factor;
    }

    public double getFactor() {
        return factor;
    }
}