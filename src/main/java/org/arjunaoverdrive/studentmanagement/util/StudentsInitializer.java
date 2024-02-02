package org.arjunaoverdrive.studentmanagement.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arjunaoverdrive.studentmanagement.ConsoleStudentService;
import org.arjunaoverdrive.studentmanagement.config.properties.InitStudentsProperties;
import org.arjunaoverdrive.studentmanagement.model.Student;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@ConditionalOnExpression("${app.init.enabled:true}")
@RequiredArgsConstructor
@Slf4j
public class StudentsInitializer {


    private final InitStudentsProperties properties;
    private final ConsoleStudentService service;
    private final StudentValidator validator;


    public void initStudents() {
        List<String> sourceFIleContent = readSourceFileContent();
        log.info("Reading file...");

        List<Student> students = new ArrayList<>();
        for (String s : sourceFIleContent) {
            convertLineToStudent(s, students);
        }
        service.addAllStudents(students);
    }

    private void convertLineToStudent(String studentLine, List<Student> students) {
        if (studentLine.isEmpty() || studentLine.isBlank()) return;

        String[] studentData = studentLine.split(",");
        try {
            validator.validateStudentData(studentData);
        } catch (RuntimeException e) {
            log.warn("Invalid student data: {}", e.getMessage());
            return;
        }
        Student student = Student.builder()
                .id(UUID.randomUUID().toString())
                .firstName(studentData[0])
                .lastName(studentData[1])
                .age(Integer.parseInt(studentData[2]))
                .build();

        students.add(student);
    }

    private List<String> readSourceFileContent() {
        List<String> content;
        String sourceFilePath = properties.getSourceFile();

        try (InputStream inputStream = new ClassPathResource(sourceFilePath).getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            content = reader.lines()
                    .filter(l -> !l.isEmpty() && !l.isBlank())
                    .toList();
        } catch (IOException ioException) {
            throw new RuntimeException(MessageFormat.format("File {0} cannot be accessed or found.",
                    sourceFilePath));
        }
        return content;
    }

}
