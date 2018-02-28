package com.dorian.mihu.PDFReportCreator;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dorian.mihu.PDFReportCreator.storage.StorageException;
import com.dorian.mihu.PDFReportCreator.storage.StorageProperties;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class JasperReportCreator {

    private Log logger = LogFactory.getLog(getClass());

    @Autowired
    private StorageProperties storageProperties;

    public void fillJasperReport(PDFInformation pdfInformation) throws StorageException {
        String fileName = pdfInformation.getTemplateName()+".jrxml";
        String sourceFileName = Paths.get(storageProperties.getTemplateLocation(),fileName).toString();
        if(Paths.get(sourceFileName).toFile().exists()){
            JasperPrint jasperPrint = null;
            List<PDFInformation> pdfInformationArrayList = new ArrayList<>();
            pdfInformationArrayList.add(pdfInformation);
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pdfInformationArrayList);
            Map parameters = new HashMap<>();
            try {
                JasperReport report = JasperCompileManager.compileReport(sourceFileName);
                jasperPrint = JasperFillManager.fillReport(report,parameters,beanCollectionDataSource);

                if(jasperPrint!=null){
                    String destFileName = Paths.get(storageProperties.getExportPDFLocation(),pdfInformation.getTemplateName()+".pdf").toString();
                    if(Paths.get(destFileName).toFile().exists()){
                        Files.delete(Paths.get(destFileName));
                    }
                    JasperExportManager.exportReportToPdfFile(jasperPrint,destFileName);
                }
            } catch (JRException e) {
                logger.error(e.getMessage());
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        } else {
            throw new StorageException("No such jasper report template exists: "+sourceFileName);
        }


    }
}
