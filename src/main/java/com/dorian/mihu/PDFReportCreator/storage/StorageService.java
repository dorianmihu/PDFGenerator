package com.dorian.mihu.PDFReportCreator.storage;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {

    void init() throws StorageException;

    void store(MultipartFile file) throws StorageException;


    Path load(String filename);

}