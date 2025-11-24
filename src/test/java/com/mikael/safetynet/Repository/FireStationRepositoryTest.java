package com.mikael.safetynet.Repository;

import com.mikael.safetynet.model.FireStation;
import com.mikael.safetynet.repository.FireStationRespository;
import com.mikael.safetynet.util.DataReaderUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FireStationRepositoryTest {

    @Test
    public void getByAddress() throws IOException {
        FireStation fireStation = new FireStation(); fireStation.setAddress("1509 Culver St"); fireStation.setStation("3");
        DataReaderUtil reader = Mockito.mock(DataReaderUtil.class);
        Mockito.when(reader.getAllFireStations()).thenReturn(List.of(fireStation));

        FireStationRespository repository = new FireStationRespository(reader);
        FireStation result = repository.getByAddress("1509 Culver St");

        assertNotNull(result);
    }

    @Test
    public void save() throws IOException {
        FireStation fireStation = new FireStation(); fireStation.setAddress("1509 Culver St"); fireStation.setStation("3");
        DataReaderUtil reader = Mockito.mock(DataReaderUtil.class);
        Mockito.when(reader.getAllFireStations()).thenReturn(new ArrayList<>());

        FireStationRespository repository = new FireStationRespository(reader);
        repository.save(fireStation);

        assertEquals(1, repository.getAll().size());
    }

    @Test
    public void update() throws IOException {
        FireStation fireStation = new FireStation(); fireStation.setAddress("29 15th St"); fireStation.setStation("3");
        DataReaderUtil reader = Mockito.mock(DataReaderUtil.class);
        Mockito.when(reader.getAllFireStations()).thenReturn(new ArrayList<>(List.of(fireStation)));

        FireStationRespository repository = new FireStationRespository(reader);
        FireStation updated = new FireStation(); updated.setAddress("29 15th St"); updated.setStation("1");

        repository.update(updated);

        assertEquals("1", repository.getByAddress("29 15th St").getStation());
    }

    @Test
    public void remove() throws IOException {
        FireStation fireStation = new FireStation(); fireStation.setAddress("1509 Culver St"); fireStation.setStation("1");
        DataReaderUtil reader = Mockito.mock(DataReaderUtil.class);
        Mockito.when(reader.getAllFireStations()).thenReturn(new ArrayList<>(List.of(fireStation)));

        FireStationRespository repository = new FireStationRespository(reader);
        repository.remove("1509 Culver St");

        assertEquals(0, repository.getAll().size());
    }
}
