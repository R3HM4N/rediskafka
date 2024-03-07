package uploadimage.uploadimage.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uploadimage.uploadimage.entity.ImageData;
import uploadimage.uploadimage.repository.StorageRepository;
import uploadimage.uploadimage.util.ImageUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final StorageRepository storageRepository;
    private final FileService fileService;
    private static final String PRODUCT_IMAGES_FOLDER_NAME = "Product-images";


    public String uploadImage(MultipartFile image) throws IOException {
        String uploadedImageURL = fileService.uploadImage(image, PRODUCT_IMAGES_FOLDER_NAME);
        ImageData uploadedImage = new ImageData(uploadedImageURL);
        uploadedImage.setImageUrl(uploadedImageURL);

        storageRepository.save(uploadedImage);
        if (uploadedImage != null) {
            return "File added: " + uploadedImage;
        }

        return null;
    }

}
