package com.etnetera.hr.repository.entity;

import com.etnetera.hr.dto.HypeLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class JavaScriptFrameworkVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "framework_version_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "framework_id")
    private JavaScriptFramework framework;

    @Column(nullable = false, length = 30)
    private String version;

    @Column
    private LocalDate deprecationDate;

    @Column(nullable = false)
    private HypeLevel hypeLevel;

}
