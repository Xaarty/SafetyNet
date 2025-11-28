package com.mikael.safetynet.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikael.safetynet.model.FireStation;
import com.mikael.safetynet.model.MedicalRecord;
import com.mikael.safetynet.model.MedicalRecords;
import com.mikael.safetynet.model.Person;
import com.mikael.safetynet.util.DataReaderUtil;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

    @Repository
    public class MedicalRecordRepository {

        private final DataReaderUtil reader;
        private final List<MedicalRecord> medicalRecords;


        public MedicalRecordRepository(DataReaderUtil reader) throws IOException {
            this.reader = reader;
            this.medicalRecords = reader.getAllMedicalRecords();
        }

        public List<MedicalRecord> getAll() {
            return medicalRecords;
        }

        public MedicalRecord getByName(String firstName, String lastName) {
            return medicalRecords.stream()
                    .filter(medicalRecords
                            -> medicalRecords.getFirstName().equals(firstName)
                            & medicalRecords.getLastName().equals(lastName))
                    .findFirst().orElseThrow(() -> new RuntimeException(
                            "Person not found: " + firstName + " " + lastName));
        }

        public MedicalRecord save(MedicalRecord medicalRecord) {
            medicalRecords.add(medicalRecord);
            reader.writeAllMedicalRecords(medicalRecords);
            return medicalRecord;
        }

        public MedicalRecord update(MedicalRecord medicalRecord) throws IOException {
            MedicalRecord medicalRecordFound = getByName(medicalRecord.getFirstName(), medicalRecord.getLastName());
            medicalRecordFound.setBirthdate(medicalRecord.getBirthdate());
            medicalRecordFound.setMedications(medicalRecord.getMedications());
            medicalRecordFound.setAllergies(medicalRecord.getAllergies());

            reader.writeAllMedicalRecords(medicalRecords);
            return medicalRecord;
        }

        public void remove(String fistName, String lastName) throws IOException {
            MedicalRecord medicalRecord = getByName(fistName, lastName);
            medicalRecords.remove(medicalRecord);
            reader.writeAllMedicalRecords(medicalRecords);
        }
    }