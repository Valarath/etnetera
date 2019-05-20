package com.etnetera.hr.controller;

import com.etnetera.hr.dto.JavaScriptFramework;
import com.etnetera.hr.service.JavaScriptFrameworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Simple REST controller for accessing application logic.
 *  
 * @author Etnetera
 *
 */
@RestController
@RequestMapping("/frameworks")
public class JavaScriptFrameworkController {

	@Autowired
	private JavaScriptFrameworkService service;

	@GetMapping
	public Iterable<JavaScriptFramework> frameworks() {
		return service.getAllFrameworks();
	}

	@GetMapping("/search")
	public Iterable<JavaScriptFramework> search(String by){
		return service.search(by);
	}

	@PostMapping
	public void addFramework(@RequestBody JavaScriptFramework framework){
		service.createFramework(framework);
	}

	@PutMapping
	public void changeFramework(@RequestBody JavaScriptFramework framework){
		service.updateFramework(framework);
	}

	@PatchMapping
	public void patchFramework(@RequestBody JavaScriptFramework framework){
		service.updateFramework(framework);
	}

	@DeleteMapping
	public void deleteFramework(long frameworkId){
		service.deleteFramework(frameworkId);
	}
}
