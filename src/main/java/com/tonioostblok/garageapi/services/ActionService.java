package com.tonioostblok.garageapi.services;

import com.tonioostblok.garageapi.entities.Action;
import com.tonioostblok.garageapi.repositories.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ActionService")
public class ActionService {
    @Autowired
    private ActionRepository actionRepository;

    public Iterable<Action> getAllActions() {
        return actionRepository.findAll();
    }

    public Action getAction(int id) {
        return actionRepository.findById(id).get();
    }

    public void deleteAction(int id) {
        actionRepository.deleteById(id);
    }

    public Action addOrUpdateAction(Action Action) {
        return actionRepository.save(Action);
    }
}
