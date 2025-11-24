package com.mikael.safetynet.Service;

import com.mikael.safetynet.model.MedicalRecord;
import com.mikael.safetynet.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;
    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public List<MedicalRecord> allMedicalRecords() throws IOException {
        return medicalRecordRepository.getAll()
                .stream().toList();
    }

    public MedicalRecord getByName(String firstName, String lastName) throws IOException {
        return medicalRecordRepository.getByName(firstName, lastName);
    }

    public MedicalRecord create(MedicalRecord record) {
        return medicalRecordRepository.save(record);
    }

    public MedicalRecord update(MedicalRecord medicalRecord) throws IOException {
        return medicalRecordRepository.update(medicalRecord);
    }

    public void delete(String firstName, String lastName) throws IOException {
        medicalRecordRepository.remove(firstName, lastName);
    }
}
