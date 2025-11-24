package com.mikael.safetynet.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikael.safetynet.model.*;
import org.springframework.asm.TypeReference;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class DataReaderUtil {
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

    public List<FireStation> getAllFireStations() throws IOException {
        FireStations firestations;
        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/firestations.json")){
            firestations = objectMapper.readValue(inputStream, FireStations.class);
        } catch (IOException e) {
            throw new RuntimeException("failed to read json", e);
        }
        return firestations.getFirestations();
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
}
