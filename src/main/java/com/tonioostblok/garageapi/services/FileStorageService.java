package com.tonioostblok.garageapi.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    public void init();

    public String save(MultipartFile file);

    public Resource load(String filename);

    public void deleteFile(String file);
}
