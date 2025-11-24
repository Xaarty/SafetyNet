package com.mikael.safetynet.Controller;

import com.mikael.safetynet.controller.MedicalRecordController;
import com.mikael.safetynet.model.MedicalRecord;
import com.mikael.safetynet.Service.MedicalRecordService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

    public class MedicalRecordControllerTest {

        @Test
        void testMedicalRecordController() throws IOException {

            MedicalRecordService mockService = Mockito.mock(MedicalRecordService.class);
            MedicalRecordController controller = new MedicalRecordController(mockService);

            MedicalRecord medicalRecord = new MedicalRecord();
            medicalRecord.setFirstName("John");
            medicalRecord.setLastName("Boyd");

            Mockito.when(mockService.allMedicalRecords()).thenReturn(List.of(medicalRecord));

            List<MedicalRecord> all = controller.all();
            assertNotNull(all);
            assertEquals(1, all.size(), "La liste retournée doit contenir 1 élément");
            Mockito.verify(mockService).allMedicalRecords();

            Mockito.when(mockService.getByName("John", "Boyd")).thenReturn(medicalRecord);
            MedicalRecord byName = controller.getByName("John", "Boyd");
            assertNotNull(byName);
            assertEquals("John", byName.getFirstName());
            Mockito.verify(mockService).getByName("John", "Boyd");

            Mockito.when(mockService.create(Mockito.any())).thenReturn(medicalRecord);
            MedicalRecord created = controller.addMedicalRecord(medicalRecord);
            assertNotNull(created);
            assertEquals("John", created.getFirstName());
            Mockito.verify(mockService).create(medicalRecord);

            Mockito.when(mockService.update(Mockito.any())).thenReturn(medicalRecord);
            MedicalRecord updated = controller.updateMedicalRecord(medicalRecord);
            assertNotNull(updated);
            assertEquals("John", updated.getFirstName());
            Mockito.verify(mockService).update(medicalRecord);

            controller.deleteMedicalRecord("John", "Boyd");
            Mockito.verify(mockService).delete("John", "Boyd");
        }
}
