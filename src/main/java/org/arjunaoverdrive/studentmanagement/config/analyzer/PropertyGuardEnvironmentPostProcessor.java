package org.arjunaoverdrive.studentmanagement.config.analyzer;

import org.arjunaoverdrive.studentmanagement.exception.StudentManagementPropertyException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringUtils;

public class PropertyGuardEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        boolean enabled = Boolean.parseBoolean(environment.getProperty("app.init.enabled"));
        String pathToSourceFile = environment.getProperty("app.init.source-file");

        if(enabled){
            check(pathToSourceFile);
        }
    }

    private void check(String pathToSourceFile){
        boolean isInvalidType = !StringUtils.hasText(pathToSourceFile);

        if(isInvalidType){
            throw new StudentManagementPropertyException("Property app.init.source-file must be set.");
        }

        if(!pathToSourceFile.matches("(\\/?.+)+\\.\\w+")){
            throw new StudentManagementPropertyException("Invalid source-file path value.");
        }
    }
}
