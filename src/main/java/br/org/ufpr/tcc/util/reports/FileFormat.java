package br.org.ufpr.tcc.util.reports;
public enum FileFormat {

    PDF("pdf", "data:application/pdf;base64,"),
    XLS("xls", "data:application/xls;base64,"),
    XLSX("xlsx", "data:application/xlsx;base64,"),
    CSV("csv", "data:application/csv;base64,"),
    XML("xml", "data:application/xml;base64,"),
    HTML("html", "data:application/html;base64,"),
    JPG("jpg", "data:application/jpg;base64,"),
    PNG("png", "data:application/png;base64,"),
    ODS("ods", "data:application/ods;base64,");

    private String webFileFormat;

    private String fileExtension;

    private FileFormat(String fileExtension, String webFileFormat) {
        this.fileExtension = fileExtension;
        this.webFileFormat = webFileFormat;
    }

    public String getWebFileFormat() {
        return webFileFormat;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public static FileFormat getFileFormat(String fileExtension) {
        for (FileFormat ff : values()) {
            if (ff.getFileExtension().equalsIgnoreCase(fileExtension)) {
                return ff;
            }
        }
        return null;
    }

}
