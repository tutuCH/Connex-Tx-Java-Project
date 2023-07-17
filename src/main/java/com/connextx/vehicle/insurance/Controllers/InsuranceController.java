package com.connextx.vehicle.insurance.Controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connextx.vehicle.insurance.Services.InsuranceService;
import com.connextx.vehicle.insurance.models.Insurance;

@CrossOrigin(origins = {
    "http://localhost:4200", 
    "https://connex-tx-angular-project.vercel.app"
})
@RestController
@RequestMapping(path = "api/v1/insurance")

public class InsuranceController {

    private final InsuranceService insuranceService;

    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<Map<String, Object>> calculateInsurancePremium(@RequestBody Insurance insuranceRequest) {
        return insuranceService.calculatePremium(insuranceRequest);
    }
}
