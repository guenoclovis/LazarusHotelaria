package br.org.ufpr.tcc.util.reports;

import static java.lang.String.format;
import static java.util.regex.Pattern.matches;
import static net.sf.jasperreports.engine.JasperCompileManager.compileReport;
import static net.sf.jasperreports.engine.JasperFillManager.fillReport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.org.ufpr.tcc.entity.Cliente;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class JasperTest {

    public static void main(String[] args) throws JRException {
        
    	System.out.println("Gerando relatorio...");

        Map<String, Object> parametros = new HashMap<String, Object>();
        
        parametros.put("tituloRelatorio", Relatorio.CLIENTES.getReportTitle());
        parametros.put("nomeSistema", "LAZARUS HOTELARIA");
        parametros.put("nome","-");
        
        List<Cliente> lista = gerarListaDeClientes();
        
        parametros.put("dados", lista);
        
        String nomeArquivoJrxml = Relatorio.CLIENTES.getJasperReportName();
        
        JasperReport jasper = compileReport(JasperTest.class.getResourceAsStream(formatReportFileName(nomeArquivoJrxml)));			 
		JasperPrint print = fillReport(jasper, parametros, new JRBeanCollectionDataSource(lista));
		JasperExportManager.exportReportToPdfFile(print, System.getProperty("user.home")+"/Area de Trabalho/Teste.pdf");
        System.out.println("Relatorio gerado.");
        

    }
    
    public static String formatReportFileName(String reportFileName) {
        String retorno = reportFileName;

        boolean fullCorrect = matches("^/relatorios/.*\\.jrxml$", retorno);
        boolean begginCorrect = matches("^/?relatorios/.*", retorno);
        boolean endCorrect = matches(".*\\.jrxml$", retorno);
        boolean endJasper = matches(".*\\.jasper$", retorno);

        if (!fullCorrect) {
            if (begginCorrect && !retorno.startsWith("/")) {
                retorno = formatReportFileName(format("/%s", retorno));
            }

            if (!begginCorrect) {
                retorno = format("/relatorios/%s", retorno);
            }

            if (endJasper) {
                retorno = formatReportFileName(retorno.replaceAll(".jasper$", ".jrxml"));
            }

            if (!endCorrect && !endJasper) {
                retorno = format("%s.jrxml", retorno);
            }

        }

        return retorno;
    }
    
    private static List<Cliente> gerarListaDeClientes() {
		List<Cliente> lista = new ArrayList<Cliente>();
        
        Cliente c = null;
        
        c = new Cliente();
        c.setNome("fkdjasklf");
        c.setStatus(0);
        c.setCpf("165165165651651651");
        c.setDtNasc(new Date());
        c.setEmail1("fdsaj@fkdsj.fdjksl");
        c.setTelefone1("51651651651");
        c.setTelefone2("51651651651");
        c.setNacionalidade("Brasileira");
        c.setStatus(1);
        c.setSexo(1);
        
        lista.add(c);
        
        c = new Cliente();
        c.setNome("fkdjasklf2");
        c.setStatus(1);
        c.setCpf("1651651656516516512");
        c.setDtNasc(new Date());
        c.setEmail1("fdsaj2@fkdsj.fdjksl");
        c.setTelefone1("516d51651651");
        c.setTelefone2("51651651651");
        c.setNacionalidade("Brasileira");
        c.setStatus(1);
        c.setSexo(1);
        
        lista.add(c);
		return lista;
	}

}