package de.dlrg.lifesavingsports.records.ui.views.records;

import de.dlrg.lifesavingsports.records.api.Gender;
import de.dlrg.lifesavingsports.records.api.RecordDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecordsService {

    private final RestTemplate keycloakRestTemplate = new RestTemplate();

    private static @NotNull Record convertToViewModel(RecordDto recordDto) {
        return new Record(recordDto.getDiscipline(),
                          recordDto.getName(),
                          recordDto.getClub(),
                          recordDto.getNation(),
                          recordDto.getGender().toString(),
                          recordDto.getVenue(),
                          recordDto.getDate().toString(),
                          recordDto.getTime().toString());
    }

    Stream<Record> fetchTimes(String agegroup, Gender gender, int offset, int limit) {

        return Arrays.stream(keycloakRestTemplate.exchange(createURI(agegroup, gender, offset, limit),
                                                           HttpMethod.GET,
                                                           null,
                                                           RecordDto[].class)
                                     .getBody()).map(r -> convertToViewModel(r));
    }

    private URI createURI(String agegroup, Gender gender, int offset, int limit) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http").host("localhost").port(9999).path("/api/records")
                .queryParam("agegroup", agegroup)
                .queryParam("gender", gender.name())
                .queryParam("offset", offset)
                .queryParam("limit", limit)
                .build();
        log.info(uriComponents.toUri().toString());
        return uriComponents.toUri();
    }
}
