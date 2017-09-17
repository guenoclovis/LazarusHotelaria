package br.org.ufpr.tcc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import br.org.ufpr.tcc.dto.FilialFiltroDTO;
import br.org.ufpr.tcc.entity.Filial;




public class FilialDAO {
    
    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());

    private final String stmtInserir = "INSERT INTO filiais(NOME, EMAIL1, DESCRICAO, EXIBIR_SITE, STATUS) VALUES(?, ?, ?, ?, ?)";
    private final String stmtObter = "SELECT * FROM filiais WHERE cod_filial = ?";
    private final String stmtExcluir = "DELETE FROM filiais WHERE cod_filial = ?";
    private final String stmtListar = "SELECT * FROM filiais";
    private final String stmtListarPaginado = "SELECT * FROM filiais WHERE nome ilike ? ";
    private final String stmtAlterar = "UPDATE filiais SET NOME = ?, EMAIL1 = ?, DESCRICAO = ?, EXIBIR_SITE = ?, STATUS = ? WHERE COD_FILIAL = ? ";

    public void inserir(Filial filial) {
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtInserir,PreparedStatement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1 ,filial.getNome());
            stmt.setString(2 , filial.getEmail());
            stmt.setString(3 , filial.getDescricao());
            stmt.setString(4 ,filial.getExibirSite());
            stmt.setString(5 ,String.valueOf(filial.getStatus()));

            stmt.executeUpdate();
            filial.setCodFilial(lerIdFilial(stmt));
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir um filial no banco de dados.", ex);
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
     }
    
    private Integer lerIdFilial(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    public Filial obter(Integer id) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Filial filialLida;
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtObter);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if(rs.next()){
            	
                filialLida = extrairFilialDoResultSet(rs);
                
                return filialLida;
                
            }else{
                throw new RuntimeException("Não existe Filial com este id. Id="+id);
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar um Filial no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{rs.close();}catch(Exception ex){System.out.println("Erro ao fechar result set. Ex="+ex.getMessage());};
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();;}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }

    }

	private Filial extrairFilialDoResultSet(ResultSet rs) throws SQLException {
		Filial filialLida;
		filialLida = new Filial();
		
		filialLida.setCodFilial(rs.getInt("COD_FILIAL"));
		filialLida.setNome(rs.getString("NOME"));
		filialLida.setEmail(rs.getString("EMAIL1"));
		filialLida.setDescricao(rs.getString("DESCRICAO"));
		filialLida.setExibirSite(rs.getString("EXIBIR_SITE"));
		filialLida.setStatus(rs.getString("STATUS").charAt(0));
		return filialLida;
	}

    public List<Filial> listar() throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Filial> lista = new ArrayList();
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtListar);
            rs = stmt.executeQuery();
            
            Filial filial = null;
            
            while(rs.next()){
            	
            	filial = extrairFilialDoResultSet(rs);
                
                lista.add(filial);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de Filial. Origem="+ex.getMessage());
        }finally{
            try{rs.close();}catch(Exception ex){System.out.println("Erro ao fechar result set. Ex="+ex.getMessage());};
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();;}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};               
        }

    }
    
    public List<Filial> listar(FilialFiltroDTO filtros) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Filial> lista = new ArrayList();
        
        String logMsg = "Iniciando a listagens de Filial DAO";
        log.info(logMsg);
        
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtListar);
            rs = stmt.executeQuery();
            
            
            //TODO: tratar os filtros aqui!
            
            Filial filial = null;
            
            while(rs.next()){
            	
            	filial = extrairFilialDoResultSet(rs);
                
                lista.add(filial);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de Filial. Origem="+ex.getMessage());
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
            throw new RuntimeException("Erro ao excliur um Filial. Origem="+ex.getMessage());
        }finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();;}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};               
        }
    }
    
        public void alterar(Filial filial) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        List<Filial> lista = new ArrayList();
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtAlterar);
            
            stmt.setString(1 ,filial.getNome());
            stmt.setString(2 ,filial.getEmail());
            stmt.setString(3 ,filial.getDescricao());
            stmt.setString(4 ,filial.getExibirSite());
            stmt.setString(5 ,String.valueOf(filial.getStatus()));
            //where
            stmt.setInt(6 ,filial.getCodFilial());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao alterar um Filial. Origem="+ex.getMessage());
        }finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();;}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};               
        }
    }

}
