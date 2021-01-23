package com.tonioostblok.garageapi.services;

import com.tonioostblok.garageapi.entities.Part;
import com.tonioostblok.garageapi.repositories.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("partService")
public class PartService {
    @Autowired
    private PartRepository partRepository;

    public Iterable<Part> getAllParts() {
        return partRepository.findAll();
    }

    public Part getPart(int id) {
        return partRepository.findById(id).get();
    }

    public void deletePart(int id) {
        partRepository.deleteById(id);
    }

    public Part addOrUpdatePart(Part part) {
        return partRepository.save(part);
    }
}
