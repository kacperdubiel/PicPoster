package com.picposter.service.api;

import org.springframework.web.multipart.MultipartFile;


public interface ImageServiceAPI {
    boolean saveImage(MultipartFile imageFile);
}
