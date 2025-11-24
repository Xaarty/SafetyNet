package com.mikael.safetynet.Model;

import com.mikael.safetynet.model.FireStation;
import com.mikael.safetynet.model.FireStations;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FireStationsTest {

    @Test
    public void testFireStations() {
        FireStation fireStation = new FireStation();
        fireStation.setStation("3");
        FireStations stations = new FireStations();
        stations.setFirestations(List.of(fireStation));

        assertNotNull(stations.getFirestations());
        assertEquals("3", stations.getFirestations().getFirst().getStation());
    }

}
