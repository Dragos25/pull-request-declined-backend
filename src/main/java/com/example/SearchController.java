package com.example;


import com.opencsv.exceptions.CsvValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/search")
@CrossOrigin
public class SearchController {
    CompanyHandler companyHandler = new CompanyHandler();
    @GetMapping("/{toSearch}")
    public ResponseEntity<ArrayList<String>> oneCompany(@PathVariable("toSearch") String toSearch) throws IOException, CsvValidationException {
        ArrayList<String> foundCompanies = companyHandler.searchCompany(toSearch);

        //if(!values.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aceasta companie nu exista in baza de date");
        if(foundCompanies.size()==0) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.ok(foundCompanies);
    }
}
