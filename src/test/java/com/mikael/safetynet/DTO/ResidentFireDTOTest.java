package com.mikael.safetynet.DTO;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResidentFireDTOTest {

    @Test
    public void testResidentFireDTO() {
        ResidentFireDTO dto = new ResidentFireDTO("John", "Doe", "123", 20, List.of("med"), List.of("all"));

        assertEquals("John", dto.getFirstName());
        assertEquals(20, dto.getAge());
        assertEquals("123", dto.getPhone());
    }
}
