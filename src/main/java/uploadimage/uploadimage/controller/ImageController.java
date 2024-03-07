package uploadimage.uploadimage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uploadimage.uploadimage.service.StorageService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {
    private final StorageService service;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
       String uploadImage = service.uploadImage(file);
       return  ResponseEntity.status(HttpStatus.OK)
               .body(uploadImage);
    }

}
