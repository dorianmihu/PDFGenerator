package com.dorian.mihu.PDFReportCreator.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing template files
     */
    private String templateLocation = "upload-dir";

    private String exportPDFLocation ="export-dir";

    public void setExportPDFLocation(String exportPDFLocation) {
        this.exportPDFLocation = exportPDFLocation;
    }

    public String getExportPDFLocation() {

        return exportPDFLocation;
    }

    public String getTemplateLocation() {
        return templateLocation;
    }

    public void setTemplateLocation(String location) {
        this.templateLocation = location;
    }


}
