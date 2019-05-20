package com.etnetera.hr.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@EqualsAndHashCode
public class JavaScriptFramework {

    private Long id;
    private String name;
    private List<JavaScriptFrameworkVersion> versions;

    @Override
    public String toString() {
        return "JavaScriptFramework [id=" + id + ", name=" + name + "]";
    }
}
