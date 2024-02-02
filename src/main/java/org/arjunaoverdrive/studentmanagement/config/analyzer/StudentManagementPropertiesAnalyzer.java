package org.arjunaoverdrive.studentmanagement.config.analyzer;

import org.arjunaoverdrive.studentmanagement.exception.StudentManagementPropertyException;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

import java.text.MessageFormat;

public class StudentManagementPropertiesAnalyzer extends AbstractFailureAnalyzer<StudentManagementPropertyException> {
    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, StudentManagementPropertyException cause) {
        return new FailureAnalysis(
                MessageFormat.format("Exception when trying to set property: {0}", cause.getMessage()),
                "Make sure the path to the source file with students data is set correctly in application.yaml",
                cause
        );
    }
}
