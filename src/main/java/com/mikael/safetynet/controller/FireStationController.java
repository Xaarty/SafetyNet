package com.mikael.safetynet.controller;

import com.mikael.safetynet.model.FireStation;
import com.mikael.safetynet.Service.FireStationService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/firestation")
public class FireStationController {

    private final FireStationService fireStationService;

    public FireStationController(FireStationService fireStationService) {
        this.fireStationService = fireStationService;
    }

    @GetMapping
    public List<FireStation> all() throws IOException {
        System.out.println("DEBUG: FireStationController.all() called");
        return fireStationService.allFirestations();
    }

    @GetMapping("/{address}")
    public FireStation getByAddress(@PathVariable String address) throws IOException {
        return fireStationService.getByAddress(address);
    }

    @PostMapping
    public FireStation addFireStation(@RequestBody FireStation fireStation) {
        return fireStationService.create(fireStation);
    }

    @PutMapping
    public FireStation updateFireStation(@RequestBody FireStation fireStation) throws IOException {
        return fireStationService.update(fireStation);
    }

    @DeleteMapping("/{address}")
    public void deleteFireStation(@PathVariable String address) throws IOException {
        fireStationService.delete(address);
    }
}
