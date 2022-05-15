package com.tattea.nfcapd.analyser.rest;

import com.tattea.nfcapd.analyser.model.TrafficBytes;
import com.tattea.nfcapd.analyser.service.csv.NetflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NetflowController {

    private final NetflowService netflowService;

    @Autowired
    public NetflowController(NetflowService netflowService) {
        this.netflowService = netflowService;
    }

    @GetMapping("/busyTraffic")
    public List<TrafficBytes> getBusyTraffic() {
        return netflowService.getBusyTraffic();
    }

}
