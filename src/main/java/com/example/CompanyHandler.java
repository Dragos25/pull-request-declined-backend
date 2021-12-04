package com.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class CompanyHandler {
    public ArrayList<String> readAllCompanies() throws IOException {
        ArrayList<String> records = new ArrayList<String>();
        File currentDirFile = new File(".");
        String helper = currentDirFile.getAbsolutePath();

        System.out.println(helper);
        File d = new File("");
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("500COMPANII_CSV.csv");
            CSVReader reader = new CSVReader(new InputStreamReader(is));
            String[] nextLine;
            reader.readNext();
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

    public Optional<ArrayList<Coordinates>> getCompanyData(String company) throws IOException, CsvValidationException {

        ArrayList<Coordinates> asd =new ArrayList<Coordinates>();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(company+".csv");
        CSVReader reader = new CSVReader(new InputStreamReader(is));
        System.out.println(is.toString());
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
