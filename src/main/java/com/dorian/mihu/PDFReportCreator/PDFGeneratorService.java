package com.dorian.mihu.PDFReportCreator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PDFGeneratorService {

    @Autowired
    private JasperReportCreator jasperReportCreator;

    public void parseIncomingJSON(String json){
        JsonParser jsonParser = new BasicJsonParser();
        Map<String,Object> jsonParameters = jsonParser.parseMap(json);
        PDFInformation pdfInformation = null;
        if(jsonParameters!=null){
            pdfInformation = new PDFInformation();
            pdfInformation.setTemplateName(jsonParameters.get("template_name").toString());
            Map<String,String> templateParameters = (Map<String, String>) jsonParameters.get("template_parameters");
            pdfInformation.setParam1(templateParameters.get("param1"));
            pdfInformation.setParam2(templateParameters.get("param2"));
            pdfInformation.setParam3(templateParameters.get("param3"));
        }
       if(pdfInformation!=null){
            jasperReportCreator.fillJasperReport(pdfInformation);
       }
    }
}
