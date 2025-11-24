package com.mikael.safetynet.Controller;

import com.mikael.safetynet.DTO.*;
import com.mikael.safetynet.controller.AlertController;
import com.mikael.safetynet.Service.AlertService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class AlertControllerTest {

    @Test
    void childAlert() throws IOException {
        AlertService mockService = Mockito.mock(AlertService.class);
        List<HouseMemberDTO> others = List.of(new HouseMemberDTO("John","Boyd"));
        ChildAlertDTO child = new ChildAlertDTO("Tenley","Boyd",8, others);
        Mockito.when(mockService.getChildAlert("1509 Culver St")).thenReturn(List.of(child));

        AlertController controller = new AlertController(mockService);
        var result = controller.childAlert("1509 Culver St");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Tenley", result.getFirst().getFirstName());
        Mockito.verify(mockService).getChildAlert("1509 Culver St");
    }

    @Test
    void phoneAlert() throws IOException {
        AlertService mockService = Mockito.mock(AlertService.class);
        Mockito.when(mockService.getPhoneAlert(1)).thenReturn(List.of("841-874-6512","841-874-6513"));

        AlertController controller = new AlertController(mockService);
        var result = controller.phoneAlert(1);

        assertNotNull(result);
        assertTrue(result.contains("841-874-6512"));
        Mockito.verify(mockService).getPhoneAlert(1);
    }

    @Test
    void fire() throws IOException {
        AlertService mockService = Mockito.mock(AlertService.class);
        ResidentFireDTO resident = new ResidentFireDTO("John","Boyd","841-874-6512",34, List.of("medicaments"), List.of("a1"));
        FireDTO dto = new FireDTO(Optional.of(3), List.of(resident));
        Mockito.when(mockService.getFireByAddress("1509 Culver St")).thenReturn(dto);

        AlertController controller = new AlertController(mockService);
        var result = controller.fire("1509 Culver St");

        assertNotNull(result);
        assertTrue(result.getStationNumber().isPresent());
        assertEquals(1, result.getResidents().size());
        Mockito.verify(mockService).getFireByAddress("1509 Culver St");
    }

    @Test
    void personInfo() throws IOException {
        AlertService mockService = Mockito.mock(AlertService.class);
        PersonInfoDTO info = new PersonInfoDTO("John","Boyd","1509 Culver St",34,"jaboyd@email.com", List.of("médicament"), List.of("allergies"));
        Mockito.when(mockService.getPersonInfoByLastName("Boyd")).thenReturn(List.of(info));

        AlertController controller = new AlertController(mockService);
        var result = controller.personInfo("Boyd");

        assertNotNull(result);
        assertEquals("Boyd", result.getFirst().getLastName());
        Mockito.verify(mockService).getPersonInfoByLastName("Boyd");
    }

    @Test
    void floodByStations() throws IOException {
        AlertService mockService = Mockito.mock(AlertService.class);
        FloodPersonDTO fp = new FloodPersonDTO("John","Boyd","841-874-6512",34,List.of(),List.of());
        Map<String, List<FloodPersonDTO>> map = new HashMap<>();
        map.put("1509 Culver St", List.of(fp));
        Mockito.when(mockService.getFloodByStations(List.of(1,2))).thenReturn(map);

        AlertController controller = new AlertController(mockService);
        var result = controller.floodByStations(List.of(1,2));

        assertNotNull(result);
        assertTrue(result.containsKey("1509 Culver St"));
        Mockito.verify(mockService).getFloodByStations(List.of(1,2));
    }

    @Test
    void personsByStation() throws IOException {
        AlertService mockService = Mockito.mock(AlertService.class);
        PersonCoveredDTO p = new PersonCoveredDTO("John","Boyd","1509 Culver St","841-874-6512",34);
        PersonsByStationDTO dto = new PersonsByStationDTO(List.of(p),1,0);
        Mockito.when(mockService.getPersonsByStation(1)).thenReturn(dto);

        AlertController controller = new AlertController(mockService);
        var result = controller.personsByStation(1);

        assertNotNull(result);
        assertEquals(1, result.getPersons().size());
        Mockito.verify(mockService).getPersonsByStation(1);
    }

    @Test
    void communityEmail() throws IOException {
        AlertService mockService = Mockito.mock(AlertService.class);
        Mockito.when(mockService.getCommunityEmails("Culver")).thenReturn(List.of("jaboyd@email.com","drk@email.com"));

        AlertController controller = new AlertController(mockService);
        var result = controller.communityEmail("Culver");

        assertEquals(2, result.size());
        Mockito.verify(mockService).getCommunityEmails("Culver");
    }

}
