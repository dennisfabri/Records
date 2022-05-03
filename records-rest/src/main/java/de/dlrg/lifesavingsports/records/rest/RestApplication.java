package de.dlrg.lifesavingsports.records.rest;

import de.dlrg.lifesavingsports.records.service.RecordTypesRepository;
import de.dlrg.lifesavingsports.records.service.RecordsRepository;
import de.dlrg.lifesavingsports.records.service.RecordsService;
import de.dlrg.lifesavingsports.records.service.imports.RecordsImporter;
import lombok.extern.slf4j.Slf4j;
import org.lisasp.basics.jre.id.IdGenerator;
import org.lisasp.basics.jre.id.UUIDGenerator;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@Slf4j
@SpringBootApplication
public class RestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	RecordsService recordsService(RecordTypesRepository recordTypes, RecordsRepository records, IdGenerator idGenerator) {
		return new RecordsService(recordTypes, records, idGenerator);
	}

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	IdGenerator idGenerator() {
		return new UUIDGenerator();
	}

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	RecordsImporter recordsImporter(RecordTypesRepository recordTypesRepository, RecordsRepository recordsRepository, IdGenerator idGenerator) {
		return new RecordsImporter(recordTypesRepository, recordsRepository, idGenerator);
	}
}
