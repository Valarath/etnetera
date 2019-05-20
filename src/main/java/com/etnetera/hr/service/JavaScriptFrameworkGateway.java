package com.etnetera.hr.service;

import com.etnetera.hr.dto.JavaScriptFramework;

import java.util.List;

public interface JavaScriptFrameworkGateway {

    List<JavaScriptFramework> getAllFrameworks();

    void deleteFramework(long frameworkId);

    void updateFramework(JavaScriptFramework framework);

    void createFramework(JavaScriptFramework framework);

    List<JavaScriptFramework> search(String by);
}
