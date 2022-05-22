package com.tattea.nfcapd.analyser.service.csv;

import com.tattea.nfcapd.analyser.domain.FileRegistry;
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

    private final FileRegistryService fileRegistryService;

    private static final String csvDirectory = "/home/tattea/test";

    private static final String CSV = "csv";

    @Autowired
    public CSVPeriodicSave(CSVFileReader csvFileReader, NetflowService netflowService, FileRegistryService fileRegistryService) {
        this.csvFileReader = csvFileReader;
        this.netflowService = netflowService;
        this.fileRegistryService = fileRegistryService;
    }

    @Scheduled(cron = "*/20 * * * * *")
    public void triggerAllCSVSave() {
        log.info("CSV Save at : {}", LocalDateTime.now().toString());

        getAllUnsavedCSVFromDirectory().stream()
                .map(File::getAbsolutePath)
                .map(csvFileReader::getNetflows)
                .forEach(netflowService::saveNetflows);

        getAllUnsavedCSVFromDirectory().stream()
                .map(file -> FileRegistry.builder()
                        .fileName(file.getName())
                        .timeSaved(LocalDateTime.now())
                        .build())
                .forEach(fileRegistryService::saveFileRegistry);
    }

    public List<File> getAllUnsavedCSVFromDirectory() {
        return Stream.of(csvDirectory)
                .map(File::new)
                .map(File::listFiles)
                .filter(Objects::nonNull)
                .flatMap(Arrays::stream)
                .filter(File::isFile)
                .filter(file -> FilenameUtils.getExtension(file.getName()).equals(CSV))
                .filter(file -> !fileRegistryService.isFileAlreadyProcessed(file.getName()))
                .collect(Collectors.toList());
    }

}
