package com.tattea.nfcapd.analyser.model;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TrafficBytes {

    private String ip;

    private Long bytes;

}
