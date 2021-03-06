package com.picposter.service;

import com.picposter.service.api.ImageServiceAPI;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service("imageService")
public class ImageService implements ImageServiceAPI {
    @Value("${file.upload-dir}")
    String FILE_DIR;

    @Autowired
    public ImageService() {

    }

    @Override
    public String saveImage(MultipartFile imageFile) {
        String newFilename;
        try {
            if(imageFile == null)
                return null;

            String extension = FilenameUtils.getExtension(imageFile.getOriginalFilename());
            if(extension == null)
                return null;
            extension = extension.toLowerCase();

            if(!extension.equals("jpg") && !extension.equals("jpeg") && !extension.equals("png"))
                return null;

            byte[] bytes = imageFile.getBytes();
            newFilename = UUID.randomUUID() + "." + extension;
            Path path = Paths.get(FILE_DIR + newFilename);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return newFilename;
    }

    @Override
    public Resource getImage(String filename){
        try {
            Path path = Paths.get(FILE_DIR + filename);
            Resource res = new UrlResource(path.toUri());
            if(res.exists())
                return res;
            else
                return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
