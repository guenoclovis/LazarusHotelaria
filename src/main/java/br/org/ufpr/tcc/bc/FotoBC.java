package br.org.ufpr.tcc.bc;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.org.ufpr.tcc.dao.FotoDAO;
import br.org.ufpr.tcc.dao.LazarusDAO;
import br.org.ufpr.tcc.dto.FotoFiltroDTO;
import br.org.ufpr.tcc.dto.ResponseDTO;
import br.org.ufpr.tcc.dto.ResultadoPaginadoDTO;
import br.org.ufpr.tcc.entity.Foto;
import br.org.ufpr.tcc.entity.Mensagem;
import br.org.ufpr.tcc.entity.Pagina;

public class FotoBC extends LazarusDAO<Foto> {

    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());

    private FotoDAO dao = new FotoDAO();
    //private FotoValidator validator = new FotoValidator();

    public Foto obter(Integer id) {
        return dao.load(id);
    }

    public ResultadoPaginadoDTO<Foto> listar(FotoFiltroDTO filtros) throws Exception {

        String logMsg = "Iniciando a listagens de Foto BC";
        log.info(logMsg);

        List<Foto> lista = dao.listar(filtros);

        return new ResultadoPaginadoDTO<Foto>(lista, new Pagina());
    }

    public Foto persistir(Foto foto) {

    	//validator.validateAndThrow(foto);
    	
        //VALIDAR A ENTIDADE ANTES DE PERSISTIR
    	Foto fotoSalva = null;
    	
        if (foto.getCodFoto() == null) {
            log.info("Inicia a persistencia de um novo Foto.");
            fotoSalva = dao.persistir(foto);
            log.info("Persistiu novo Foto na base de dados.");

        } else {
            log.info("Inicia a atualizacao do Foto [id=%d]" + foto.getCodFoto());

            try {
            	fotoSalva = dao.persistir(foto);
            } catch (Exception ex) {
                Logger.getLogger(FotoBC.class.getName()).log(Level.SEVERE, "Erro ao alterar.", ex);
            }
            log.info("Alterou Foto na base de dados.");
        }
        
        return fotoSalva;
    }

    public ResponseDTO remover(Integer id) {
    	ResponseDTO response = new ResponseDTO();
    	try {
			dao.remover(dao.load(id));
		} catch (Exception e) {
			response.getMensagens().add(new Mensagem(Mensagem.ERRO, "Não foi possível excluir Foto com id=" + id));
			e.printStackTrace();
		}
    	return response;
    }

}
