package br.org.ufpr.tcc.util.reports;
public enum RelatoriosExportados {
    CLIENTES_PDF(Relatorio.CLIENTES,FileFormat.PDF, "clientes"),
    RESERVAS_PDF(Relatorio.RESERVAS,FileFormat.PDF, "reservas");
    private FileFormat fileFormat;

    private Relatorio relatorio;

    private String fileName;

    private RelatoriosExportados(Relatorio relatorio, FileFormat fileFormat, String fileName) {
        this.fileFormat = fileFormat;
        this.fileName = fileName;
        this.relatorio = relatorio;
    }

    public FileFormat getFileFormat() {
        return fileFormat;
    }

    public String getFileName() {
        return fileName;
    }

    public String getJasperReportName() {
        return relatorio.getJasperReportName();
    }

    public Relatorio getRelatorio() {
        return relatorio;
    }

    public FileDTO buildFileDTO() {
        FileDTO dto = new FileDTO();

        dto.setFileName(getFileName());
        dto.setFileExtension(getFileFormat().getFileExtension());
        dto.setWebFileFormat(getFileFormat().getWebFileFormat());

        return dto;
    }

    public static FileDTO buildFileDTO(RelatoriosExportados relatorio) {
        FileDTO dto = new FileDTO();

        dto.setFileName(relatorio.getFileName());
        dto.setFileExtension(relatorio.getFileFormat().getFileExtension());
        dto.setWebFileFormat(relatorio.getFileFormat().getWebFileFormat());

        return dto;
    }
}