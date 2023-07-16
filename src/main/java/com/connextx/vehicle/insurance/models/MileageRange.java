package com.connextx.vehicle.insurance.models;

public enum MileageRange {
    UNDER_20k(0.9),
    BETWEEN_20k_AND_30k(1),
    BETWEEN_30k_AND_50k(1.1),
    OVER_50k(1.3);

    private final double factor;

    MileageRange(double factor) {
        this.factor = factor;
    }

    public double getFactor() {
        return factor;
    }
}