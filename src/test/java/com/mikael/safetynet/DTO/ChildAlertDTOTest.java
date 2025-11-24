package com.mikael.safetynet.DTO;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class ChildAlertDTOTest {

    @Test
    public void testChildAlertDTO() {
        HouseMemberDTO member = new HouseMemberDTO("John", "Boyd");
        ChildAlertDTO dto = new ChildAlertDTO("Tenley", "Boyd", 13, List.of(member));

        assertEquals("Tenley", dto.getFirstName());
        assertEquals(13, dto.getAge());
        assertEquals(1, dto.getOtherMembers().size());
    }

    @Test
    public void childAlertDTOTest() {

        HouseMemberDTO member = new HouseMemberDTO("John", "Boyd");

        ChildAlertDTO dto = new ChildAlertDTO();
        dto.setFirstName("Tenley");
        dto.setLastName("Boyd");
        dto.setAge(13);
        dto.setOtherMembers(List.of(member));

        assertEquals("Tenley", dto.getFirstName());
        assertEquals("Boyd", dto.getLastName());
        assertEquals(13, dto.getAge());
        assertEquals(1, dto.getOtherMembers().size());
        assertEquals("John", dto.getOtherMembers().getFirst().getFirstName());
    }
}