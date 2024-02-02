package org.arjunaoverdrive.studentmanagement.event;

import lombok.Getter;
import org.arjunaoverdrive.studentmanagement.model.Student;
import org.springframework.context.ApplicationEvent;

@Getter
public class CreateStudentEventHolder extends ApplicationEvent {

    private final Student student;

    public CreateStudentEventHolder(Object source, Student student) {
        super(source);
        this.student = student;
    }
}
