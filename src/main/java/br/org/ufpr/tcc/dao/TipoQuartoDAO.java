package br.org.ufpr.tcc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import br.org.ufpr.tcc.dto.TipoQuartoFiltroDTO;
import br.org.ufpr.tcc.dto.TipoQuartoFiltroDTO;
import br.org.ufpr.tcc.entity.Filial;
import br.org.ufpr.tcc.entity.Pagina;
import br.org.ufpr.tcc.entity.TipoQuarto;
import br.org.ufpr.tcc.util.Util;

public class TipoQuartoDAO extends LazarusDAO<TipoQuarto> {
    
	public List<TipoQuarto> listar(TipoQuartoFiltroDTO filtros) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TipoQuarto> cq = cb.createQuery(TipoQuarto.class);
        Root<TipoQuarto> root = cq.from(TipoQuarto.class);
        Predicate[] predicados = buildPredicatePesquisa(filtros, cb, root);

        cq.where(cb.and(predicados));

        Pagina pagina = filtros.getPagina();

        return findByCriteriaQuery(cq, pagina);
    }

    private Predicate[] buildPredicatePesquisa(TipoQuartoFiltroDTO filtros, CriteriaBuilder cb,
        Root<TipoQuarto> root) {
        Predicate[] predicados = { };
        
        Path<String> pathCampoTexto = root.get(TipoQuarto.NOME);
        
        if(StringUtils.isNotBlank(filtros.getNome())){
            predicados = Util.add(predicados, cb.like(pathCampoTexto, Util.likeFormat(filtros.getNome())));
        }
        
        //... outros predicados/filtros se houver
        
        return predicados;
    }

    /*
    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());
    private final String stmtInserir = "INSERT INTO ATRIBUTOS (TIPO, NOME, DESCRICAO, STATUS) VALUES(?, ?, ?, ?)";
    private final String stmtObter = "SELECT * FROM ATRIBUTOS WHERE COD_ATRIBUTO = ?";
    private final String stmtExcluir = "UPDATE ATRIBUTOS SET STATUS = 'E' WHERE COD_ATRIBUTO = ? ";
    private final String stmtListar = "SELECT * FROM ATRIBUTOS";
    private final String stmtListarPaginado = "SELECT * FROM ATRIBUTOS";
    private final String stmtAlterar = "UPDATE ATRIBUTOS SET TIPO = ?, NOME = ?, DESCRICAO = ?, STATUS = ? WHERE COD_ATRIBUTO = ? ";

    public void inserir(TipoQuarto tipoquarto) {
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtInserir,PreparedStatement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, tipoquarto.getTipo());            
            stmt.setString(2, tipoquarto.getNome());                       
            stmt.setString(3, tipoquarto.getDescricao());            
            stmt.setString(4, String.valueOf(tipoquarto.getStatus()));
            stmt.executeUpdate();
            
            tipoquarto.setCodTipoQuarto(lerCodTipoQuarto(stmt));
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir tipoquarto no banco de dados.", ex);
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexao. Ex="+ex.getMessage());};
        }
     }
    
    private Integer lerCodTipoQuarto(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    public TipoQuarto obter(Integer CodTipoQuarto) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        TipoQuarto tipoquartoLido;
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtObter);
            stmt.setInt(1, CodTipoQuarto);
            rs = stmt.executeQuery();
            
            if(rs.next()){
            	
                tipoquartoLido = extrairTipoQuartoDoResultSet(rs);
                
                return tipoquartoLido;
                
            }else{
                throw new RuntimeException("Nao existe TipoQuarto correspondente ao c�digo = " + CodTipoQuarto);
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar TipoQuartos no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{rs.close();}catch(Exception ex){System.out.println("Erro ao fechar result set. Ex="+ex.getMessage());};
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();;}catch(Exception ex){System.out.println("Erro ao fechar conexao. Ex="+ex.getMessage());};
        }

    }

	private TipoQuarto extrairTipoQuartoDoResultSet(ResultSet rs) throws SQLException {
		TipoQuarto tipoquartoLido;
		tipoquartoLido = new TipoQuarto();
		
		tipoquartoLido.setCodTipoQuarto(rs.getInt("COD_ATRIBUTO"));
		tipoquartoLido.setTipo(rs.getString("TIPO"));		
		tipoquartoLido.setNome(rs.getString("NOME"));
		tipoquartoLido.setDescricao(rs.getString("DESCRICAO"));		
		tipoquartoLido.setStatus(rs.getString("STATUS").charAt(0));
		
		return tipoquartoLido;
	}

    public List<TipoQuarto> listar() throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<TipoQuarto> lista = new ArrayList();
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtListar);
            rs = stmt.executeQuery();
            
            TipoQuarto tipoquarto = null;            
            while(rs.next()){            	
            	tipoquarto = extrairTipoQuartoDoResultSet(rs);                
                lista.add(tipoquarto);
            }            
            return lista;
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar tipoquartos. Origem="+ex.getMessage());
        }finally{
            try{rs.close();}catch(Exception ex){System.out.println("Erro ao fechar result set. Ex="+ex.getMessage());};
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();;}catch(Exception ex){System.out.println("Erro ao fechar conexao. Ex="+ex.getMessage());};               
        }

    }
    
    public List<TipoQuarto> listar(TipoQuartoFiltroDTO filtros) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<TipoQuarto> lista = new ArrayList();
        
        String logMsg = "Iniciando a listagens de TipoQuarto DAO";
        log.info(logMsg);
        
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtListar);
            rs = stmt.executeQuery();
            
            
            //TODO: tratar os filtros aqui!
            
            TipoQuarto tipoquarto = null;
            
            while(rs.next()){
            	
            	tipoquarto = extrairTipoQuartoDoResultSet(rs);
                
                lista.add(tipoquarto);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de TipoQuarto. Origem="+ex.getMessage());
        }finally{
            try{rs.close();}catch(Exception ex){System.out.println("Erro ao fechar result set. Ex="+ex.getMessage());};
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();;}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};               
        }

    }
    
    public void excluir(Integer id) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtExcluir);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao excluir TipoQuarto. Origem="+ex.getMessage());
        }finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();;}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};               
        }
    }
    
        public void alterar(TipoQuarto tipoquarto) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        List<TipoQuarto> lista = new ArrayList();
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtAlterar);
            
            stmt.setString(1, tipoquarto.getTipo());            
            stmt.setString(2, tipoquarto.getNome());
            stmt.setString(3, tipoquarto.getDescricao());            
            stmt.setString(4, String.valueOf(tipoquarto.getStatus()));            
            stmt.setInt(5, tipoquarto.getCodTipoQuarto());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao alterar tipoquarto. Origem="+ex.getMessage());
        }finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();;}catch(Exception ex){System.out.println("Erro ao fechar conexao. Ex="+ex.getMessage());};               
        }
    }
    */
}
