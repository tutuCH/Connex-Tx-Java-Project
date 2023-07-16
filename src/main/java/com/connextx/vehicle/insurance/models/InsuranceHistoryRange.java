package com.connextx.vehicle.insurance.models;

public enum InsuranceHistoryRange {
    UNDER_1(1.2),
    UNDER_2(1.1),
    OVER_2(1);

    private final double factor;

    InsuranceHistoryRange(double factor) {
        this.factor = factor;
    }

    public double getFactor() {
        return factor;
    }
}