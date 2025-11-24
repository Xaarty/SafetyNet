package com.mikael.safetynet.Model;

import com.mikael.safetynet.model.FireStation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FireStationTest {

    @Test
    public void testFireStation() {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("1509 Culver St");
        fireStation.setStation("3");

        assertEquals("1509 Culver St", fireStation.getAddress());
        assertEquals("3", fireStation.getStation());
    }
}
