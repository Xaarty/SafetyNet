package com.mikael.safetynet.repository;

import com.mikael.safetynet.model.FireStation;
import com.mikael.safetynet.util.DataReaderUtil;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class FireStationRespository {

    private final DataReaderUtil reader;
    private final List<FireStation> firestations;

    public FireStationRespository( DataReaderUtil reader) throws IOException {
        this.reader = reader;
        this.firestations = reader.getAllFireStations();
    }

    public List<FireStation> getAll() {
        return firestations;
    }

    public FireStation getByAddress(String address) {
        return firestations.stream()
                .filter(firestations
                -> firestations.getAddress().equals(address))
                .findFirst().orElseThrow(() -> new RuntimeException(
                        "FireStation mapping not found for address: " + address));
    }

    public FireStation save(FireStation fireStation) {
        firestations.add(fireStation);
        reader.writeAllFireStations(firestations);
        return fireStation;
    }

    public FireStation update(FireStation fireStation) {
        FireStation fireStationFound = getByAddress(fireStation.getAddress());
        fireStationFound.setStation(fireStation.getStation());
        reader.writeAllFireStations(firestations);
        return fireStation;
    }

    public void remove(String address) {
        FireStation fireStation = getByAddress(address);
        firestations.remove(fireStation);
        reader.writeAllFireStations(firestations);
    }
}
