package com.tattea.nfcapd.analyser.repository;

import com.tattea.nfcapd.analyser.domain.Netflow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetflowRepository extends JpaRepository<Netflow, Long> {
}
