package com.tattea.nfcapd.analyser.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author tattea
 */
@Getter
@Setter
@Builder
@ToString
@Table(name = "file_registry", schema = "analyser")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FileRegistry {

    @Id
    @GeneratedValue
    @Column(name = "netflow_id")
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "time_saved")
    private LocalDateTime timeSaved;
}
