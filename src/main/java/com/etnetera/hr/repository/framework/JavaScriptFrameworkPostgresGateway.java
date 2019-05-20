package com.etnetera.hr.repository.framework;

import com.etnetera.hr.dto.JavaScriptFramework;
import com.etnetera.hr.service.JavaScriptFrameworkGateway;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JavaScriptFrameworkPostgresGateway implements JavaScriptFrameworkGateway {

    @Autowired
    private JavaScriptFrameworkRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<JavaScriptFramework> getAllFrameworks() {
        return toDto(repository.findAll());
    }

    @Override
    public void deleteFramework(long frameworkId) {
        repository.deleteById(frameworkId);
    }

    @Override
    public void updateFramework(JavaScriptFramework framework) {
        repository.save(modelMapper.map(framework, com.etnetera.hr.repository.entity.JavaScriptFramework.class));
    }

    @Override
    public void createFramework(JavaScriptFramework framework) {
        repository.save(modelMapper.map(framework, com.etnetera.hr.repository.entity.JavaScriptFramework.class));
    }

    @Override
    public List<JavaScriptFramework> search(String by) {
        return toDto(repository.search(by));
    }

    private List<JavaScriptFramework> toDto(List<com.etnetera.hr.repository.entity.JavaScriptFramework> entities){
       return entities.stream()
                .map(javaScriptFramework -> modelMapper.map(javaScriptFramework,JavaScriptFramework.class))
                .collect(Collectors.toList());
    }

}
