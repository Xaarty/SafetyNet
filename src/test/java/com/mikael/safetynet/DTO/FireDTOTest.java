package com.mikael.safetynet.DTO;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FireDTOTest {
    @Test
    public void testFireDTO() {
        ResidentFireDTO resident = new ResidentFireDTO("John", "Doe", "123", 20, List.of("med1"), List.of("all1"));
        FireDTO dto = new FireDTO(Optional.of(2), List.of(resident));

        assertTrue(dto.getStationNumber().isPresent());
        assertEquals(1, dto.getResidents().size());
    }
}
