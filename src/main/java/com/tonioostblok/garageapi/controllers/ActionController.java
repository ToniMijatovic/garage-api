package com.tonioostblok.garageapi.controllers;

import com.tonioostblok.garageapi.entities.Action;
import com.tonioostblok.garageapi.entities.ErrorMessage;
import com.tonioostblok.garageapi.services.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ActionController {

    @Autowired
    private ActionService actionService;

    @RequestMapping(value = "/action/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> action(@PathVariable(value = "id") int action_id) {
        try {
            Action action = actionService.getAction(action_id);
            return ResponseEntity.ok(action);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessage("The action that you have tried to fetch does not exist."));
        }
    }

    @RequestMapping(value = "/action", method = RequestMethod.POST)
    public ResponseEntity<?> addAction(@RequestBody Action action) {
        try {
            Action savedAction = actionService.addOrUpdateAction(action);
            return ResponseEntity.ok(savedAction);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessage("Something went wrong whilst trying to add an action."));
        }
    }

    @RequestMapping(value = "/action", method = RequestMethod.PUT)
    public ResponseEntity<?> updateAction(@RequestBody Action action) {
        try {
            Action savedAction = actionService.addOrUpdateAction(action);
            return ResponseEntity.ok(savedAction);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessage("Something went wrong whilst trying to update an action."));
        }
    }

    @RequestMapping(value = "/action/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAction(@PathVariable(value = "id") int action_id) {
        try {
            actionService.deleteAction(action_id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body( new ErrorMessage("Something went wrong whilst trying to delete an action."));
        }
    }
}
