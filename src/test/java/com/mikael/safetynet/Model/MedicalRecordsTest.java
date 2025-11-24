package com.mikael.safetynet.Model;

import com.mikael.safetynet.model.MedicalRecord;
import com.mikael.safetynet.model.MedicalRecords;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MedicalRecordsTest {

    @Test
    public void testMedicalRecords() {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("John");

        MedicalRecords medicalRecords = new MedicalRecords();
        medicalRecords.setMedicalRecords(List.of(medicalRecord));

        assertNotNull(medicalRecords.getMedicalrecords());
        assertEquals("John", medicalRecords.getMedicalrecords().getFirst().getFirstName());
    }
}
