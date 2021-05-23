package com.picposter.api;

import com.picposter.service.api.ImageServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageController {
    private final ImageServiceAPI imageService;

    @Autowired
    public ImageController(@Qualifier("imageService") ImageServiceAPI imageService) {
        this.imageService = imageService;
    }

    @RequestMapping(path = "upload", method = RequestMethod.POST)
    public ResponseEntity<String> uploadImage(@RequestBody @NonNull MultipartFile imageFile){
        imageService.saveImage(imageFile);

        return new ResponseEntity<>("Yeaa", HttpStatus.CREATED);
    }


}
