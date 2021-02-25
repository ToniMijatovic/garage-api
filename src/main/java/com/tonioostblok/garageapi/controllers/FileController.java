package com.tonioostblok.garageapi.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.tonioostblok.garageapi.entities.ErrorMessage;
import com.tonioostblok.garageapi.entities.File;
import com.tonioostblok.garageapi.services.CarService;
import com.tonioostblok.garageapi.services.FileService;
import com.tonioostblok.garageapi.services.FilesStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@RestController
public class FileController {

    @Autowired
    FilesStorageServiceImpl storageService;
    @Autowired
    FileService fileService;
    @Autowired
    CarService carService;

    @PostMapping("/file/{id}")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file , @RequestParam("name") String name, @PathVariable(value = "id") int car_id) {
        try {
            String url = storageService.save(file);
            File createdFile = fileService.addOrUpdateFile(new File(name, url));

            carService.addFileToCar(createdFile, car_id);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessage("Something went wrong whilst trying to add a file to a car"));
        }
    }

    @DeleteMapping("/file/{file_id}/{car_id}")
    public ResponseEntity<?>  deleteFile(@PathVariable(value = "file_id") int file_id, @PathVariable(value = "car_id") int car_id) {
        try {
            File file = fileService.getFile(file_id);
            storageService.deleteFile(file.getUrl());
            carService.removeFileFromCar(car_id, file);
            fileService.deleteFile(file.getId());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessage("Something went wrong whilst trying to delete a file from a car"));
        }
    }


    @GetMapping("/file/{id}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable(value = "id") int file_id) throws IOException {
        try{
            File chosenFile = fileService.getFile(file_id);
            Resource file = storageService.load(chosenFile.getUrl());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ErrorMessage("Something went wrong whilst trying to fetch the file"));
        }
    }
}