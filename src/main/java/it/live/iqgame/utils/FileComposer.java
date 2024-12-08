package it.live.iqgame.utils;

import it.live.iqgame.exception.MainException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileComposer {
    private static final String MAIN_UPLOAD_DIRECTORY = "/app/uploads/DOCUMENTS";

    public static String imageUploader(MultipartFile multipartFile) {
        // Validate content type
        String contentType = multipartFile.getContentType();
        if (contentType == null) {
            throw new MainException("File type is missing!");
        }

        String extension = contentType.split("/")[1];
        if (!isValidExtension(extension)) {
            throw new MainException("No access. Only png, jpg, jpeg, svg formats are valid!");
        }

        // Generate unique file name
        String fileName = UUID.randomUUID() + "." + extension;
        Path uploadDirectory = Paths.get(MAIN_UPLOAD_DIRECTORY);

        try {
            // Create directories if they don't exist
            if (!Files.exists(uploadDirectory)) {
                Files.createDirectories(uploadDirectory);
            }

            // Define the full file path
            Path filePath = uploadDirectory.resolve(fileName);

            // Save the file to the path
            multipartFile.transferTo(filePath.toFile());
        } catch (IOException e) {
            // Provide detailed error information
            throw new MainException("Error while uploading image: " + e.getMessage());
        }

        return fileName;
    }

    private static boolean isValidExtension(String extension) {
        return extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg") || extension.equalsIgnoreCase("svg");
    }
}
