package org.arjunaoverdrive.studentmanagement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arjunaoverdrive.studentmanagement.event.CreateStudentEventHolder;
import org.arjunaoverdrive.studentmanagement.event.DeleteStudentEventHolder;
import org.arjunaoverdrive.studentmanagement.model.Student;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.util.List;
import java.util.UUID;

@ShellComponent
@RequiredArgsConstructor
@Slf4j
public class ConsoleStudentService {

    private final StudentRepo studentRepo;


    @ShellMethod(key = "l")
    @ShellMethodAvailability("canFetchStudents")
    public void list() {
        System.out.printf(String.format("%-38s|%-15s|%-15s|%-10s\n",
                "id", "first name", "last name", "age"));
        this.studentRepo
                .getStudents()
                .forEach(System.out::println);
    }

    public Availability canFetchStudents(){
        return !this.studentRepo.getStudents().isEmpty() ? Availability.available()
                : Availability.unavailable("No students on the list yet.");
    }

    @ShellMethod(key = "a")
    public void addNewStudent(@ShellOption(value = "n") String firstName,
                              @ShellOption (value = "l") String lastName,
                              @ShellOption(value = "a") int age){
        Student student = Student.builder()
                .id(UUID.randomUUID().toString())
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .build();

        this.studentRepo.addStudent(student);
    }


    @ShellMethod(key = "rm")
    @ShellMethodAvailability("canFetchStudents")
    public void deleteStudentById(@ShellOption(value = "i") String id){
        this.studentRepo.deleteStudent(id);
    }

    @ShellMethod(key = "c")
    @ShellMethodAvailability("canFetchStudents")
    public void deleteAllStudents(){
        this.studentRepo.clearStudents();
        log.info("Deleted all students.");
    }


    public void addAllStudents(List<Student> studentList) {
        this.studentRepo.saveAll(studentList);
        log.info("Saved {} students", studentList.size());
    }
}
