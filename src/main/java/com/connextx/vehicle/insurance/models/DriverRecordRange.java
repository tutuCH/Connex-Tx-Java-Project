package com.connextx.vehicle.insurance.models;

public enum DriverRecordRange {
    UNDER_1(1.0),
    UNDER_2(1.1),
    BETWEEN_2_AND_3(1.3),
    OVER_3(0.0);

    private final double factor;

    DriverRecordRange(double factor) {
        this.factor = factor;
    }

    public double getFactor() {
        return factor;
    }
}