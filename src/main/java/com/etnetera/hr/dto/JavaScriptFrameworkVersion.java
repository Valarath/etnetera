package com.etnetera.hr.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class JavaScriptFrameworkVersion {

    private Long id;
    private JavaScriptFramework framework;
    private String version;
    private LocalDate deprecationDate;
    private HypeLevel hypeLevel;

}
