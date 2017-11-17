package br.org.ufpr.tcc.facade;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;

import br.org.ufpr.tcc.bc.FotoBC;
import br.org.ufpr.tcc.converter.DTOtoFoto;
import br.org.ufpr.tcc.converter.FotoToDTO;
import br.org.ufpr.tcc.dao.FotoDAO;
import br.org.ufpr.tcc.dto.FotoDTO;
import br.org.ufpr.tcc.dto.FotoFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
import br.org.ufpr.tcc.entity.Foto;
import br.org.ufpr.tcc.entity.Mensagem;
import br.org.ufpr.tcc.entity.Pagina;
import br.org.ufpr.tcc.exception.handler.NegocioException;
import br.org.ufpr.tcc.util.Constantes;
import br.org.ufpr.tcc.util.DataUtil;
import br.org.ufpr.tcc.util.ImageUtil;



public class FotoFacade {

    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());
    
    private FotoBC bc = new FotoBC();    

    public FotoDTO obter(Long id, boolean carregarFotoMiniatura, boolean carregarFotoOriginal) {
        String logMsg = "Iniciando a busca de foto id[%d]" + id;
        
        log.info(logMsg);

        Foto f = bc.obter(id.intValue());

        logMsg = "Busca de foto finalizada";
        log.info(logMsg);

        //CONVERTER
        FotoToDTO converter = new FotoToDTO();
        FotoDTO fotoDTO = converter.convert(f, true, false);
    	
        return fotoDTO;
    }

    public ResultadoPaginadoDTO<FotoDTO> listar(FotoFiltroDTO filtros, String fields) {
        String logMsg = "Iniciando a listagem de Foto Facade";
        log.info(logMsg);

        ResultadoPaginadoDTO<Foto> listagem = null;
        try {
			listagem = bc.listar(filtros);
		} catch (Exception e) {
			log.severe("Erro ao listar");
			e.printStackTrace();
		}
        
        log.info("Convertendo resultados obtidos");

        
        //CONVERTER
        List<FotoDTO> fotosDTO = new ArrayList<FotoDTO>();
        for(Foto f : listagem.getEntidades()){
        	FotoToDTO converter = new FotoToDTO();
        	FotoDTO fotoDTO = converter.convert(f, true, false);
        	fotosDTO.add(fotoDTO);
        }
		
		ResultadoPaginadoDTO<FotoDTO> responseDTO = new ResultadoPaginadoDTO<FotoDTO>(fotosDTO, new Pagina());

        logMsg = "Finalizando listagem de Foto";
        log.info(logMsg);

        return responseDTO;
    }

    public ResponseDTO persistir(FotoDTO dto) {
        String logMsg = "Iniciando a persistencia de Foto";
        log.info(logMsg);

        //CONVERTER
        DTOtoFoto converter = new DTOtoFoto();
        Foto foto = converter.convert(dto);
        
		Foto fotoSalva = bc.persistir(foto);

        logMsg = "Registro de Foto persistido";
        log.info(logMsg);

        return new ResponseDTO(Long.valueOf(fotoSalva.getCodFoto()));
    }
    
    public ResponseDTO remover(Long... ids) {
    	ResponseDTO retorno = new ResponseDTO();
    	
    	for(Long id : ids){
    		ResponseDTO aux = bc.remover(id.intValue());
    		retorno.getMensagens().addAll(aux.getMensagens());
    	}
    	
    	return retorno;
    	 
    }

	public FotoDTO inserir(FotoDTO dto) {
		String logMsg = "Iniciando a persistencia de Foto";
        log.info(logMsg);

        //CONVERTER
        DTOtoFoto converter = new DTOtoFoto();
        Foto foto = converter.convert(dto);
        
		Foto fotoSalva = bc.persistir(foto);

        logMsg = "Registro de Foto persistido";
        log.info(logMsg);

        FotoToDTO toDTOConverter = new FotoToDTO();
        FotoDTO fotoSalvaDTO = toDTOConverter.convert(fotoSalva, true, false);
        
        return fotoSalvaDTO;
	}

	public FotoDTO gravarFotoPastaTemporaria(List<InputPart> inputParts, String nomeArquivo, String legenda) {
		
		String logMsg = "Iniciando gravação da imagem da Foto na pasta" + Constantes.NOME_PASTA_TMP_FOTOS;
        log.info(logMsg);
        FotoDTO fotoDTO = null;
		FotoDTO fotoSalvaDTO = null;

		for (InputPart inputPart : inputParts) {

			try {
				InputStream inputStream = inputPart.getBody(InputStream.class, null);
				
				String[] partesDoNome = nomeArquivo.split("\\.");
				
				String extensaoFoto = "";
				if(partesDoNome.length >= 1){
					extensaoFoto = partesDoNome[partesDoNome.length-1];
					extensaoFoto = extensaoFoto.trim().toLowerCase();
					if(!extensaoFoto.equals("jpg") && !extensaoFoto.equals("jpeg")){
						Mensagem m = new Mensagem(Mensagem.ERRO, "Extensão inválida");
						throw new NegocioException(m);
					} else {
						extensaoFoto = "jpg";
					}
				}
				
				byte[] bytesImgOriginal = IOUtils.toByteArray(inputStream);
				byte[] bytesImgMiniatura = ImageUtil.resize(bytesImgOriginal, 180, 130, extensaoFoto);

				String dataHora = DataUtil.fromLocalDateTimeToString(LocalDateTime.now(), "yyyy-MM-dd_HHmmss");
				
				String nomeArquivoOriginal = nomeArquivo + "_" + dataHora + "." + extensaoFoto;
				String nomeArquivoMiniatura = nomeArquivo + "_" + dataHora + ".min." + extensaoFoto;
				
				writeFile(bytesImgOriginal, Constantes.PATH_ARMAZENAMENTO_FOTOS + File.separator + Constantes.NOME_PASTA_TMP_FOTOS + File.separator + nomeArquivoOriginal);
				writeFile(bytesImgMiniatura, Constantes.PATH_ARMAZENAMENTO_FOTOS + File.separator + Constantes.NOME_PASTA_TMP_FOTOS + File.separator + nomeArquivoMiniatura);

				fotoDTO = new FotoDTO();
				fotoDTO.setLegenda(legenda);
				fotoDTO.setNomeFotoOriginal(nomeArquivoOriginal);
				fotoDTO.setNomeFotoMiniatura(nomeArquivoMiniatura);

				fotoSalvaDTO = inserir(fotoDTO);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		return fotoSalvaDTO;
	}

	
	
	/**
	 * header sample { Content-Type=[image/png], Content-Disposition=[form-data;
	 * name="file"; filename="filename.extension"] }
	 **/
	// get uploaded filename, is there a easy way in RESTEasy?
	private String getFileName(MultivaluedMap<String, String> header) {

		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {

				String[] name = filename.split("=");

				String finalFileName = name[1].trim().replaceAll("\"", "");
				return finalFileName;
			}
		}
		return "unknown";
	}

	// save to somewhere
	private void writeFile(byte[] content, String filename) throws IOException {

		File file = new File(filename);

		if (!file.exists()) {
			file.createNewFile();
		}

		FileOutputStream fop = new FileOutputStream(file);

		fop.write(content);
		fop.flush();
		fop.close();

	}

}
