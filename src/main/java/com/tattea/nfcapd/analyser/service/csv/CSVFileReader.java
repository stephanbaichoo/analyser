package com.tattea.nfcapd.analyser.service.csv;

import com.tattea.nfcapd.analyser.domain.Netflow;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tattea
 */
@Service
public class CSVFileReader {

    private static final String csvPath = "C:\\Users\\steph\\Documents\\Projects\\analyser\\src\\main\\resources\\static\\nfcapd.202205020100.csv";

    @Autowired
    public CSVFileReader() {
    }

    public List<Netflow> getNetflows() {
        List<List<String>> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.trim().split(" ");
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return records.stream()
                .skip(1) // skip header
                .filter(strings -> !startWithIllegalWords(String.join("", strings)))
                .map(strings -> strings.stream()
                        .filter(t -> !StringUtils.isEmpty(t))
                        .filter(t -> !t.contains("->"))
                        .collect(Collectors.toList()))
                .map(this::buildNetflow)
                .collect(Collectors.toList());
    }

    private Netflow buildNetflow(List<String> csvRow) {
        return Netflow.builder()
                .dateFirstSeen(LocalDate.parse(csvRow.get(0)))
                .timeFirstSeen(LocalTime.parse(csvRow.get(1)))
                .duration(new BigDecimal(csvRow.get(2)))
                .protocol(csvRow.get(3))
                .srcIp(Netflow.IP.builder()
                        .ip(getIp(csvRow.get(4)))
                        .port(getPort(csvRow.get(4)))
                        .build())
                .dstIp(Netflow.IP.builder()
                        .ip(getIp(csvRow.get(5)))
                        .port(getPort(csvRow.get(5)))
                        .build())
                .flags(csvRow.get(6))
                .tos(Integer.valueOf(csvRow.get(7)))
                .packetNo(Integer.valueOf(csvRow.get(8)))
                .bytes(new BigDecimal(csvRow.get(9)))
                .pps(csvRow.get(10))
                .bps(new BigDecimal(csvRow.get(11)))
                .Bpp(csvRow.get(12))
                .flows(Integer.valueOf(csvRow.get(13)))
                .build();
    }

    private boolean startWithIllegalWords(String s) {
        return List.of("Summary", "Time", "Total", "Sys").stream().anyMatch(s::startsWith);
    }

    private String getIp(String s) {
        return s.split(":")[0];
    }

    private Integer getPort(String s) {
        return Double.valueOf(s.split(":")[1]).intValue();
    }

}
