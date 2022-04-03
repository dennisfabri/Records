package de.dlrg.lifesavingsports.records.service.exception;

public class RecordTypeUnknownException extends RuntimeException {
    public RecordTypeUnknownException(String acronym) {
        super(String.format("The RecordType '%s' is unknown.", acronym));
    }
}
