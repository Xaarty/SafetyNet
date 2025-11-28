package com.mikael.safetynet.controller;

import com.mikael.safetynet.model.FireStation;
import com.mikael.safetynet.Service.FireStationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/firestation")
public class FireStationController {

    private static final Logger logger = LoggerFactory.getLogger(FireStationController.class);

    private final FireStationService fireStationService;

    public FireStationController(FireStationService fireStationService) {
        this.fireStationService = fireStationService;
    }

    @GetMapping
    public List<FireStation> all() throws IOException {
        logger.info("GET /firestation ");
        try {
            return fireStationService.allFirestations();
        } catch (Exception e) {
            logger.error("GET /firestation - erreur", e);
            throw e;
        }
    }

    @GetMapping("/{address}")
    public FireStation getByAddress(@PathVariable String address) throws IOException {
        logger.info("GET /firestation/{}", address);
        try {
            return fireStationService.getByAddress(address);
        } catch (Exception e) {
            logger.error("GET /firestation/{} - erreur", address, e);
            throw e;
        }
    }

    @PostMapping
    public FireStation addFireStation(@RequestBody FireStation fireStation) {
        logger.info("POST /firestation - address={} station={}",
                fireStation.getAddress(), fireStation.getStation());
        try {
            return fireStationService.create(fireStation);
        } catch (Exception e) {
            logger.error("POST /firestation - erreur", e);
            throw e;
        }
    }

    @PutMapping
    public FireStation updateFireStation(@RequestBody FireStation fireStation) throws IOException {
        logger.info("PUT /firestation - address={} station={}",
                fireStation.getAddress(), fireStation.getStation());
        try {
            return fireStationService.update(fireStation);
        } catch (Exception e) {
            logger.error("PUT /firestation - erreur", e);
            throw e;
        }
    }

    @DeleteMapping("/{address}")
    public void deleteFireStation(@PathVariable String address) throws IOException {
        logger.info("DELETE /firestation/{}", address);
        try {
            fireStationService.delete(address);
        } catch (Exception e) {
            logger.error("DELETE /firestation/{} - erreur", address, e);
            throw e;
        }
    }
}
