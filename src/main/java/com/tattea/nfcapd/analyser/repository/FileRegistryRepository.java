package com.tattea.nfcapd.analyser.repository;

import com.tattea.nfcapd.analyser.domain.FileRegistry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRegistryRepository extends JpaRepository<FileRegistry, Long> {
}
