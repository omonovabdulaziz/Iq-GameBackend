package it.live.iqgame.utils;

import com.sun.tools.javac.Main;
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
    private static final String MAIN_UPLOAD_DIRECTORY = "DOCUMENTS";

    public static String imageUploader(MultipartFile multipartFile) {
        String extension = Objects.requireNonNull(multipartFile.getContentType()).split("/")[1];
        if (!extension.equalsIgnoreCase("png") && !extension.equalsIgnoreCase("jpg") && !extension.equalsIgnoreCase("jpeg") && !extension.equalsIgnoreCase("svg")) {
            throw new MainException("No access. Only png, jpg, jpeg, svg format valid!");
        }
        String fileName = UUID.randomUUID() + "." + extension;
        Path uploadDirectory = Paths.get(MAIN_UPLOAD_DIRECTORY);
        try {
            if (!Files.exists(uploadDirectory)) Files.createDirectories(uploadDirectory);
            Path filePath = uploadDirectory.resolve(fileName);
            multipartFile.transferTo(filePath);
        } catch (Exception e) {
            throw new MainException("Error while uploading image!");
        }
        return fileName;

    }
}
