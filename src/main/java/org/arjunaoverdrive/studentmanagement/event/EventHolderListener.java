package org.arjunaoverdrive.studentmanagement.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
@Slf4j
public class EventHolderListener {

    @EventListener(CreateStudentEventHolder.class)
    public void listen(CreateStudentEventHolder eventHolder){
        log.info(MessageFormat.format("Created student: {0}", eventHolder.getStudent()));
    }

    @EventListener(DeleteStudentEventHolder.class)
    public void listen(DeleteStudentEventHolder eventHolder){
        log.info(MessageFormat.format("Deleted student with id {0}", eventHolder.getStudentId()));
    }
}
