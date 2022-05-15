package com.tattea.nfcapd.analyser;

import com.tattea.nfcapd.analyser.service.csv.CSVFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnalyserApplication implements CommandLineRunner {

    private final CSVFileReader csvReader;

    @Autowired
    public AnalyserApplication(CSVFileReader csvReader) {
        this.csvReader = csvReader;
    }

    public static void main(String[] args) {
        SpringApplication.run(AnalyserApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        csvReader.getNetflows();
    }
}
