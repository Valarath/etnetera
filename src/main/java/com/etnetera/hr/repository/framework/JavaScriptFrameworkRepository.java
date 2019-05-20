package com.etnetera.hr.repository.framework;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etnetera.hr.repository.entity.JavaScriptFramework;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring data repository interface used for accessing the data in database.
 * 
 * @author Etnetera
 *
 */
public interface JavaScriptFrameworkRepository extends JpaRepository<JavaScriptFramework, Long> {

    @Query(value = "select *\n" +
            "from java_script_framework\n" +
            "         left join java_script_framework_version jsfv on java_script_framework.framework_id = jsfv.framework_id\n" +
            "where to_tsvector(concat_ws(' ',jsfv.framework_id, name, deprecation_date, hype_level, version)) @@ to_tsquery(:by)", nativeQuery = true)
    List<JavaScriptFramework> search(@Param("by") String by);

}
