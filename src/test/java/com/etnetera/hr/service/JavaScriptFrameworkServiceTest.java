package com.etnetera.hr.service;

import com.etnetera.hr.dto.HypeLevel;
import com.etnetera.hr.dto.JavaScriptFramework;
import com.etnetera.hr.dto.JavaScriptFrameworkVersion;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaScriptFrameworkServiceTest {

    private static final long FRAMEWORK_ID = 999999;
    private static final String FRAMEWORK_NAME = "framework";
    private static final String FRAMEWORK_VERSION = "alpha";
    private static final long FRAMEWORK_VERSION_ID = 1;
    private static final String SEARCH_BY = "by";

    @InjectMocks
    private JavaScriptFrameworkService service;

    @Mock
    private JavaScriptFrameworkGateway gateway;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllFrameworks() {
        List<JavaScriptFramework> frameworks = getFrameworksForTest();
        getAllFrameworks_prepareEnvironment(frameworks);
        List<JavaScriptFramework> receivedFrameworks = service.getAllFrameworks();
        performAssertion(frameworks, receivedFrameworks);
    }

    private void getAllFrameworks_prepareEnvironment(List<JavaScriptFramework> frameworks) {
        when(gateway.getAllFrameworks()).thenReturn(frameworks);
    }

    @Test
    public void deleteFramework_frameworkExists() {
        List<JavaScriptFramework> frameworks = getFrameworksForTest();
        deleteFramework_frameworkExists_prepareEnvironment(frameworks);
        service.deleteFramework(frameworks.get(0).getId());
    }

    private void deleteFramework_frameworkExists_prepareEnvironment(List<JavaScriptFramework> frameworks) {
        frameworkExists_prepareEnvironment(frameworks);
        doNothing().when(gateway).deleteFramework(frameworks.get(0).getId());
    }

    @Test
    public void deleteFramework_frameworkDoesNotExists() {
        List<JavaScriptFramework> frameworks = getFrameworksForTest();
        frameworkDoesNotExists_prepareEnvironment(frameworks.get(0).getId());
        service.deleteFramework(frameworks.get(0).getId());
    }


    @Test
    public void updateFramework_frameworkExists() {
        List<JavaScriptFramework> frameworks = getFrameworksForTest();
        updateFramework_frameworkExists_prepareEnvironment(frameworks);
        service.updateFramework(frameworks.get(0));
    }

    private void updateFramework_frameworkExists_prepareEnvironment(List<JavaScriptFramework> frameworks) {
        frameworkExists_prepareEnvironment(frameworks);
        doNothing().when(gateway).updateFramework(frameworks.get(0));
    }



    @Test
    public void updateFramework_frameworkDoesNotExists() {
        List<JavaScriptFramework> frameworks = getFrameworksForTest();
        frameworkDoesNotExists_prepareEnvironment(frameworks.get(0).getId());
        service.updateFramework(frameworks.get(0));
    }


    @Test
    public void createFramework_frameworkDoesNotExists() {
        JavaScriptFramework framework = getFrameworkForTest().setId(null);
        createFramework_frameworkDoesNotExists_prepareEnvironment(framework);
        service.createFramework(framework);
    }

    private void createFramework_frameworkDoesNotExists_prepareEnvironment(JavaScriptFramework framework) {
        frameworkDoesNotExists_prepareEnvironment(framework.getId());
        doNothing().when(gateway).createFramework(framework);
    }

    @Test
    public void createFramework_frameworkAlreadyExists() {
        List<JavaScriptFramework> frameworks = getFrameworksForTest();
        frameworkExists_prepareEnvironment(frameworks);
        service.createFramework(frameworks.get(0));
    }

    @Test
    public void search() {
        List<JavaScriptFramework> frameworks = getFrameworksForTest();
        search_prepareEnvironment(frameworks, SEARCH_BY);
        List<JavaScriptFramework> receivedFrameworks = service.search(SEARCH_BY);
        performAssertion(frameworks, receivedFrameworks);
    }

    private void search_prepareEnvironment(List<JavaScriptFramework> frameworks, String by){
        when(gateway.search(by)).thenReturn(frameworks);
    }

    private void frameworkDoesNotExists_prepareEnvironment(Long frameworkId) {
        when(gateway.search(String.valueOf(frameworkId))).thenReturn(new ArrayList<>());
    }

    private void frameworkExists_prepareEnvironment(List<JavaScriptFramework> frameworks) {
        when(gateway.search(String.valueOf(frameworks.get(0).getId()))).thenReturn(frameworks);
    }

    private void performAssertion(List<JavaScriptFramework> frameworks, List<JavaScriptFramework> receivedFrameworks) {
        assertNotNull(receivedFrameworks);
        assertFalse(receivedFrameworks.isEmpty());
        assertThat(receivedFrameworks,is(frameworks));
    }

    private List<JavaScriptFramework> getFrameworksForTest(){
        return Collections.singletonList(getFrameworkForTest());
    }

    private JavaScriptFramework getFrameworkForTest(){
        return new JavaScriptFramework()
                .setId(FRAMEWORK_ID)
                .setName(FRAMEWORK_NAME)
                .setVersions(getVersionsForTest());
    }

    private List<JavaScriptFrameworkVersion> getVersionsForTest() {
        return Collections.singletonList(getFrameworkVersionForTest());
    }

    private JavaScriptFrameworkVersion getFrameworkVersionForTest(){
        return new JavaScriptFrameworkVersion()
                .setVersion(FRAMEWORK_VERSION)
                .setHypeLevel(HypeLevel.OVER_9000)
                .setDeprecationDate(LocalDate.now())
                .setId(FRAMEWORK_VERSION_ID);

    }

}