package com.tonioostblok.garageapi.controllers;

import com.tonioostblok.garageapi.entities.ErrorMessage;
import com.tonioostblok.garageapi.entities.Repair;
import com.tonioostblok.garageapi.services.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RepairController {
    @Autowired
    private RepairService repairService;

    @RequestMapping(value = "/repair", method = RequestMethod.POST)
    public ResponseEntity<?> addRepair(@RequestBody Repair repair) {
        try {
            int id = repairService.addOrUpdateRepair(repair);
            Repair createdRepair = repairService.getAllRepairInformation(id);
            return ResponseEntity.ok(createdRepair);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/repair", method = RequestMethod.GET)
    public ResponseEntity<?> getAllRepair() {
        try {
            Iterable<Repair> repair = repairService.getAllRepairs();
            return ResponseEntity.ok(repair);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessage("Something went wrong whilst trying to fetch all repairs."));
        }
    }
    @RequestMapping(value = "/repair/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getRepair(@PathVariable(value = "id") int repair_id) {
        try {
            Repair repair = repairService.getRepair(repair_id);
            return ResponseEntity.ok(repair);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessage("The repair that you have tried to fetch does not exist."));
        }
    }

    @RequestMapping(value = "/repair/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteRepair(@PathVariable(value = "id") int repair_id) {
        try {
            repairService.deleteRepair(repair_id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessage("The repair that you have tried to delete does not exist."));
        }
    }

    @RequestMapping(value = "/repair", method = RequestMethod.PUT)
    public ResponseEntity<?> updateRepair(@RequestBody Repair repair) {
        try {
            int id = repairService.addOrUpdateRepair(repair);
            Repair updatedRepair = repairService.getAllRepairInformation(id);
            return ResponseEntity.ok(updatedRepair);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessage("The repair that you have tried to update does not exist."));
        }
    }
}
