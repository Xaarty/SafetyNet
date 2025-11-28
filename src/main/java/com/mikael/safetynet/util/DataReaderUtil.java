package com.mikael.safetynet.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikael.safetynet.model.*;
import org.springframework.asm.TypeReference;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class DataReaderUtil {
    private static final String FIRESTATION_PATH = "src/main/resources/data/firestations.json";
    private static final String PERSON_PATH         = "src/main/resources/data/persons.json";
    private static final String MEDICALRECORD_PATH  = "src/main/resources/data/medicalrecords.json";
    private final ObjectMapper objectMapper;
    public final static String DATA_PATH = "data/Data.json";


    public DataReaderUtil(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    public List<Person> getAllPersons() throws IOException {
        Persons persons;
        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/persons.json")){
            persons = objectMapper.readValue(inputStream, Persons.class);
        } catch (IOException e) {
            throw new RuntimeException("failed to read json", e);
        }
        return persons.getPersons();
    }

    public void writeAllPersons(List<Person> personsList) {
        try {
            Persons wrapper = new Persons();
            wrapper.setPersons(personsList);

            objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(new File(PERSON_PATH), wrapper);

        } catch (IOException e) {
            throw new RuntimeException("failed to write persons json", e);
        }
    }

    public List<FireStation> getAllFireStations() throws IOException {
        FireStations firestations;
        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/firestations.json")){
            firestations = objectMapper.readValue(inputStream, FireStations.class);
        } catch (IOException e) {
            throw new RuntimeException("failed to read json", e);
        }
        return firestations.getFirestations();
    }

    public void writeAllFireStations(List<FireStation> firestations) {
        try {
            FireStations wrapper = new FireStations();
            wrapper.setFirestations(firestations);

            objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(new File(FIRESTATION_PATH), wrapper);

        } catch (IOException e) {
            throw new RuntimeException("failed to write firestations json", e);
        }
    }

    public List<MedicalRecord> getAllMedicalRecords() throws IOException {
        MedicalRecords medicalRecords;
        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/medicalrecords.json")){
            medicalRecords = objectMapper.readValue(inputStream, MedicalRecords.class);
        } catch (IOException e) {
            throw new RuntimeException("failed to read json", e);
        }
        return medicalRecords.getMedicalrecords();
    }

    public void writeAllMedicalRecords(List<MedicalRecord> records) {
        try {
            MedicalRecords wrapper = new MedicalRecords();
            wrapper.setMedicalRecords(records);

            objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(new File(MEDICALRECORD_PATH), wrapper);

        } catch (IOException e) {
            throw new RuntimeException("failed to write medicalrecords json", e);
        }
    }
}
