package org.arjunaoverdrive.studentmanagement.util;

import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class StudentValidator {
    public boolean validateStudentData(String[] studentData) {
        return validateDataLength(studentData)
                && validateName(studentData[0])
                && validateName(studentData[1])
                && validateAge(studentData[2]);
    }

    private boolean validateDataLength(String[] studentData) {
        if (studentData.length != 3) {
            throw new RuntimeException("Wrong data format." +
                    "Should consist of name, last name, and age values separated by commas.");
        }
        return true;
    }

    private boolean validateAge(String age) {
        if (!age.matches("\\d+?")) {
            throw new RuntimeException(
                    MessageFormat.format("Wrong age format: {0}. Should consist of digits.", age));
        }
        if(Integer.parseInt(age) < 17 || Integer.parseInt(age) > 50){
            throw new RuntimeException(
                    MessageFormat.format("Wrong age format: {0}. Should be a value between 17 and 50 (including).", age));
        }
        return true;
    }

    private boolean validateName(String name) {
        if (!name.matches("[A-Z]\\w+?")) {
            throw new RuntimeException(
                    MessageFormat.format("Wrong name format: {0}. Should consist of alphabetic characters.", name));
        }
        return true;
    }
}
