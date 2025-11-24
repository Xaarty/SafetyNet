package com.mikael.safetynet.Controller;

import com.mikael.safetynet.controller.FireStationController;
import com.mikael.safetynet.model.FireStation;
import com.mikael.safetynet.Service.AlertService;
import com.mikael.safetynet.Service.FireStationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FireStationControllerTest {

    @Test
    void all() throws IOException {
        FireStationService mockService = Mockito.mock(FireStationService.class);
        AlertService mockAlert = Mockito.mock(AlertService.class);
        FireStation f = new FireStation(); f.setAddress("1509 Culver St"); f.setStation("3");
        Mockito.when(mockService.allFirestations()).thenReturn(List.of(f));

        FireStationController controller = new FireStationController(mockService);
        var result = controller.all();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("1509 Culver St", result.getFirst().getAddress());
        Mockito.verify(mockService).allFirestations();
    }

    @Test
    void getByAddress() throws IOException {
        FireStationService mockService = Mockito.mock(FireStationService.class);
        AlertService mockAlert = Mockito.mock(AlertService.class);
        FireStation f = new FireStation(); f.setAddress("1509 Culver St"); f.setStation("3");
        Mockito.when(mockService.getByAddress("1509 Culver St")).thenReturn(f);

        FireStationController controller = new FireStationController(mockService);
        var result = controller.getByAddress("1509 Culver St");

        assertEquals("3", result.getStation());
        Mockito.verify(mockService).getByAddress("1509 Culver St");
    }

    @Test
    void add_update_delete() throws IOException {
        FireStationService mockService = Mockito.mock(FireStationService.class);
        AlertService mockAlert = Mockito.mock(AlertService.class);
        FireStationController controller = new FireStationController(mockService);

        FireStation input = new FireStation(); input.setAddress("1509 Culver St"); input.setStation("3");
        Mockito.when(mockService.create(input)).thenReturn(input);
        var created = controller.addFireStation(input);
        assertEquals("1509 Culver St", created.getAddress());
        Mockito.verify(mockService).create(input);

        FireStation updated = new FireStation(); updated.setAddress("1508 Culver St"); updated.setStation("3");
        Mockito.when(mockService.update(updated)).thenReturn(updated);
        var up = controller.updateFireStation(updated);
        assertEquals("3", up.getStation());
        Mockito.verify(mockService).update(updated);

        controller.deleteFireStation("1509 Culver St");
        Mockito.verify(mockService).delete("1509 Culver St");
    }
}
