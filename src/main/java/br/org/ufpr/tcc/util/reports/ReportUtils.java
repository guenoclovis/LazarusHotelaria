package br.org.ufpr.tcc.util.reports;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.ArrayUtils.getLength;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

/**
 * Utilitário para geração e exportação de relatórios através do JasperReports.
 * 
 * @author <a href="mailto:leandro.deitos@serpro.gov.br">Leandro Deitos</a>
 *
 */
public class ReportUtils {
    private static final String PARAMETRO_TITULO_RELATORIO = "tituloRelatorio";

    private static final String PARAMETRO_NOME_SISTEMA = "nomeSistema";

    private static final String NOME_SISTEMA_RELATORIOS =
        "Sistema de Informações Penitenciárias do Departamento Penitenciário Nacional";

    private static final ClassLoader classLoader = ReportUtils.class.getClassLoader();

    private static final LogUtil LOG = new LogUtil();

    /**
     * @param relatorio
     *            Relatório que se deseja gerar.
     * @return Mapa de parâmetros básicos contendo o nome do sistema e o título do relatório.
     */
    public static Map<String, Object> getParametrosBasicos(RelatoriosExportados relatorio) {
        return getParametrosBasicos(relatorio.getRelatorio());
    }

    /**
     * @param relatorio
     *            Relatório que se deseja gerar.
     * @return Mapa de parâmetros básicos contendo o nome do sistema e o título do relatório.
     */
    public static Map<String, Object> getParametrosBasicos(Relatorio relatorio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(PARAMETRO_NOME_SISTEMA, NOME_SISTEMA_RELATORIOS);
        parametros.put(PARAMETRO_TITULO_RELATORIO, relatorio.getReportTitle());

        return parametros;
    }

    /**
     * @param relatorio
     *            Relatório que se deseja exportar.
     * @param dados
     *            Dados a serem enviados para preencher o relatório.
     * @param parametros
     *            Mapa de parâmetros para compor o relatório.
     * @return DTO contedo a representação de arquivo PDF do relatório desejado.
     */
    public static <T> FileDTO getAsPDF(RelatoriosExportados relatorio, T dados, Map<String, Object> parametros) {
        FileDTO dto = relatorio.buildFileDTO();
        LOG.info(format("Exportando dados do relatório [%s] para PDF.", relatorio.getJasperReportName()));

        LOG.debug("Obtendo InputStream.");
        ByteArrayOutputStream baos = null;
        InputStream reportStream = null;
        try {
        	baos = new ByteArrayOutputStream();
            reportStream = classLoader.getResourceAsStream(relatorio.getJasperReportName());
            LOG.debug("Obtendo HasperPrint.");
            JasperPrint print = getPrinter(reportStream, dados, parametros);
            LOG.debug("Exportando para PDF.");
            JasperExportManager.exportReportToPdfStream(print, baos);

            dto.setContent(baos.toByteArray());
        } catch (Exception ex) {
            LOG.error("Erro ao converter relatório para PDF", ex);
            throw new RuntimeException(ex);
        } finally {
        	if(baos!=null){
        		try {
					baos.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
        	}
        	if(reportStream!=null){
        		try {
        			reportStream.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
        	}
        	
        }

        LOG.info(format("Exportação realizada com sucesso. Tamanho do arquivo gerado: %s", getDataSize(dto)));

        return dto;
    }

    private static String getDataSize(FileDTO dto) {
        return getDataSize(getLength(dto.getContent()));
    }

    private static String getDataSize(int size) {
        String[] units = {"B", "KB", "MB", "GB", "TB" };
        final int divisor = 1000;
        int remainder = size > divisor ? size : 0;
        String unit = units[0];

        for (int i = 0; i < 4; i++) {
            if (size > divisor) {
                size /= divisor;
                remainder %= divisor;
                unit = units[i + 1];
            } else {
                break;
            }
        }

        return format("%d,%03d %s", size, remainder, unit);
    }

    private static <T> JasperPrint getPrinter(InputStream reportStream, T dados,
        Map<String, Object> parametros) throws JRRuntimeException {
        JasperPrint print = null;
        try {
            JasperReport jasper = (JasperReport) JRLoader.loadObject(reportStream);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(asList(dados));
            print = JasperFillManager.fillReport(jasper, parametros, dataSource);
            LOG.info("JasperReport preenchido com sucesso.");
        } catch (JRException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return print;
    }

    /**
     * @param relatorio
     *            Relatório que se deseja exportar.
     * @param dados
     *            Dados a serem enviados para preencher o relatório.
     * @param parametros
     *            Mapa de parâmetros para compor o relatório.
     * @return DTO contedo a representação de arquivo XLS do relatório desejado.
     */
    public static <T> FileDTO getAsXLS(RelatoriosExportados relatorio, T dados, Map<String, Object> parametros) {
        FileDTO dto = relatorio.buildFileDTO();
        LOG.info(format("Exportando dados do relatório %s para XLS.", relatorio.getJasperReportName()));

        LOG.debug("Obtendo InputStream.");
        
        ByteArrayOutputStream baos = null;
        InputStream reportStream = null;
        try {
        	baos = new ByteArrayOutputStream();
            reportStream = classLoader.getResourceAsStream(relatorio.getJasperReportName());
            LOG.debug("Obtendo HasperPrint.");
            JasperPrint print = getPrinter(reportStream, dados, parametros);
            LOG.debug("Exportando para PDF.");
            JRXlsExporter exporter = getXlsExporter(baos, print);
            exporter.exportReport();

            dto.setContent(baos.toByteArray());
        } catch (Exception ex) {
            LOG.error("Erro ao converter relatório para XLS", ex);
            throw new RuntimeException(ex);
        } finally {
        	if(baos!=null){
        		try {
					baos.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
        	}
        	if(reportStream!=null){
        		try {
        			reportStream.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
        	}
        	
        }

        LOG.info(format("Exportação realizada com sucesso. Tamanho do arquivo gerado: %s", getDataSize(dto)));

        return dto;
    }

    private static JRXlsExporter getXlsExporter(ByteArrayOutputStream baos, JasperPrint print) {
        JRXlsExporter exporter = new JRXlsExporter();
        SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();

        configuration.setDetectCellType(true);
        configuration.setCollapseRowSpan(true);
        configuration.setRemoveEmptySpaceBetweenRows(true);
        configuration.setRemoveEmptySpaceBetweenColumns(true);

        exporter.setConfiguration(configuration);
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));

        return exporter;
    }

    /**
     * Gets the as xls from template.
     *
     * @param <T>
     *            the generic type
     * @param fileName
     *            the file name
     * @param templatePath
     *            the template path
     * @param parametros
     *            Mapa de parâmetros para compor o relatório.
     * @return DTO contedo a representação de arquivo XLS do relatório desejado.
     */
    public static <T> FileDTO getAsXLSFromTemplate(String fileName, String templatePath,
        Map<String, Object> parametros) {

        FileDTO dto = new FileDTO();
        dto.setFileName(fileName);
        dto.setFileExtension(FileFormat.XLS.getFileExtension());
        dto.setWebFileFormat(FileFormat.XLS.getWebFileFormat());

        LOG.info(format("Processando dados do template %s para XLS.", templatePath));

        LOG.debug("Obtendo Template.");

        ByteArrayOutputStream baos = null;
        FileInputStream reportStream = null;
        try {
        	baos = new ByteArrayOutputStream();
        	reportStream = new FileInputStream(new File(templatePath));

            Context context = new Context();
            context.putVar("map", parametros);

            LOG.debug("Processando Template.");
            JxlsHelper.getInstance().processTemplate(reportStream, baos, context);

            dto.setContent(baos.toByteArray());
        } catch (Exception ex) {
            LOG.error("Erro ao converter relatório para XLS", ex);
            throw new RuntimeException(ex);
        } finally {
        	if(baos!=null){
        		try {
					baos.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
        	}
        	if(reportStream!=null){
        		try {
        			reportStream.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
        	}
        	
        }

        LOG.info(format("Processamento realizado com sucesso. Tamanho do arquivo gerado: %s",
            getDataSize(dto)));

        return dto;
    }

}
