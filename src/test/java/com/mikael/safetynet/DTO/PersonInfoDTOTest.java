package com.mikael.safetynet.DTO;

import com.mikael.safetynet.model.Person;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonInfoDTOTest {

    @Test
    public void testPersonInfoDTO() {
        PersonInfoDTO dto = new PersonInfoDTO("John", "Boyd", "1509 Culver St", 40, "jaboyd@email.com", List.of("aznol:350mg", "hydrapermazol:100mg"), List.of("nillacilan"));

        assertEquals("Boyd", dto.getLastName());
        assertEquals("jaboyd@email.com", dto.getEmail());
        assertEquals(40, dto.getAge());
    }

    @Test
    public void personInfoDTOTest() {
        PersonInfoDTO dto = new PersonInfoDTO();

        dto.setFirstName("John");
        dto.setLastName("Boyd");
        dto.setAddress("1509 Culver St");
        dto.setAge(40);
        dto.setMedications(List.of("aznol:350mg", "hydrapermazol:100mg"));
        dto.setAllergies(List.of("nillacilan"));
        dto.setEmail("jaboyd@email.com");

        assertEquals("John", dto.getFirstName());
        assertEquals("Boyd", dto.getLastName());
        assertEquals("1509 Culver St", dto.getAddress());
        assertEquals(40, dto.getAge());
        assertEquals("aznol:350mg", dto.getMedications().getFirst());
        assertEquals("nillacilan", dto.getAllergies().getFirst());
        assertEquals("jaboyd@email.com", dto.getEmail());
    }
}
