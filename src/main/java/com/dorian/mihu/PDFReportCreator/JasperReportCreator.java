package com.dorian.mihu.PDFReportCreator;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class JasperReportCreator {

    public void fillJasperReport(PDFInformation pdfInformation){
        String sourceFileName = "C:\\Java_Projects\\PDFGenerator\\jasper_report.jrxml";
        JasperPrint jasperPrint = null;
        List<PDFInformation> pdfInformationArrayList = new ArrayList<>();
        pdfInformationArrayList.add(pdfInformation);
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pdfInformationArrayList);
        Map parameters = new HashMap<>();
        try {
            JasperReport report = JasperCompileManager.compileReport(sourceFileName);
            jasperPrint = JasperFillManager.fillReport(report,parameters,beanCollectionDataSource);

            if(jasperPrint!=null){
                JasperExportManager.exportReportToPdfFile(jasperPrint,"C:\\Java_Projects\\PDFGenerator\\"+pdfInformation.getTemplateName()+".pdf");
            }
        } catch (JRException e) {
            e.printStackTrace();
        }

    }
}
