package com.Intuit.chirped.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/files/**")
public class FileRetrievalController {
//    @GetMapping("/{path:.+}")
//    public ResponseEntity<Resource> getFile(@PathVariable("path") String path) {
//        try {
//            Resource file = loadAsResource(path);
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
//                    .body(file);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(null);
//        }
//    }

    private final Path rootLocation = Paths.get("uploaded");

    @GetMapping
    public ResponseEntity<Resource> getFile(HttpServletRequest request) {
        String filePath = extractPathFromPattern(request);

        try {
            String targetLocation = this.rootLocation.resolve(filePath).toString();
            Resource file = loadAsResource(targetLocation);
            return ResponseEntity.ok()
                    .contentType(determineMediaType(targetLocation))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
                    .body(file);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    private String extractPathFromPattern(HttpServletRequest request) {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);

        return new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path);
    }


    private Resource loadAsResource(String filename) throws IOException {
        try {
            // Path file = rootLocation.resolve(filename);
            Path file = Paths.get(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("File not found " + filename);
            }
        } catch (MalformedURLException e) {
            throw new IOException("Invalid file path " + filename, e);
        }
    }

    private MediaType determineMediaType(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        String ext = (lastDotIndex == -1) ? "" : fileName.substring(lastDotIndex + 1).toLowerCase();
        switch (ext) {
            case "jpeg":
            case "jpg":
                return MediaType.IMAGE_JPEG;
            case "gif":
                return MediaType.IMAGE_GIF;
            case "png":
                return MediaType.IMAGE_PNG;
            default:
                return MediaType.APPLICATION_OCTET_STREAM;  // Use a general binary file type if unsure
        }
    }

}
