package com.mikael.safetynet.Repository;

import com.mikael.safetynet.model.MedicalRecord;
import com.mikael.safetynet.repository.MedicalRecordRepository;
import com.mikael.safetynet.util.DataReaderUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MedicalRecordRepositoryTest {

    @Test
    public void getByName() throws IOException {
        MedicalRecord medicalRecord = new MedicalRecord(); medicalRecord.setFirstName("John"); medicalRecord.setLastName("Boyd");
        DataReaderUtil reader = Mockito.mock(DataReaderUtil.class);
        Mockito.when(reader.getAllMedicalRecords()).thenReturn(List.of(medicalRecord));

        MedicalRecordRepository repository = new MedicalRecordRepository(reader);
        MedicalRecord result = repository.getByName("John", "Boyd");

        assertNotNull(result);
    }

    @Test
    public void save() throws IOException {
        MedicalRecord medicalRecord = new MedicalRecord(); medicalRecord.setFirstName("John"); medicalRecord.setLastName("Boyd");
        DataReaderUtil reader = Mockito.mock(DataReaderUtil.class);
        Mockito.when(reader.getAllMedicalRecords()).thenReturn(new ArrayList<>());

        MedicalRecordRepository repository = new MedicalRecordRepository(reader);
        repository.save(medicalRecord);

        assertEquals(1, repository.getAll().size());
    }

    @Test
    public void update() throws IOException {
        MedicalRecord medicalRecord = new MedicalRecord(); medicalRecord.setFirstName("John"); medicalRecord.setLastName("Boyd"); medicalRecord.setBirthdate("03/06/1984");
        DataReaderUtil reader = Mockito.mock(DataReaderUtil.class);
        Mockito.when(reader.getAllMedicalRecords()).thenReturn(new ArrayList<>(List.of(medicalRecord)));

        MedicalRecordRepository repository = new MedicalRecordRepository(reader);
        MedicalRecord updated = new MedicalRecord(); updated.setFirstName("John"); updated.setLastName("Boyd"); updated.setBirthdate("03/06/1985");

        repository.update(updated);

        assertEquals("03/06/1985", repository.getByName("John", "Boyd").getBirthdate());
    }

    @Test
    public void remove() throws IOException {
        MedicalRecord medicalRecord = new MedicalRecord(); medicalRecord.setFirstName("John"); medicalRecord.setLastName("Boyd");
        DataReaderUtil reader = Mockito.mock(DataReaderUtil.class);
        Mockito.when(reader.getAllMedicalRecords()).thenReturn(new ArrayList<>(List.of(medicalRecord)));

        MedicalRecordRepository repository = new MedicalRecordRepository(reader);
        repository.remove("John", "Boyd");

        assertEquals(0, repository.getAll().size());
    }
}
