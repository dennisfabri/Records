package de.dlrg.lifesavingsports.records.ui.views.times;

import de.dlrg.lifesavingsports.records.api.RecordDto;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TimesService {

    private final KeycloakRestTemplate keycloakRestTemplate;

    private static Person createPerson(RecordDto recordDto) {
        return new Person(recordDto.getName(), recordDto.getClub(), recordDto.getNation());
    }

    Stream<Person> fetchTimes(int offset, int limit) {
        // return Arrays.stream(new Person[0]);
        return Arrays.stream(keycloakRestTemplate.exchange("http://localhost:9999/api/records",
                                                    HttpMethod.GET,
                                                    null,
                                                    RecordDto[].class)
                .getBody()).map(r -> createPerson(r)).skip(offset).limit(limit);
    }
}
