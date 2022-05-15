package com.tattea.nfcapd.analyser.service.csv;

import com.tattea.nfcapd.analyser.domain.Netflow;
import com.tattea.nfcapd.analyser.repository.NetflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NetflowService {

    private NetflowRepository netflowRepository;

    @Autowired
    public NetflowService(NetflowRepository netflowRepository) {
        this.netflowRepository = netflowRepository;
    }

    public void saveNetflows(List<Netflow> netflows) {
        netflows.forEach(netflowRepository::save);
    }

    public void saveNetflow(Netflow netflow) {
        netflowRepository.save(netflow);
    }
}
