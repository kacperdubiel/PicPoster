package com.picposter.api;

import com.picposter.domain.Follow;
import com.picposter.service.api.ImageServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ImageController {
    private final ImageServiceAPI imageService;

    @Autowired
    public ImageController(@Qualifier("imageService") ImageServiceAPI imageService) {
        this.imageService = imageService;
    }

    @RequestMapping(path = "upload", method = RequestMethod.POST)
    public ResponseEntity<String> uploadImage(@RequestBody @NonNull MultipartFile imageFile){
        // TODO: Better responses?
        if(imageService.saveImage(imageFile))
            return new ResponseEntity<>("Image uploaded.", HttpStatus.OK);
        return new ResponseEntity<>("Error", HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping(path = "upload/{filename}", method = RequestMethod.GET)
    public ResponseEntity<Resource> getImage(@PathVariable("filename") String filename, HttpServletRequest request){
        Resource resource = imageService.getImage(filename);

        if(resource != null){
            String contentType = null;
            try {
                contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            // Fallback to the default content type if type could not be determined
            if(contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        }
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }



}
