package com.mikael.safetynet.DTO;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FloodPersonDTOTest {

    @Test
    public void testFloodPersonDTO() {
        FloodPersonDTO dto = new FloodPersonDTO("John", "Boyd", "841-874-6512", 40, List.of("aznol:350mg", "hydrapermazol:100mg"), List.of("nillacilan"));

        assertEquals("John", dto.getFirstName());
        assertEquals(40, dto.getAge());
        assertEquals("841-874-6512", dto.getPhone());
    }

    @Test
    public void FloodPersonDTOTest() {

        FloodPersonDTO dto = new FloodPersonDTO();

        dto.setFirstName("John");
        dto.setLastName("Boyd");
        dto.setPhone("841-874-6512");
        dto.setAge(40);
        dto.setMedications(List.of("aznol:350mg", "hydrapermazol:100mg"));
        dto.setAllergies(List.of("nillacilan"));

        assertEquals("John", dto.getFirstName());
        assertEquals("Boyd", dto.getLastName());
        assertEquals("841-874-6512", dto.getPhone());
        assertEquals(40, dto.getAge());
        assertEquals("aznol:350mg", dto.getMedications().getFirst());
        assertEquals("nillacilan", dto.getAllergies().getFirst());
    }
}
