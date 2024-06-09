package com.socialnetwork.media_service.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;
    private final String cloudName;

    public CloudinaryService() {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("cloud_name", "dwuvvf1tm");
        valuesMap.put("api_key", "935826224131117");
        valuesMap.put("api_secret", "k1E47y6AjRzzsyEgCpW0JjmAEX8");
        cloudinary = new Cloudinary(valuesMap);
        cloudName = valuesMap.get("cloud_name");
    }

    public Map<String, Object> upload(MultipartFile multipartFile, String folderName, String publicId) throws IOException {
        // Check if the image already exists and delete it if it does
        try {
            cloudinary.api().resource(publicId, ObjectUtils.emptyMap());
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (Exception ignored) {
            // The image does not exist, so nothing to delete
        }

        // Upload the new image
        File file = convert(multipartFile);
        Map<String, Object> uploadParams = new HashMap<>();
        uploadParams.put("folder", folderName);
        uploadParams.put("public_id", publicId);
        Map<String, Object> result = cloudinary.uploader().upload(file, uploadParams);
        if (!Files.deleteIfExists(file.toPath())) {
            throw new IOException("Failed to delete temporary file: " + file.getAbsolutePath());
        }
        return result;
    }
    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try (FileOutputStream fo = new FileOutputStream(file)) {
            fo.write(multipartFile.getBytes());
        }
        return file;
    }

    public String fetchImageUrl(String publicId) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("resource_type", "image");
            publicId = "user/" + publicId;
            Map result = cloudinary.api().resource(publicId, params);
            return (String) result.get("url");
        } catch (Exception e) {
            return "Image not found or error retrieving the image";
        }
    }
}

