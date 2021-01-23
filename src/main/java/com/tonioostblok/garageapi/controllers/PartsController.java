package com.tonioostblok.garageapi.controllers;

import com.tonioostblok.garageapi.entities.Part;
import com.tonioostblok.garageapi.services.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PartsController {

    @Autowired
    private PartService partService;

    @RequestMapping(value = "/part/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> part(@PathVariable(value = "id") int part_id, HttpServletRequest request) {
        try {
            Part part = partService.getPart(part_id);
            return ResponseEntity.ok(part);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\":\"The part that you have tried to fetch does not exist.\"}");
        }
    }

    @RequestMapping(value = "/part", method = RequestMethod.POST)
    public ResponseEntity<?> addPart(@RequestBody Part part, HttpServletRequest request) {
        try {
            Part savedPart = partService.addOrUpdatePart(part);
            return ResponseEntity.ok(savedPart);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\":\"Something went wrong whilst trying to add a part.\"}");
        }
    }
    @RequestMapping(value = "/part", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePart(@RequestBody Part part, HttpServletRequest request) {
        try {
            Part savedPart = partService.addOrUpdatePart(part);
            return ResponseEntity.ok(savedPart);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\":\"Something went wrong whilst trying to update a new part.\"}");
        }
    }
    @RequestMapping(value = "/part/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePart(@PathVariable(value = "id") int part_id, HttpServletRequest request) {
        try {
            partService.deletePart(part_id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\":\"Something went wrong whilst trying to update a new part.\"}");
        }
    }
}
