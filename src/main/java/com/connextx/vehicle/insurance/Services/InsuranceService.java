package com.connextx.vehicle.insurance.Services;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.connextx.vehicle.insurance.models.AgeRange;
import com.connextx.vehicle.insurance.models.DriverExperienceRange;
import com.connextx.vehicle.insurance.models.DriverRecordRange;
import com.connextx.vehicle.insurance.models.Insurance;
import com.connextx.vehicle.insurance.models.ClaimsRange;
import com.connextx.vehicle.insurance.models.CarValueRange;
import com.connextx.vehicle.insurance.models.MileageRange;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.connextx.vehicle.insurance.models.InsuranceHistoryRange;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class InsuranceService {
    public ResponseEntity<Map<String, Object>> calculatePremium(Insurance insuranceRequest) {
        try {
            double premium = 
                getBasePremium() * 
                getAgeFactor(insuranceRequest.getAge()) *
                getCarValueFactor(insuranceRequest.getCarValue(), insuranceRequest.getCarAge()) *
                getClaimsFactor(insuranceRequest.getClaims()) * 
                getDrivingExperienceFactor(insuranceRequest.getDrivingExperience()) * 
                getDriverRecordFactor(insuranceRequest.getDriverRecord()) *
                getInsuranceHistoryFactor(insuranceRequest.getInsuranceHistory()) * 
                getMileageFactor(insuranceRequest.getAnnualMileage());
            String quoteReference = getQuoteReference();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("premium", premium);
            response.put("quote_reference", quoteReference);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Handle any errors that occur during the calculation
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    
    public double getAgeFactor(double age) {

        AgeRange ageRange;

        if (age < 25) {
            ageRange = AgeRange.UNDER_25;
        } else if (age < 40) {
            ageRange = AgeRange.BETWEEN_25_AND_40;
        } else if (age < 70) {
            ageRange = AgeRange.BETWEEN_40_AND_70;
        } else {
            ageRange = AgeRange.OVER_70;
        }

        return ageRange.getFactor();
    }

    public double getDrivingExperienceFactor(double drivingExperience) {
        DriverExperienceRange driverExperience;

        if (drivingExperience < 2) {
            driverExperience = DriverExperienceRange.UNDER_2;
        } else if (drivingExperience < 5) {
            driverExperience = DriverExperienceRange.BETWEEN_2_AND_5;
        } else if (drivingExperience < 10) {
            driverExperience = DriverExperienceRange.BETWEEN_5_AND_10;
        } else {
            driverExperience = DriverExperienceRange.OVER_10;
        }
        
        return driverExperience.getFactor();
    }

    public double getDriverRecordFactor(int driverRecord) {
        DriverRecordRange driverRecordRange;

        if (driverRecord < 1) {
            driverRecordRange = DriverRecordRange.UNDER_1;
        } else if (driverRecord < 2) {
            driverRecordRange = DriverRecordRange.UNDER_2;
        } else if (driverRecord < 3) {
            driverRecordRange = DriverRecordRange.BETWEEN_2_AND_3;
        } else {
            driverRecordRange = DriverRecordRange.OVER_3;
        }
        
        return driverRecordRange.getFactor();
    }

    public double getClaimsFactor(int claims) {
        ClaimsRange claimsRange;

        if (claims < 1) {
            claimsRange = ClaimsRange.UNDER_1;
        } else if (claims < 2) {
            claimsRange = ClaimsRange.UNDER_2;
        } else if (claims < 3) {
            claimsRange = ClaimsRange.BETWEEN_2_AND_3;
        } else {
            claimsRange = ClaimsRange.OVER_3;
        }
        return claimsRange.getFactor();
    }

    public double getCarValueFactor(double carValue, int carAge) {
        carValue = getDeprecatedCarValue(carValue, carAge);
        CarValueRange carValueRange;
        if (carValue < 30000) {
            carValueRange = CarValueRange.UNDER_30k;
        } else if (carValue < 60000) {
            carValueRange = CarValueRange.BETWEEN_30k_AND_60k;
        } else if (carValue < 100000) {
            carValueRange = CarValueRange.BETWEEN_60k_AND_100k;
        } else if (carValue < 150000) {
            carValueRange = CarValueRange.BETWEEN_100k_AND_150k;
        } else if (carValue < 200000) {
            carValueRange = CarValueRange.BETWEEN_150k_AND_200k;
        } else {
            carValueRange = CarValueRange.OVER_200k;
        }
        return carValueRange.getFactor();
    }

    public double getMileageFactor(double mileage) {
        MileageRange mileageRange;

        if (mileage < 20000) {
            mileageRange = MileageRange.UNDER_20k;
        } else if (mileage < 30000) {
            mileageRange = MileageRange.BETWEEN_20k_AND_30k;
        } else if (mileage < 50000) {
            mileageRange = MileageRange.BETWEEN_30k_AND_50k;
        } else {
            mileageRange = MileageRange.OVER_50k;
        }
        return mileageRange.getFactor();
    }

    public double getInsuranceHistoryFactor(int insuranceHistory) {
        InsuranceHistoryRange insuranceHistoryRange;

        if (insuranceHistory < 1) {
            insuranceHistoryRange = InsuranceHistoryRange.UNDER_1;
        } else if (insuranceHistory < 2) {
            insuranceHistoryRange = InsuranceHistoryRange.UNDER_2;
        } else {
            insuranceHistoryRange = InsuranceHistoryRange.OVER_2;
        }
        return insuranceHistoryRange.getFactor();
    }     
    
    public double getBasePremium() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://storage.googleapis.com/connex-th/insurance_assignment/base_premium.json";
            String jsonStr = restTemplate.getForObject(url, String.class);

            // Process the JSON string to extract the base_premium value
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonStr);
            double basePremium = rootNode.get("base_premium").asDouble();
            return basePremium;
        } catch (Exception e) {
            // Handle any errors that occur during the request or JSON processing
            System.err.println("Error fetching base premium: " + e.getMessage());
            return 1500.00; // return 1500 as default value
        }
    }

    public static String getQuoteReference() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();
        String firstChar = Character.toString(uuidStr.charAt(0));
        String remainingChars = uuidStr.substring(1).replaceAll("-", ""); 
        String newUuidStr = firstChar + remainingChars.substring(0, 9);
        return newUuidStr;
    }

    public double getDeprecatedCarValue(double purchasePrice, int carAge) {
        double depreciationRate;
        if (carAge <= 3) {
            depreciationRate = 0.15;
        } else {
            depreciationRate = 0.1;
        }
        double depreciationFactor = Math.pow(1 - depreciationRate, carAge);
        return purchasePrice * depreciationFactor;
    }
}
