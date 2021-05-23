package com.picposter.service;

import com.picposter.service.api.ImageServiceAPI;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    public boolean saveImage(MultipartFile imageFile) {

        try {
            if(imageFile == null)
                return false;

            String extension = FilenameUtils.getExtension(imageFile.getOriginalFilename());
            if(extension == null)
                return false;
            extension = extension.toLowerCase();

            if(!extension.equals("jpg") && !extension.equals("jpeg") && !extension.equals("png"))
                return false;

            byte[] bytes = imageFile.getBytes();
            String newFilename = UUID.randomUUID() + "." + extension;
            Path path = Paths.get(FILE_DIR + newFilename);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
