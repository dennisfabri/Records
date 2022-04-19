package de.dlrg.lifesavingsports.records.rest;

import de.dlrg.lifesavingsports.records.service.RecordTypeDtoRepository;
import de.dlrg.lifesavingsports.records.service.RecordsService;
import org.lisasp.basics.jre.id.IdGenerator;
import org.lisasp.basics.jre.id.UUIDGenerator;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class RestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	RecordsService recordsService(RecordTypeDtoRepository recordTypes, IdGenerator idGenerator) {
		return new RecordsService(recordTypes, idGenerator);
	}

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	IdGenerator idGenerator() {
		return new UUIDGenerator();
	}
}
