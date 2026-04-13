package co.istad.quizera.project.util;

import org.springframework.web.multipart.MultipartFile;

public class FileValidator {

    // 5 MB limit
    private static final long MAX_SIZE = 5 * 1024 * 1024;

    public static void validateImage(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        if (file.getSize() > MAX_SIZE) {
            throw new RuntimeException("File size must be less than 5MB");
        }

        String contentType = file.getContentType();

        if (contentType == null || !contentType.startsWith("image/")) {
            throw new RuntimeException("Only image files are allowed");
        }
    }
}