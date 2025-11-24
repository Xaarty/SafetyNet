package com.mikael.safetynet.DTO;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonsByStationDTOTest {

    @Test
    public void testPersonsByStationDTO() {
        PersonCoveredDTO covered = new PersonCoveredDTO("John", "Doe", "123", "555", 20);
        PersonsByStationDTO dto = new PersonsByStationDTO(List.of(covered), 1, 0);

        assertEquals(1, dto.getAdultCount());
        assertEquals(1, dto.getPersons().size());
    }
}
