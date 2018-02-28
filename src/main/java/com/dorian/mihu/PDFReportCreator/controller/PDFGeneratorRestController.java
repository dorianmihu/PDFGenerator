package com.dorian.mihu.PDFReportCreator.controller;

import com.dorian.mihu.PDFReportCreator.PDFGeneratorService;
import com.dorian.mihu.PDFReportCreator.storage.StorageException;
import com.dorian.mihu.PDFReportCreator.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PDFGeneratorRestController {

    @Autowired
    private PDFGeneratorService pdfGeneratorService;

    @Autowired
    private StorageService storageService;

    @RequestMapping("/hi")
    public String helloWorld(){
        return "HI";
    }

    @RequestMapping(method = RequestMethod.POST,value ="/generatepdf")
    public void parseIncomingReportRequest(@RequestBody String json){
        pdfGeneratorService.parseIncomingJSON(json);
    }

    @RequestMapping(method = RequestMethod.POST,value="/templates")
    public void addNewJasperTemplate(@RequestParam("file") MultipartFile file) throws StorageException {
            storageService.store(file);
    }
}
