package com.tattea.nfcapd.analyser.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author tattea
 */
@Getter
@Setter
@Builder
@ToString
@Table(name = "netflow", schema = "analyser")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Netflow {

    @Id
    @GeneratedValue
    @Column(name = "netflow_id")
    private Long id;

    @Column(name = "date_first_seen")
    private LocalDate dateFirstSeen;

    @Column(name = "time_first_seen")
    private LocalTime timeFirstSeen;

    @Column(name = "duration")
    private BigDecimal duration;

    @Column(name = "protocol")
    private String protocol;

    @Column(name = "src_ip")
    private String srcIp;

    @Column(name = "dst_ip")
    private String dstIp;

    @Column(name = "flags")
    private String flags;

    @Column(name = "tos")
    private Integer tos;

    @Column(name = "packet_no")
    private Integer packetNo;

    @Column(name = "bytes")
    private String bytes;

    @Column(name = "pps")
    private String pps;

    @Column(name = "bps")
    private String bps;

    @Column(name = "bpp")
    private Long Bpp;

    @Column(name = "flows")
    private String flows;

    enum Protocol {
        TCP,
        ICMP,
        UDP;
    }

    @Embeddable
    @Getter
    @Setter
    @Builder(toBuilder = true)
    @ToString
    public static class IP {

        @Column(name = "ip", insertable = false, updatable = false)
        private String ip;

        @Column(name = "port", insertable = false, updatable = false)
        private Integer port;
    }

}
