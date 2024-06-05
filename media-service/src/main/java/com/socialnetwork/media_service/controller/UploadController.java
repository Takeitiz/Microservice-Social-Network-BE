package com.socialnetwork.media_service.controller;


import com.socialnetwork.media_service.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/upload")
@RequiredArgsConstructor
public class UploadController {
    private final CloudinaryService cloudinaryService;
    @PostMapping()
    public ResponseEntity<String> upload(@RequestParam MultipartFile multipartFile) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (bi == null) {
            return new ResponseEntity<>("Image non valide!", HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);
        return new ResponseEntity<>("https://res.cloudinary.com/dwuvvf1tm/image/upload/v1717578107/"+ (String) result.get("public_id") + ".png", HttpStatus.OK);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
//        Content content = contentService.getContentById(id);
//        if (content == null) {
//            return new ResponseEntity<>("Content didnt exists", HttpStatus.NOT_FOUND);
//        }
//        String cloudinaryImageId = content.getImage_id();
//        try {
//            cloudinaryService.delete(cloudinaryImageId);
//        } catch (IOException e) {
//            return new ResponseEntity<>("Failed to delete image", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        contentService.deleteContent(id);
//        return new ResponseEntity<>("Delete content success", HttpStatus.OK);
//    }

}
