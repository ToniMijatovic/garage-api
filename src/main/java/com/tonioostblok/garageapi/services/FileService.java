package com.tonioostblok.garageapi.services;

import com.tonioostblok.garageapi.entities.File;
import com.tonioostblok.garageapi.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("fileService")
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    public Iterable<File> getAllParts() {
        return fileRepository.findAll();
    }

    public File getFile(int id) {
        return fileRepository.findById(id).get();
    }

    public void deleteFile(int id) {
        fileRepository.deleteById(id);
    }

    public File addOrUpdateFile(File file) {
        return fileRepository.save(file);
    }
}
