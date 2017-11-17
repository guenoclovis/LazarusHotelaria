package br.org.ufpr.tcc.converter;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import br.org.ufpr.tcc.dto.FotoDTO;
import br.org.ufpr.tcc.entity.Foto;
import br.org.ufpr.tcc.util.Constantes;
import br.org.ufpr.tcc.util.ImageUtil;

public class FotoToDTO {
	
	private Logger log = Logger.getLogger(this.getClass().getCanonicalName());
	
	public FotoDTO convert(Foto foto, boolean carregarImagemMiniatura, boolean carregarImagemOriginal){
		
		FotoDTO dto = new FotoDTO();
		
		if(foto != null){
		
			dto.setCodFoto(foto.getCodFoto());
			dto.setNomeFotoOriginal(foto.getNomeFotoOriginal());		
			dto.setNomeFotoMiniatura(foto.getNomeFotoMiniatura());
			dto.setLegenda(foto.getLegenda());
			
			if(carregarImagemMiniatura && foto.getNomeFotoMiniatura() != null){
				try {
					byte[] img = ImageUtil.lerFotoParaByteArray(Constantes.PATH_ARMAZENAMENTO_FOTOS + File.separator
							+ Constantes.NOME_PASTA_DEF_FOTOS + File.separator + foto.getNomeFotoMiniatura());
					
					dto.setImagemMiniatura(img);
				} catch (IOException e) {
					log.info("Erro ao ler " + foto.getNomeFotoMiniatura());
					e.printStackTrace();
				}
			}
			
			if(carregarImagemOriginal && foto.getNomeFotoOriginal() != null){
				try {
					byte[] img = ImageUtil.lerFotoParaByteArray(Constantes.PATH_ARMAZENAMENTO_FOTOS + File.separator
							+ Constantes.NOME_PASTA_DEF_FOTOS + File.separator + foto.getNomeFotoOriginal());
					
					dto.setImagemOriginal(img);
					
				} catch (IOException e) {
					log.info("Erro ao ler " + foto.getNomeFotoOriginal());
					e.printStackTrace();
				}
			}
		
		}
		
		return dto;
	}

}
