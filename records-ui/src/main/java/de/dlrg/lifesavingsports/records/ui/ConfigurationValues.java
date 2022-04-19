package de.dlrg.lifesavingsports.records.ui;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class ConfigurationValues {

    @Value("${rekorde.public-url}")
    private String publicUrl;

    @Value("${keycloak.auth-server-url}")
    private String keycloakUrl;

    @Value("${keycloak.realm}")
    private String realm;
}
