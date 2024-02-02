package org.arjunaoverdrive.studentmanagement.config;

import lombok.RequiredArgsConstructor;
import org.arjunaoverdrive.studentmanagement.config.properties.InitStudentsProperties;
import org.arjunaoverdrive.studentmanagement.util.StudentsInitializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@ConditionalOnBean(StudentsInitializer.class)
@EnableConfigurationProperties(InitStudentsProperties.class)
@RequiredArgsConstructor
public class AppConfiguration {

    private final StudentsInitializer studentsInitializer;

    @EventListener(ApplicationStartedEvent.class)
    public void initStudentsList(){
        studentsInitializer.initStudents();
    }

}
