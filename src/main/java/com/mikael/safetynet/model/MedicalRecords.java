package com.mikael.safetynet.model;

import java.util.List;

public class MedicalRecords {
    private List<MedicalRecord> medicalrecords;

    public List<MedicalRecord> getMedicalrecords() {
        return medicalrecords;
    }

    public void setMedicalRecords(List<MedicalRecord> medicalrecords) {
        this.medicalrecords = medicalrecords;
    }

    @Override
    public String toString() {
        return "Medicalrecords{" +
                "medicalrecords=" + medicalrecords +
                '}';
    }
}
