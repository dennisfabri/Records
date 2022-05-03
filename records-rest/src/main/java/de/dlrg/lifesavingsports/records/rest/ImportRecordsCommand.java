package de.dlrg.lifesavingsports.records.rest;

import de.dlrg.lifesavingsports.records.service.imports.RecordsImporter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class ImportRecordsCommand implements CommandLineRunner {

    private final RecordsImporter importer;

    @Override
    public void run(String... args) throws IOException {
        if (args == null || args.length == 0) {
            return;
        }
        if (args.length != 2) {
            return;
        }
        if (args[0].equals("-import")) {
            importFile(args[1]);
        }

    }

    private void importFile(String filename) throws IOException {
        try (InputStream is = new FileInputStream(filename)) {
            importer.importCSV(is);
        }
    }
}
