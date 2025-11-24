package com.mikael.safetynet.Service;

import com.mikael.safetynet.model.FireStation;
import com.mikael.safetynet.repository.FireStationRespository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class FireStationService {
    private final FireStationRespository fireStationRepository;

    public FireStationService(FireStationRespository fireStationRespository) {
        this.fireStationRepository = fireStationRespository;
    }

    public List<FireStation> allFirestations() throws IOException {
        return fireStationRepository.getAll();
    }

    public FireStation getByAddress(String address) throws IOException {
        return fireStationRepository.getByAddress(address);
    }

    public FireStation create(FireStation fireStation) {
        return fireStationRepository.save(fireStation);
    }

    public FireStation update(FireStation fireStation) throws IOException {
        return fireStationRepository.update(fireStation);
    }

    public void delete(String address) throws IOException {
        fireStationRepository.remove(address);
    }
}
