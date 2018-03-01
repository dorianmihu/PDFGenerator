package com.dorian.mihu.PDFReportCreator.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path templaterootLocation;
    private final Path exportLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.templaterootLocation = Paths.get(properties.getTemplateLocation());
        this.exportLocation = Paths.get(properties.getExportPDFLocation());
    }

    public void store(MultipartFile file) throws StorageException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            Files.copy(file.getInputStream(), this.templaterootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

    @Override
    public Path load(String filename) {
        return templaterootLocation.resolve(filename);
    }


    @Override
    public void init() throws StorageException {
        try {
            if(!templaterootLocation.toFile().isDirectory()) {
                Files.createDirectories(templaterootLocation);
            }
            if(!exportLocation.toFile().isDirectory()) {
                Files.createDirectories(exportLocation);
            }
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
