package br.org.ufpr.tcc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.org.ufpr.tcc.dao.ConnectionManager;
import br.org.ufpr.tcc.dto.ClienteFiltroDTO;
import br.org.ufpr.tcc.entity.Cliente;
import java.util.logging.Logger;



public class ClienteDAO {
    
    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());

    private final String stmtInserir = "INSERT INTO cliente(nome) VALUES(?)";
    private final String stmtObter = "SELECT * FROM cliente WHERE id = ?";
    private final String stmtExcluir = "DELETE * FROM cliente WHERE id = ?";
    private final String stmtListar = "SELECT * FROM cliente";
    private final String stmtListarPaginado = "SELECT * FROM cliente";
    private final String stmtAlterar = "UPDATE cliente SET nome = ? WHERE id = ?";

    public void inserir(Cliente cliente) {
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtInserir,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, cliente.getNome());
            stmt.executeUpdate();
            cliente.setId(lerIdCliente(stmt));
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir um cliente no banco de dados.", ex);
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
     }
    
    private Long lerIdCliente(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getLong(1);
    }

    public Cliente obter(Long id) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente clienteLido;
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtObter);
            stmt.setLong(1, id.longValue());
            rs = stmt.executeQuery();
            if(rs.next()){
                clienteLido = new Cliente();
                clienteLido.setNome(rs.getString("nome"));
                clienteLido.setId(rs.getLong("id"));
                return clienteLido;
            }else{
                throw new RuntimeException("Não existe cliente com este id. Id="+id);
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar um cliente no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{rs.close();}catch(Exception ex){System.out.println("Erro ao fechar result set. Ex="+ex.getMessage());};
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();;}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }

    }

    public List<Cliente> listar() throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> lista = new ArrayList();
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtListar);
            rs = stmt.executeQuery();
            
            Cliente cliente = null;
            
            while(rs.next()){
            	cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setId(rs.getLong("id"));
                
                lista.add(cliente);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de clientees. Origem="+ex.getMessage());
        }finally{
            try{rs.close();}catch(Exception ex){System.out.println("Erro ao fechar result set. Ex="+ex.getMessage());};
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();;}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};               
        }

    }
    
    public List<Cliente> listar(ClienteFiltroDTO filtros) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> lista = new ArrayList();
        
        String logMsg = "Iniciando a listagens de Cliente DAO";
        log.info(logMsg);
        
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtListar);
            rs = stmt.executeQuery();
            
            
            //TODO: tratar os filtros!
            
            Cliente cliente = null;
            
            while(rs.next()){
            	cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setId(rs.getLong("id"));
                
                lista.add(cliente);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de clientees. Origem="+ex.getMessage());
        }finally{
            try{rs.close();}catch(Exception ex){System.out.println("Erro ao fechar result set. Ex="+ex.getMessage());};
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();;}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};               
        }

    }
    
    public void excluir(int id) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> lista = new ArrayList();
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtExcluir);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao excliur um cliente. Origem="+ex.getMessage());
        }finally{
            try{rs.close();}catch(Exception ex){System.out.println("Erro ao fechar result set. Ex="+ex.getMessage());};
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();;}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};               
        }
    }
    
        public void alterar(Cliente cliente) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        List<Cliente> lista = new ArrayList();
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtAlterar);
            stmt.setString(1, cliente.getNome());
            stmt.setLong(2, cliente.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao alterar um cliente. Origem="+ex.getMessage());
        }finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();;}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};               
        }
    }

}
