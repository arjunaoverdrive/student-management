package org.arjunaoverdrive.studentmanagement.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.init")
@Getter
@Setter
public class InitStudentsProperties {

    private boolean enabled;

    private String sourceFile;
}
