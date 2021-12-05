package com.example;


import com.opencsv.exceptions.CsvValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/predict")
public class PredictionController {
    CompanyHandler companyHandler = new CompanyHandler();
    @GetMapping("/{companyid}")
    public ResponseEntity<Float> oneCompany(@PathVariable("companyid") String companyid) throws IOException, CsvValidationException {
        System.out.println(companyid);
        Optional<ArrayList<Coordinates>> values =  companyHandler.getCompanyData(companyid);


        if(!values.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        ArrayList<Coordinates> allCoords = values.get();
        float value = companyHandler.linearPrediction(allCoords);
        System.out.println(value);
        return ResponseEntity.ok(value);
    }
}
