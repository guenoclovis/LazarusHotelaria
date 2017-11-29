package br.org.ufpr.tcc.util.reports;

public class FileDTO {
    
    private byte[] content;

    private String fileName;

    private String fileExtension;

    private String webFileFormat;

    public byte[] getContent() {
        return content;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public String getWebFileFormat() {
        return webFileFormat;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public void setWebFileFormat(String webFileFormat) {
        this.webFileFormat = webFileFormat;
    }
}
