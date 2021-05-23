package com.picposter.service.api;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface ImageServiceAPI {
    boolean saveImage(MultipartFile imageFile);
    Resource getImage(String filename);
}
