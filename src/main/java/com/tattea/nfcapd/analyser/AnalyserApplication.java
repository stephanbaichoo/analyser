package com.tattea.nfcapd.analyser;

import com.tattea.nfcapd.analyser.service.csv.CSVFileReader;
import com.tattea.nfcapd.analyser.service.csv.NetflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnalyserApplication implements CommandLineRunner {

    private final CSVFileReader csvReader;

    private final NetflowService netflowService;

    @Autowired
    public AnalyserApplication(CSVFileReader csvReader, NetflowService netflowService) {
        this.csvReader = csvReader;
        this.netflowService = netflowService;
    }

    public static void main(String[] args) {
        SpringApplication.run(AnalyserApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //csvReader.getNetflows().forEach(System.out::println);

        netflowService.saveNetflows(csvReader.getNetflows());
    }
}
