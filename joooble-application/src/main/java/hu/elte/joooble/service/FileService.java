package hu.elte.joooble.service;

import hu.elte.joooble.controller.FileUploadController;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.nio.file.Path;

@Service
public class FileService {
    public String extractExtension(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

        if (i > p) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }

    public String getFileAbsolutePath(Path logoPath){
        return MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                "serveFile", logoPath.getFileName().toString()).build().toUri().toString();
    }
}
