package org.arjunaoverdrive.studentmanagement.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class DeleteStudentEventHolder extends ApplicationEvent {

    private final String studentId;
    public DeleteStudentEventHolder(Object source, String studentId) {
        super(source);
        this.studentId = studentId;
    }
}
