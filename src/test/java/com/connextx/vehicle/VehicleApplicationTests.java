package com.connextx.vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.connextx.vehicle.insurance.Services.InsuranceService;

@SpringBootTest
class VehicleInsuranceTests {

  @Test
  void testGetAgeFactor() {
    // Arrange
    InsuranceService insuranceService = new InsuranceService();
    
    // Act and Assert
    assertEquals(1.2, insuranceService.getAgeFactor(18));
    assertEquals(1.0, insuranceService.getAgeFactor(35));
    assertEquals(0.85, insuranceService.getAgeFactor(65));
  }

	// Additional tests for helper methods

	@Test
	void testGetClaimsFactor() {

	InsuranceService insuranceService = new InsuranceService();

	assertEquals(0.8, insuranceService.getClaimsFactor(0));
	assertEquals(0.9, insuranceService.getClaimsFactor(1));
	assertEquals(1.1, insuranceService.getClaimsFactor(3));
	}

	@Test 
	void testGetDriverRecordFactor() {
	
	InsuranceService insuranceService = new InsuranceService();

	assertEquals(0.9, insuranceService.getDriverRecordFactor(0));
	assertEquals(1.1, insuranceService.getDriverRecordFactor(2));
	assertEquals(1.3, insuranceService.getDriverRecordFactor(4));
	}

	@Test
	void testGetCarValueFactor() {

	InsuranceService insuranceService = new InsuranceService();

	assertEquals(0.8, insuranceService.getCarValueFactor(10000, 5));
	assertEquals(0.9, insuranceService.getCarValueFactor(50000, 3));
	assertEquals(1.1, insuranceService.getCarValueFactor(150000, 1)); 
	}

	@Test 
	void testGetMileageFactor() {

	InsuranceService insuranceService = new InsuranceService();

	assertEquals(0.8, insuranceService.getMileageFactor(10000));
	assertEquals(0.9, insuranceService.getMileageFactor(30000));
	assertEquals(1.1, insuranceService.getMileageFactor(70000));
	}

}