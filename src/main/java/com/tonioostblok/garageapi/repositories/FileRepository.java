package com.tonioostblok.garageapi.repositories;

import com.tonioostblok.garageapi.entities.File;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("FileRepository")
public interface FileRepository extends CrudRepository<File, Integer> {
    public File findByName(String name);
}
