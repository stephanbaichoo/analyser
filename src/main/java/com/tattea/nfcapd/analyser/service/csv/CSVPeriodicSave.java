package com.tattea.nfcapd.analyser.service.csv;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class CSVPeriodicSave {

    private final CSVFileReader csvFileReader;

    private final NetflowService netflowService;

    private static final String csvDirectory = "C:\\Users\\steph\\Documents\\Projects\\analyser\\src\\main\\resources\\static";

    private static final String CSV = "csv";

    @Autowired
    public CSVPeriodicSave(CSVFileReader csvFileReader, NetflowService netflowService) {
        this.csvFileReader = csvFileReader;
        this.netflowService = netflowService;
    }

    @Scheduled(cron = "*/30 * * * * *")
    public void triggerAllCSVSave() {
        log.info("CSV Save at : {}", LocalDateTime.now().toString());
        getAllCSVFromDirectory().stream()
                .map(File::getAbsolutePath)
                .map(csvFileReader::getNetflows)
                .forEach(netflowService::saveNetflows);
    }

    public List<File> getAllCSVFromDirectory() {
        return Stream.of(csvDirectory)
                .map(File::new)
                .map(File::listFiles)
                .filter(Objects::nonNull)
                .flatMap(Arrays::stream)
                .filter(File::isFile)
                .filter(file -> FilenameUtils.getExtension(file.getName()).equals(CSV))
                .collect(Collectors.toList());
    }

}