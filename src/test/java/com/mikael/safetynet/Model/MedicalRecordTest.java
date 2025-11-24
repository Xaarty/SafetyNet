package com.mikael.safetynet.Model;

import com.mikael.safetynet.model.MedicalRecord;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MedicalRecordTest {

    @Test
    public void testMedicalRecord() {
        MedicalRecord record = new MedicalRecord();
        record.setFirstName("John");
        record.setLastName("Boyd");
        record.setBirthdate("03/06/1984");
        record.setMedications(List.of("aznol:350mg", "hydrapermazol:100mg"));
        record.setAllergies(List.of("nillacilan"));

        assertEquals("John", record.getFirstName());
        assertEquals("Boyd", record.getLastName());
        assertEquals("03/06/1984", record.getBirthdate());
        assertEquals(2, record.getMedications().size());
        assertEquals("nillacilan", record.getAllergies().getFirst());
    }
}
