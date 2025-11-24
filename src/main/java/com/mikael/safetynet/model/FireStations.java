package com.mikael.safetynet.model;

import java.util.List;

public class FireStations {
    private List<FireStation> firestations;

    public List<FireStation> getFirestations() {
        return firestations;
    }

    public void setFirestations(List<FireStation> firestations) {
        this.firestations = firestations;
    }

    @Override
    public String toString() {
        return "Firestations{" +
                "firestations=" + firestations +
                '}';
    }
}