package com.mikael.safetynet.Service;

import com.mikael.safetynet.model.FireStation;
import com.mikael.safetynet.repository.FireStationRespository;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FireStationServiceTest {

    FireStationRespository mockRepo = mock(FireStationRespository.class);
    FireStationService service = new FireStationService(mockRepo);

    @Test
    public void allFirestations() throws IOException {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("1509 Culver St");
        fireStation.setStation(String.valueOf(3));
        when(mockRepo.getAll()).thenReturn(List.of(fireStation));

        List<FireStation> result = service.allFirestations();

        assertEquals(1, result.size());
        verify(mockRepo).getAll();
    }

    @Test
    public void getByAddress() throws IOException {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("1509 Culver St");
        when(mockRepo.getByAddress("1509 Culver St")).thenReturn(fireStation);

        FireStation result = service.getByAddress("1509 Culver St");

        assertEquals("1509 Culver St", result.getAddress());
        verify(mockRepo).getByAddress("1509 Culver St");
    }

    @Test
    public void create() {
        FireStation fireStation = new FireStation();
        when(mockRepo.save(fireStation)).thenReturn(fireStation);

        FireStation result = service.create(fireStation);

        assertEquals(fireStation, result);
        verify(mockRepo).save(fireStation);
    }

    @Test
    public void update() throws IOException {
        FireStation fireStation = new FireStation();
        when(mockRepo.update(fireStation)).thenReturn(fireStation);

        FireStation result = service.update(fireStation);

        assertEquals(fireStation, result);
        verify(mockRepo).update(fireStation);
    }

    @Test
    public void delete() throws IOException {
        service.delete("1509 Culver St");
        verify(mockRepo).remove("1509 Culver St");
    }
}
