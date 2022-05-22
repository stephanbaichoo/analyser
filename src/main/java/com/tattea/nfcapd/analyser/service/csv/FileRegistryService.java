package com.tattea.nfcapd.analyser.service.csv;

import com.tattea.nfcapd.analyser.domain.FileRegistry;
import com.tattea.nfcapd.analyser.domain.Netflow;
import com.tattea.nfcapd.analyser.repository.FileRegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileRegistryService {

    private FileRegistryRepository registryRepository;

    @Autowired
    public FileRegistryService(FileRegistryRepository registryRepository) {
        this.registryRepository = registryRepository;
    }

    public void saveFileRegistry(FileRegistry fileRegistry) {
        registryRepository.save(fileRegistry);
    }

    public boolean isFileAlreadyProcessed(String fileName) {
        return registryRepository.findAll()
                .stream()
                .map(FileRegistry::getFileName)
                .anyMatch(fN -> fN.equals(fileName));
    }
}
