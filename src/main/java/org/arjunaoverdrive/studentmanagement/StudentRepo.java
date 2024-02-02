package org.arjunaoverdrive.studentmanagement;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.arjunaoverdrive.studentmanagement.event.CreateStudentEventHolder;
import org.arjunaoverdrive.studentmanagement.event.DeleteStudentEventHolder;
import org.arjunaoverdrive.studentmanagement.model.Student;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class StudentRepo {
    @Getter
    private final Set<Student> students;
    private final ApplicationEventPublisher publisher;

    public StudentRepo(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
        this.students = new HashSet<>();
    }

    public boolean addStudent(Student student) {
        if (students.contains(student)) {
            System.out.println("This student already exists.");
            return false;
        }
        students.add(student);
        publisher.publishEvent(new CreateStudentEventHolder(this, student));
        return true;
    }

    public void deleteStudent(String id) {
        Map<String, Student> studentMap = new HashMap<>();
        students.forEach(s -> studentMap.put(s.getId(), s));
        if (studentMap.containsKey(id)) {
            Student toRemove = studentMap.get(id);
            students.remove(toRemove);
            publisher.publishEvent(new DeleteStudentEventHolder(this, id));
            return;
        }
        log.warn("Student with id {} not found.", id);
    }

    public void clearStudents() {
        students.clear();
    }

    public void saveAll(List<Student> studentList) {
        students.addAll(studentList);
    }
}
