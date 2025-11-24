package com.mikael.safetynet.DTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonCoveredDTOTest {

    @Test
    public void testPersonCoveredDTO() {
        PersonCoveredDTO dto = new PersonCoveredDTO("John", "Boyd", "1509 Culver St", "841-874-6512", 40);

        assertEquals("John", dto.getFirstName());
        assertEquals("Boyd", dto.getLastName());
        assertEquals("1509 Culver St", dto.getAddress());
        assertEquals("841-874-6512", dto.getPhone());
        assertEquals(40, dto.getAge());
    }

    @Test
    public void testPersonCoveredDTOGettersSetters() {

        PersonCoveredDTO dto = new PersonCoveredDTO();

        dto.setFirstName("John");
        dto.setLastName("Boyd");
        dto.setAddress("1509 Culver St");
        dto.setPhone("123-456-7890");
        dto.setAge(40);

        assertEquals("John", dto.getFirstName());
        assertEquals("Boyd", dto.getLastName());
        assertEquals("1509 Culver St", dto.getAddress());
        assertEquals("123-456-7890", dto.getPhone());
        assertEquals(40, dto.getAge());
    }

}
