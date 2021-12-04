package com.example;

import com.opencsv.exceptions.CsvValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @GetMapping("")
    public ResponseEntity<ArrayList<String>> index() {
        ArrayList<String> allCompanies = CompanyHandler.readAllCompanies();
        return ResponseEntity.ok((ArrayList<String>) allCompanies
                .stream()
                .collect(Collectors.toList()));
    }

    @GetMapping("/{companyid}")
    public ResponseEntity<ArrayList<Coordinates>> oneCompany(@PathVariable("companyid") String companyid) throws IOException, CsvValidationException {
        System.out.println(companyid);
        Optional<ArrayList<Coordinates>> values =  CompanyHandler.getCompanyData(companyid);

        //if(!values.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aceasta companie nu exista in baza de date");
        if(!values.isPresent()) return (ResponseEntity<ArrayList<Coordinates>>) ResponseEntity.notFound();
        ArrayList<Coordinates> allCoords = values.get();
        System.out.println(allCoords.get(0).getX());
        return ResponseEntity.ok(allCoords);
    }

}