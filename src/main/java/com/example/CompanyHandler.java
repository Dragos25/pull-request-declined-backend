package com.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CompanyHandler {
    public static ArrayList<String> readAllCompanies() {
        ArrayList<String> records = new ArrayList<String>();
        try {

            CSVReader reader = new CSVReader(new FileReader("C:\\Users\\DRAGOS\\Desktop\\hackitall\\API forbes\\src\\main\\java\\500COMPANII_CSV.csv"));

            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {

                String name = nextLine[0];
                records.add(name);
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
        return records;
    }

    public static Optional<ArrayList<Coordinates>> getCompanyData(String company) throws IOException, CsvValidationException {
        File f = new File("src/main/resources/"+company+".csv");
        System.out.println(f.getAbsolutePath());
        if(!f.exists()) {
            return Optional.empty();
        }

        ArrayList<Coordinates> asd =new ArrayList<Coordinates>();
        CSVReader reader = new CSVReader(new FileReader(f));

        String[] nextLine;
        reader.readNext();
        try{
        while ((nextLine = reader.readNext()) != null) {
            Coordinates temp = new Coordinates();
            String x = nextLine[0];
            Float y = Float.parseFloat(nextLine[1]);
            temp.setX(x);
            temp.setY(y);
            asd.add(temp);
        }}
        catch(Exception error){
        }


        return Optional.of(asd);

    }
}
