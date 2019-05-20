package com.etnetera.hr.service;

import com.etnetera.hr.dto.JavaScriptFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JavaScriptFrameworkService {

    @Autowired
    private JavaScriptFrameworkGateway gateway;

    public List<JavaScriptFramework> getAllFrameworks() {
        return gateway.getAllFrameworks();
    }

    public List<JavaScriptFramework> search(String by){
        return gateway.search(by);
    }

    public void deleteFramework(Long frameworkId){
        if(frameworkExists(frameworkId))
            gateway.deleteFramework(frameworkId);

    }

    public void updateFramework(JavaScriptFramework framework){
        if(frameworkExists(framework.getId()))
            gateway.updateFramework(framework);
    }

    public void createFramework(JavaScriptFramework framework){
        if(frameworkDoesNotExist(framework))
            gateway.createFramework(framework);
    }

    private boolean frameworkDoesNotExist(JavaScriptFramework framework) {
        return  framework.getName() != null && search(framework.getName()).isEmpty() && framework.getId() == null;
    }

    private boolean frameworkExists(Long frameworkId) {
        return frameworkId != null && !search(String.valueOf(frameworkId)).isEmpty();
    }

}
