package com.mikael.safetynet.Service;

import com.mikael.safetynet.model.MedicalRecord;
import com.mikael.safetynet.repository.MedicalRecordRepository;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class MedicalRecordServiceTest {
    MedicalRecordRepository mockRepo = mock(MedicalRecordRepository.class);
    MedicalRecordService service = new MedicalRecordService(mockRepo);

    @Test
    public void allMedicalRecords() throws IOException {
        List<MedicalRecord> expected = List.of(new MedicalRecord());
        when(mockRepo.getAll()).thenReturn(expected);

        List<MedicalRecord> result = service.allMedicalRecords();

        assertEquals(1, result.size());
        verify(mockRepo).getAll();
    }

    @Test
    public void getByName() throws IOException {
        MedicalRecord medicalRecord = new MedicalRecord();
        when(mockRepo.getByName("John", "Boyd")).thenReturn(medicalRecord);

        MedicalRecord result = service.getByName("John", "Boyd");

        assertNotNull(result);
        verify(mockRepo).getByName("John", "Boyd");
    }

    @Test
    public void create() {
        MedicalRecord medicalRecord = new MedicalRecord();
        when(mockRepo.save(medicalRecord)).thenReturn(medicalRecord);

        MedicalRecord result = service.create(medicalRecord);

        assertEquals(medicalRecord, result);
        verify(mockRepo).save(medicalRecord);
    }

    @Test
    public void update() throws IOException {
        MedicalRecord medicalRecord = new MedicalRecord();
        when(mockRepo.update(medicalRecord)).thenReturn(medicalRecord);

        MedicalRecord result = service.update(medicalRecord);

        assertEquals(medicalRecord, result);
        verify(mockRepo).update(medicalRecord);
    }

    @Test
    public void delete() throws IOException {
        service.delete("John", "Boyd");
        verify(mockRepo).remove("John", "Boyd");
    }
}
