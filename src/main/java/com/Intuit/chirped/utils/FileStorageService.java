package com.Intuit.chirped.utils;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {
    private final Path rootLocation = Paths.get("uploaded");

    public String storeFile(MultipartFile file, String folder) throws IOException {
        try {
            Path targetLocation = this.rootLocation.resolve(folder);
            Files.createDirectories(targetLocation);

            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path targetPath = targetLocation.resolve(filename);

            Files.copy(file.getInputStream(), targetPath);

            String fullPath = targetPath.toUri().toString();
            String myPath = rootLocation + folder + "/" + filename;
            String path = rootLocation.relativize(targetPath).toString();

            return path;

        } catch (IOException e) {
            System.err.println("Error occurred while storing file: " + e.getMessage());
            throw e;
        }
    }

    public void deleteFile(String fileUrl) throws Exception {
        try {
            String workingDir = System.getProperty("user.dir");
            URI uri = new URI("file://" + workingDir + "/uploaded/" + fileUrl);
            Path file = Paths.get(uri);
            Files.deleteIfExists(file);
        } catch (Exception e) {
            System.err.println("Error occurred while delete file: " + e.getMessage());
            throw e;
        }
    }


}
