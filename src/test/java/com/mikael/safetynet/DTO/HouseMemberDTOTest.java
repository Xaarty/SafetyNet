package com.mikael.safetynet.DTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HouseMemberDTOTest {

    @Test
    public void testHouseMemberDTO() {
        HouseMemberDTO dto = new HouseMemberDTO("John", "Boyd");

        assertEquals("John", dto.getFirstName());
        assertEquals("Boyd", dto.getLastName());
    }
}
