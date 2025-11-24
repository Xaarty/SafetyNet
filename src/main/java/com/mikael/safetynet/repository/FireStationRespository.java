package com.mikael.safetynet.repository;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikael.safetynet.model.FireStation;
import com.mikael.safetynet.util.DataReaderUtil;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class FireStationRespository {

    private final List<FireStation> firestations;

    public FireStationRespository(DataReaderUtil reader) throws IOException {
        this.firestations = reader.getAllFireStations();
    }

    public List<FireStation> getAll() throws IOException {
        return firestations;
    }

    public FireStation getByAddress(String address) throws IOException {
        return firestations.stream()
                .filter(firestations
                -> firestations.getAddress().equals(address))
                .findFirst().orElseThrow(() -> new RuntimeException(
                        "FireStation mapping not found for address: " + address));
    }

    public FireStation save(FireStation fireStation) {
        firestations.add(fireStation);
        return fireStation;
    }

    public FireStation update(FireStation fireStation) throws IOException {
        FireStation fireStationFound = getByAddress(fireStation.getAddress());
        fireStationFound.setStation(fireStation.getStation());
        return fireStation;
    }

    public void remove(String address) throws IOException {
        FireStation fireStation = getByAddress(address);
        firestations.remove(fireStation);
    }
}
