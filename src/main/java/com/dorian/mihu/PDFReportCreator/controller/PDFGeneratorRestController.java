package com.dorian.mihu.PDFReportCreator.controller;

import com.dorian.mihu.PDFReportCreator.PDFGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PDFGeneratorRestController {

    @Autowired
    private PDFGeneratorService pdfGeneratorService;

    @RequestMapping("/hi")
    public String helloWorld(){
        return "HI";
    }

    @RequestMapping(method = RequestMethod.POST,value ="/templates")
    public void parseIncomingJSON(@RequestBody String json){
        pdfGeneratorService.parseIncomingJSON(json);
    }
}
