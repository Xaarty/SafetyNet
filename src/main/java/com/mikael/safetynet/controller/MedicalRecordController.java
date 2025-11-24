package com.mikael.safetynet.controller;

import com.mikael.safetynet.model.MedicalRecord;
import com.mikael.safetynet.Service.MedicalRecordService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping
   public List<MedicalRecord> all() throws IOException {
        return medicalRecordService.allMedicalRecords();
    }

    @GetMapping("/{firstName}/{lastName}")
    public MedicalRecord getByName(@PathVariable String firstName, @PathVariable String lastName) throws IOException {
        return medicalRecordService.getByName(firstName, lastName);
    }

    @PostMapping
    public MedicalRecord addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        return medicalRecordService.create(medicalRecord);
    }

    @PutMapping
    public MedicalRecord updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws IOException {
        return medicalRecordService.update(medicalRecord);
    }

    @DeleteMapping("/{firstName}/{lastName}")
    public void deleteMedicalRecord(@PathVariable String firstName, @PathVariable String lastName) throws IOException {
        medicalRecordService.delete(firstName, lastName);
    }

}
