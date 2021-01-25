package com.tonioostblok.garageapi.controllers;

import com.tonioostblok.garageapi.entities.Car;
import com.tonioostblok.garageapi.entities.Repair;
import com.tonioostblok.garageapi.services.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RepairController {
    @Autowired
    private RepairService repairService;

    @RequestMapping(value = "/repair", method = RequestMethod.POST)
    public ResponseEntity<?> addRepair(@RequestBody Repair repair, HttpServletRequest request) {
        try {
            int id = repairService.addOrUpdateRepair(repair);
            Repair createdRepair = repairService.getAllRepairInformation(id);
            return ResponseEntity.ok(createdRepair);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/repair/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> car(@PathVariable(value = "id") int repair_id, HttpServletRequest request) {
        try {
            Repair repair = repairService.getRepair(repair_id);
            return ResponseEntity.ok(repair);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\":\"The repair that you have tried to fetch does not exist.\"}");
        }
    }
}
