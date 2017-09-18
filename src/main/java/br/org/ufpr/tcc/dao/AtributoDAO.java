package br.org.ufpr.tcc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import br.org.ufpr.tcc.dto.AtributoFiltroDTO;
import br.org.ufpr.tcc.entity.Atributo;

public class AtributoDAO {
    
    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());

    private final String stmtInserir = "INSERT INTO ATRIBUTOS (TIPO, NOME, DESCRICAO, STATUS) VALUES(?, ?, ?, ?)";
    private final String stmtObter = "SELECT * FROM ATRIBUTOS WHERE COD_ATRIBUTO = ?";
    private final String stmtExcluir = "UPDATE ATRIBUTOS SET STATUS = 'E' WHERE COD_ATRIBUTO = ? ";
    private final String stmtListar = "SELECT * FROM ATRIBUTOS";
    private final String stmtListarPaginado = "SELECT * FROM ATRIBUTOS";
    private final String stmtAlterar = "UPDATE ATRIBUTOS SET TIPO = ?, NOME = ?, DESCRICAO = ?, STATUS = ? WHERE COD_ATRIBUTO = ? ";

    public void inserir(Atributo atributo) {
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtInserir,PreparedStatement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, atributo.getTipo());            
            stmt.setString(2, atributo.getNome());                       
            stmt.setString(3, atributo.getDescricao());            
            stmt.setString(4, String.valueOf(atributo.getStatus()));
            stmt.executeUpdate();
            
            atributo.setCodAtributo(lerCodAtributo(stmt));
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir atributo no banco de dados.", ex);
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexao. Ex="+ex.getMessage());};
        }
     }
    
    private Integer lerCodAtributo(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    public Atributo obter(Integer CodAtributo) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Atributo atributoLido;
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtObter);
            stmt.setInt(1, CodAtributo);
            rs = stmt.executeQuery();
            
            if(rs.next()){
            	
                atributoLido = extrairAtributoDoResultSet(rs);
                
                return atributoLido;
                
            }else{
                throw new RuntimeException("Nao existe Atributo correspondente ao c�digo = " + CodAtributo);
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar Atributos no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{rs.close();}catch(Exception ex){System.out.println("Erro ao fechar result set. Ex="+ex.getMessage());};
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();;}catch(Exception ex){System.out.println("Erro ao fechar conexao. Ex="+ex.getMessage());};
        }

    }

	private Atributo extrairAtributoDoResultSet(ResultSet rs) throws SQLException {
		Atributo atributoLido;
		atributoLido = new Atributo();
		
		atributoLido.setCodAtributo(rs.getInt("COD_ATRIBUTO"));
		atributoLido.setTipo(rs.getString("TIPO"));		
		atributoLido.setNome(rs.getString("NOME"));
		atributoLido.setDescricao(rs.getString("DESCRICAO"));		
		atributoLido.setStatus(rs.getString("STATUS").charAt(0));
		
		return atributoLido;
	}

    public List<Atributo> listar() throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Atributo> lista = new ArrayList();
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtListar);
            rs = stmt.executeQuery();
            
            Atributo atributo = null;            
            while(rs.next()){            	
            	atributo = extrairAtributoDoResultSet(rs);                
                lista.add(atributo);
            }            
            return lista;
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar atributos. Origem="+ex.getMessage());
        }finally{
            try{rs.close();}catch(Exception ex){System.out.println("Erro ao fechar result set. Ex="+ex.getMessage());};
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();;}catch(Exception ex){System.out.println("Erro ao fechar conexao. Ex="+ex.getMessage());};               
        }

    }
    
    public List<Atributo> listar(AtributoFiltroDTO filtros) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Atributo> lista = new ArrayList();
        
        String logMsg = "Iniciando a listagens de Atributo DAO";
        log.info(logMsg);
        
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtListar);
            rs = stmt.executeQuery();
            
            
            //TODO: tratar os filtros aqui!
            
            Atributo atributo = null;
            
            while(rs.next()){
            	
            	atributo = extrairAtributoDoResultSet(rs);
                
                lista.add(atributo);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de Atributo. Origem="+ex.getMessage());
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
            throw new RuntimeException("Erro ao excluir Atributo. Origem="+ex.getMessage());
        }finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();;}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};               
        }
    }
    
        public void alterar(Atributo atributo) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        List<Atributo> lista = new ArrayList();
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtAlterar);
            
            stmt.setString(1, atributo.getTipo());            
            stmt.setString(2, atributo.getNome());
            stmt.setString(3, atributo.getDescricao());            
            stmt.setString(4, String.valueOf(atributo.getStatus()));            
            stmt.setInt(5, atributo.getCodAtributo());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao alterar atributo. Origem="+ex.getMessage());
        }finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();;}catch(Exception ex){System.out.println("Erro ao fechar conexao. Ex="+ex.getMessage());};               
        }
    }
}
