package de.dlrg.lifesavingsports.records.service.imports;

import com.opencsv.bean.CsvToBeanBuilder;
import de.dlrg.lifesavingsports.records.service.RecordTypesRepository;
import de.dlrg.lifesavingsports.records.service.RecordsRepository;
import lombok.RequiredArgsConstructor;
import org.lisasp.basics.jre.id.IdGenerator;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RequiredArgsConstructor
public class RecordsImporter {

    private final RecordTypesRepository recordTypesRepository;
    private final RecordsRepository recordsRepository;

    private final IdGenerator idGenerator;

    public void importCSV(InputStream inputStream) {
        List<ImportedRecord> records = new CsvToBeanBuilder(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).withType(ImportedRecord.class).withSeparator(';').build().parse();
        records.forEach(r -> recordsRepository.save(r.toDto(idGenerator.nextId())));
    }
}
