package com.tattea.nfcapd.analyser.service.csv;

import com.tattea.nfcapd.analyser.domain.Netflow;
import com.tattea.nfcapd.analyser.model.TrafficBytes;
import com.tattea.nfcapd.analyser.repository.NetflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<TrafficBytes> getBusyTraffic() {
       return netflowRepository.findAll()
                .stream()
                .map(netflow -> TrafficBytes.builder()
                        .ip(netflow.getSrcIp().concat("->").concat(netflow.getDstIp()))
                        .bytes(convertToBytes(netflow.getBytes()))
                        .build())
               .sorted(Comparator.comparing(TrafficBytes::getBytes).reversed())
               .limit(10)
               .collect(Collectors.toList());
    }

    private Long convertToBytes(String bytes) {
        if (bytes.contains("M")) {
            return (long) Double.parseDouble(bytes.replace("M", "")) * 100000;
        }
        return Long.parseLong(bytes);
    }
}
