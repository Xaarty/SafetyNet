package com.mikael.safetynet.controller;

import com.mikael.safetynet.model.MedicalRecord;
import com.mikael.safetynet.Service.MedicalRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    private static final Logger logger = LoggerFactory.getLogger(MedicalRecordController.class);

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping
   public List<MedicalRecord> all() throws IOException {
        logger.info("GET /medicalRecord");
        try {
            return medicalRecordService.allMedicalRecords();
        } catch (Exception e) {
            logger.error("GET /medicalRecord - erreur", e);
            throw e;
        }
    }

    @GetMapping("/{firstName}/{lastName}")
    public MedicalRecord getByName(@PathVariable String firstName, @PathVariable String lastName) throws IOException {
        logger.info("GET /medicalRecord/{}/{}", firstName, lastName);
        try {
            return medicalRecordService.getByName(firstName, lastName);
        } catch (Exception e) {
            logger.error("GET /medicalRecord/{}/{} - erreur", firstName, lastName, e);
            throw e;
        }
    }

    @PostMapping
    public MedicalRecord addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        logger.info("POST /medicalRecord -  firstName={} lastName={}",
                medicalRecord.getFirstName(), medicalRecord.getLastName());
        try {
            return medicalRecordService.create(medicalRecord);
        } catch (Exception e) {
            logger.error("POST /medicalRecord - erreur", e);
            throw e;
        }
    }

    @PutMapping
    public MedicalRecord updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws IOException {
        logger.info("PUT /medicalRecord - firstName={} lastName={}",
                medicalRecord.getFirstName(), medicalRecord.getLastName());
        try {
            return medicalRecordService.update(medicalRecord);
        } catch (Exception e) {
            logger.error("PUT /medicalRecord - erreur", e);
            throw e;
        }
    }

    @DeleteMapping("/{firstName}/{lastName}")
    public void deleteMedicalRecord(@PathVariable String firstName, @PathVariable String lastName) throws IOException {
        logger.info("DELETE /medicalRecord/{}/{}", firstName, lastName);
        try {
            medicalRecordService.delete(firstName, lastName);
        } catch (Exception e) {
            logger.error("DELETE /medicalRecord/{}/{} - erreur",
                    firstName, lastName, e);
            throw e;
        }
    }

}
