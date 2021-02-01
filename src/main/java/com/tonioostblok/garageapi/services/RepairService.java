package com.tonioostblok.garageapi.services;

import com.tonioostblok.garageapi.entities.Action;
import com.tonioostblok.garageapi.entities.Car;
import com.tonioostblok.garageapi.entities.Part;
import com.tonioostblok.garageapi.entities.Repair;
import com.tonioostblok.garageapi.repositories.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service("repairService")
public class RepairService {
    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private CarService carService;
    @Autowired
    private PartService partService;
    @Autowired
    private ActionService actionService;

    public Iterable<Repair> getAllRepairs() {
        return repairRepository.findAll();
    }

    public Repair getRepair(int id) {
        return repairRepository.findById(id).get();
    }

    public void deleteRepair(int id) {
        repairRepository.deleteById(id);
    }

    public int addOrUpdateRepair(Repair repair) {
        Repair savedRepair = repairRepository.save(repair);
        return savedRepair.getId();
    }

    public Repair getAllRepairInformation(int id) {
        Repair repair = this.getRepair(id);

        int car_id = repair.getCar().getId();
        if(car_id > 0) {
            repair.setCar(carService.getCar(car_id));
        }

        Collection<Part> partsToAdd = new java.util.ArrayList<>(Collections.emptyList());

        Collection<Part> parts = repair.getParts();
        for (Part part : parts){
            Part fetchedPart = partService.getPart(part.getId());
            if(fetchedPart.getId() != 0){
                partsToAdd.add(fetchedPart);
            }
        }

        repair.setParts(partsToAdd);

        Collection<Action> actionsToAdd = new java.util.ArrayList<>(Collections.emptyList());

        Collection<Action> actions = repair.getActions();
        for (Action action : actions){
            Action fetchedAction = actionService.getAction(action.getId());
            if(fetchedAction.getId() != 0){
                actionsToAdd.add(fetchedAction);
            }
        }

        repair.setActions(actionsToAdd);

        return repair;
    }

    public Repair insertRepair(Repair repair) {
        Car car = carService.getCar(repair.getCar().getId());

        Collection<Part> partsToAdd = Collections.emptySet();
        Collection<Part> parts = repair.getParts();

        for (Part part : parts){
            Part fetchedPart = partService.getPart(part.getId());
            if(fetchedPart.getId() != 0){
                partsToAdd.add(fetchedPart);
            }
        }

        repair.setCar(car);
        repair.setParts(partsToAdd);

        repairRepository.save(repair);

        return repair;
    }
}
