package com.example;

import com.opencsv.exceptions.CsvValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
@CrossOrigin
public class CompanyController {
    CompanyHandler companyHandler = new CompanyHandler();

    @GetMapping("")
    public ResponseEntity<ArrayList<String>> index() throws IOException {
        ArrayList<String> allCompanies = companyHandler.readAllCompanies();
        return ResponseEntity.ok((ArrayList<String>) allCompanies
                .stream()
                .collect(Collectors.toList()));
    }

    @GetMapping("/{companyid}")
    public ResponseEntity<ArrayList<Coordinates>> oneCompany(@PathVariable("companyid") String companyid) throws IOException, CsvValidationException {
        System.out.println(companyid);
        Optional<ArrayList<Coordinates>> values =  companyHandler.getCompanyData(companyid);

        //if(!values.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aceasta companie nu exista in baza de date");
        if(!values.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        ArrayList<Coordinates> allCoords = values.get();
        System.out.println(allCoords.get(0).getX());
        return ResponseEntity.ok(allCoords);
    }

}