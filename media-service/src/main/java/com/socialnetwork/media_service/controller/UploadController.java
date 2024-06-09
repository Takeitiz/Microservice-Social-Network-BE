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

    @PostMapping("/post")
    public ResponseEntity<String> uploadPostImage(@RequestParam MultipartFile multipartFile, @RequestParam(required = false) String imageName) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (bi == null) {
            return new ResponseEntity<>("Image not valid!", HttpStatus.BAD_REQUEST);
        }
        String publicId = imageName != null ? imageName : multipartFile.getOriginalFilename();
        Map<String, Object> result = cloudinaryService.upload(multipartFile, "post", publicId);
        String imageUrl = (String) result.get("url");
        return new ResponseEntity<>(imageUrl, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<String> uploadAvatar(@RequestParam MultipartFile multipartFile, @RequestParam(required = false) String imageName) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (bi == null) {
            return new ResponseEntity<>("Image not valid!", HttpStatus.BAD_REQUEST);
        }
        String publicId = imageName != null ? imageName : multipartFile.getOriginalFilename();
        Map<String, Object> result = cloudinaryService.upload(multipartFile, "user", publicId);
        String imageUrl = (String) result.get("url");
        return new ResponseEntity<>(imageUrl, HttpStatus.OK);
    }

    @GetMapping("/image")
    public ResponseEntity<String> fetchImage(@RequestParam String publicId) {
        String imageUrl = cloudinaryService.fetchImageUrl(publicId);
        if (imageUrl.equals("Image not found or error retrieving the image")) {
            return new ResponseEntity<>(imageUrl, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(imageUrl, HttpStatus.OK);
    }
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