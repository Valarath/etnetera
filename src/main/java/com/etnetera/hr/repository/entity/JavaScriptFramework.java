package com.etnetera.hr.repository.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Simple data entity describing basic properties of every JavaScript framework.
 * 
 * @author Etnetera
 *
 */
@Entity
@Data
@NoArgsConstructor
public class JavaScriptFramework {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "framework_id")
	private Long id;

	@Column(nullable = false, length = 30)
	private String name;

	@OneToMany(
			mappedBy = "framework",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.EAGER
	)
	private List<JavaScriptFrameworkVersion> versions;
		
}
