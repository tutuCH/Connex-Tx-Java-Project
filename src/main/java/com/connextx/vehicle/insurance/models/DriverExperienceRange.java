package com.connextx.vehicle.insurance.models;

public enum DriverExperienceRange {
    UNDER_2(1.5),
    BETWEEN_2_AND_5(1.3),
    BETWEEN_5_AND_10(1.0),
    OVER_10(0.9),
    OTHER(0.0);

    private final double factor;

    DriverExperienceRange(double factor) {
        this.factor = factor;
    }

    public double getFactor() {
        return factor;
    }
}