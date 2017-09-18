package br.org.ufpr.tcc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import br.org.ufpr.tcc.dto.ShowcaseFiltroDTO;
import br.org.ufpr.tcc.entity.Showcase;


public class ShowcaseDAO {
    
    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());

    private final String stmtInserir = "INSERT INTO filiais(NOME, EMAIL1, DESCRICAO, EXIBIR_SITE, STATUS) VALUES(?, ?, ?, ?, ?)";
    private final String stmtObter = "SELECT * FROM filiais WHERE cod_showcase = ?";
    private final String stmtExcluir = "DELETE FROM filiais WHERE cod_showcase = ?";
    private final String stmtListar = "SELECT * FROM filiais";
    private final String stmtListarPaginado = "SELECT * FROM filiais WHERE nome ilike ? ";
    private final String stmtAlterar = "UPDATE filiais SET NOME = ?, EMAIL1 = ?, DESCRICAO = ?, EXIBIR_SITE = ?, STATUS = ? WHERE COD_FILIAL = ? ";

    public void inserir(Showcase showcase) {
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtInserir,PreparedStatement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1 ,showcase.getNome());
            stmt.setString(2 , showcase.getEmail());
            stmt.setString(3 , showcase.getDescricao());
            stmt.setString(4 ,showcase.getExibirSite());
            stmt.setString(5 ,String.valueOf(showcase.getStatus()));

            stmt.executeUpdate();
            showcase.setCodShowcase(lerIdShowcase(stmt));
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir um showcase no banco de dados.", ex);
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
     }
    
    private Integer lerIdShowcase(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    public Showcase obter(Integer id) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Showcase showcaseLida;
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtObter);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if(rs.next()){
            	
                showcaseLida = extrairShowcaseDoResultSet(rs);
                
                return showcaseLida;
                
            }else{
                throw new RuntimeException("Não existe Showcase com este id. Id="+id);
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar um Showcase no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{rs.close();}catch(Exception ex){System.out.println("Erro ao fechar result set. Ex="+ex.getMessage());};
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();;}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }

    }

	private Showcase extrairShowcaseDoResultSet(ResultSet rs) throws SQLException {
		Showcase showcaseLida;
		showcaseLida = new Showcase();
		
		showcaseLida.setCodShowcase(rs.getInt("COD_FILIAL"));
		showcaseLida.setNome(rs.getString("NOME"));
		showcaseLida.setEmail(rs.getString("EMAIL1"));
		showcaseLida.setDescricao(rs.getString("DESCRICAO"));
		showcaseLida.setExibirSite(rs.getString("EXIBIR_SITE"));
		showcaseLida.setStatus(rs.getString("STATUS").charAt(0));
		return showcaseLida;
	}

    public List<Showcase> listar() throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Showcase> lista = new ArrayList();
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtListar);
            rs = stmt.executeQuery();
            
            Showcase showcase = null;
            
            while(rs.next()){
            	
            	showcase = extrairShowcaseDoResultSet(rs);
                
                lista.add(showcase);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de Showcase. Origem="+ex.getMessage());
        }finally{
            try{rs.close();}catch(Exception ex){System.out.println("Erro ao fechar result set. Ex="+ex.getMessage());};
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();;}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};               
        }

    }
    
    public List<Showcase> listar(ShowcaseFiltroDTO filtros) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Showcase> lista = new ArrayList();
        
        String logMsg = "Iniciando a listagens de Showcase DAO";
        log.info(logMsg);
        
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtListar);
            rs = stmt.executeQuery();
            
            
            //TODO: tratar os filtros aqui!
            
            Showcase showcase = null;
            
            while(rs.next()){
            	
            	showcase = extrairShowcaseDoResultSet(rs);
                
                lista.add(showcase);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de Showcase. Origem="+ex.getMessage());
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
            throw new RuntimeException("Erro ao excliur um Showcase. Origem="+ex.getMessage());
        }finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();;}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};               
        }
    }
    
        public void alterar(Showcase showcase) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        List<Showcase> lista = new ArrayList();
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtAlterar);
            
            stmt.setString(1 ,showcase.getNome());
            stmt.setString(2 ,showcase.getEmail());
            stmt.setString(3 ,showcase.getDescricao());
            stmt.setString(4 ,showcase.getExibirSite());
            stmt.setString(5 ,String.valueOf(showcase.getStatus()));
            //where
            stmt.setInt(6 ,showcase.getCodShowcase());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao alterar um Showcase. Origem="+ex.getMessage());
        }finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();;}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};               
        }
    }

}
