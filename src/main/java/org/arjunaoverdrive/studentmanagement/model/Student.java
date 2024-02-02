package org.arjunaoverdrive.studentmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    private String id;
    private String firstName;
    private String lastName;
    private int age;

    @Override
    public String toString() {
        return String.format("%-38s|%-15s|%-15s|%-10d", id, firstName, lastName, age);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return getAge() == student.getAge() && Objects.equals(getFirstName(),
                student.getFirstName()) && Objects.equals(getLastName(), student.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getAge());
    }
}
