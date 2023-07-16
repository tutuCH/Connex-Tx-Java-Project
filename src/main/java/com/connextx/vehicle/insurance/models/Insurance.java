package com.connextx.vehicle.insurance.models;

public class Insurance {
    private int age;
    private int drivingExperience;
    private int driverRecord;
    private int claims;
    private int carValue;
    private int annualMileage;
    private int insuranceHistory;
    private int carAge;

    // Default constructor is needed for deserialization
    public Insurance() {}

    // Constructor with all fields
    public Insurance(int age, int drivingExperience, int driverRecord, int claims, int carValue, int annualMileage, int insuranceHistory, int carAge) {

        this.age = age;
        this.drivingExperience = drivingExperience;
        this.driverRecord = driverRecord;
        this.claims = claims;
        this.carValue = carValue;
        this.annualMileage = annualMileage;
        this.insuranceHistory = insuranceHistory;
        this.carAge = carAge;
    }

    // Getters and setters
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDrivingExperience() {
        return drivingExperience;
    }

    public void setDrivingExperience(int drivingExperience) {
        this.drivingExperience = drivingExperience;
    }

    public int getDriverRecord() {
        return driverRecord;
    }

    public void setDriverRecord(int driverRecord) {
        this.driverRecord = driverRecord;
    }

    public int getClaims() {
        return claims;
    }

    public void setClaims(int claims) {
        this.claims = claims;
    }

    public int getCarValue() {
        return carValue;
    }

    public void setCarValue(int carValue) {
        this.carValue = carValue;
    }

    public int getAnnualMileage() {
        return annualMileage;
    }

    public void setAnnualMileage(int annualMileage) {
        this.annualMileage = annualMileage;
    }

    public int getInsuranceHistory() {
        return insuranceHistory;
    }

    public void setInsuranceHistory(int insuranceHistory) {
        this.insuranceHistory = insuranceHistory;
    }

    public int getCarAge() {
        return carAge;
    }

    public void setCarAge(int carAge) {
        this.carAge = carAge;
    }

    @Override
    public String toString() {
      return "Insurance [age=" + age + ", drivingExperience=" + drivingExperience + ", driverRecord=" + driverRecord
          + ", claims=" + claims + ", carValue=" + carValue + ", annualMileage=" + annualMileage + ", insuranceHistory="
          + insuranceHistory + ", carAge="
          + carAge + "]";
    }

    
}