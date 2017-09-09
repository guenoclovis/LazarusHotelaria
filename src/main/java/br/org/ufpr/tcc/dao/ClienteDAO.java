package br.org.ufpr.tcc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import br.org.ufpr.tcc.dto.ClienteFiltroDTO;
import br.org.ufpr.tcc.entity.Cliente;



public class ClienteDAO {
    
    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());

    private final String stmtInserir = "INSERT INTO clientes(NOME, DT_NASC, SEXO, NACIONALIDADE, TELEFONE1, TELEFONE2, EMAIL1, EMAIL2, CPF, RG, PASSAPORTE, END_RUA, END_NRO, END_BAIRRO, END_CIDADE, END_UF, END_COMPL, SENHA_ACESSO, STATUS) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String stmtObter = "SELECT * FROM clientes WHERE cod_cliente = ?";
    private final String stmtExcluir = "DELETE FROM clientes WHERE cod_cliente = ?";
    private final String stmtListar = "SELECT * FROM clientes";
    private final String stmtListarPaginado = "SELECT * FROM clientes";
    private final String stmtAlterar = "UPDATE clientes SET NOME = ?, DT_NASC = ?, SEXO = ?, NACIONALIDADE = ?, TELEFONE1 = ?, TELEFONE2 = ?, EMAIL1 = ?, EMAIL2 = ?, CPF = ?, RG = ?, PASSAPORTE = ?, END_RUA = ?, END_NRO = ?, END_BAIRRO = ?, END_CIDADE = ?, END_UF = ?, END_COMPL = ?, SENHA_ACESSO = ?, STATUS = ? WHERE COD_CLIENTE = ? ";

    public void inserir(Cliente cliente) {
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtInserir,PreparedStatement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1 ,cliente.getNome());
            stmt.setDate(2 , new java.sql.Date(cliente.getDtNasc().getTime()));
            stmt.setString(3 ,String.valueOf(cliente.getSexo()));
            stmt.setString(4 ,cliente.getNacionalidade());
            stmt.setString(5 ,cliente.getTelefone1());
            stmt.setString(6 ,cliente.getTelefone2());
            stmt.setString(7 ,cliente.getEmail1());
            stmt.setString(8 ,cliente.getEmail2());
            stmt.setString(9 ,cliente.getCpf());
            stmt.setString(10 ,cliente.getRg());
            stmt.setString(11 ,cliente.getPassaporte());
            stmt.setString(12 ,cliente.getEndRua());
            stmt.setInt(13 ,cliente.getEndNro());
            stmt.setString(14 ,cliente.getEndBairro());
            stmt.setString(15 ,cliente.getEndCidade());
            stmt.setString(16 ,cliente.getEndUf());
            stmt.setString(17 ,cliente.getEndCompl());
            stmt.setString(18 ,cliente.getSenhaAcesso());
            stmt.setString(19 ,String.valueOf(cliente.getStatus()));

            stmt.executeUpdate();
            cliente.setCodCliente(lerIdCliente(stmt));
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir um cliente no banco de dados.", ex);
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
     }
    
    private Integer lerIdCliente(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    public Cliente obter(Integer id) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente clienteLido;
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtObter);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if(rs.next()){
            	
                clienteLido = extrairClienteDoResultSet(rs);
                
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

	private Cliente extrairClienteDoResultSet(ResultSet rs) throws SQLException {
		Cliente clienteLido;
		clienteLido = new Cliente();
		
		clienteLido.setCodCliente(rs.getInt("COD_CLIENTE"));
		clienteLido.setNome(rs.getString("NOME"));
		clienteLido.setDtNasc(rs.getDate("DT_NASC"));
		clienteLido.setSexo(rs.getString("SEXO").charAt(0));
		clienteLido.setNacionalidade(rs.getString("NACIONALIDADE"));
		clienteLido.setTelefone1(rs.getString("TELEFONE1"));
		clienteLido.setTelefone2(rs.getString("TELEFONE2"));
		clienteLido.setEmail1(rs.getString("EMAIL1"));
		clienteLido.setEmail2(rs.getString("EMAIL2"));
		clienteLido.setCpf(rs.getString("CPF"));
		clienteLido.setRg(rs.getString("RG"));
		clienteLido.setPassaporte(rs.getString("PASSAPORTE"));
		clienteLido.setEndRua(rs.getString("END_RUA"));
		clienteLido.setEndNro(rs.getInt("END_NRO"));
		clienteLido.setEndBairro(rs.getString("END_BAIRRO"));
		clienteLido.setEndCidade(rs.getString("END_CIDADE"));
		clienteLido.setEndUf(rs.getString("END_UF"));
		clienteLido.setEndCompl(rs.getString("END_COMPL"));
		clienteLido.setSenhaAcesso(rs.getString("SENHA_ACESSO"));
		clienteLido.setStatus(rs.getString("STATUS").charAt(0));
		return clienteLido;
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
            	
            	cliente = extrairClienteDoResultSet(rs);
                
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
            
            
            //TODO: tratar os filtros aqui!
            
            Cliente cliente = null;
            
            while(rs.next()){
            	
            	cliente = extrairClienteDoResultSet(rs);
                
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
    
    public void excluir(Integer id) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtExcluir);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao excliur um cliente. Origem="+ex.getMessage());
        }finally{
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
            
            stmt.setString(1 ,cliente.getNome());
            stmt.setDate(2 , new java.sql.Date(cliente.getDtNasc().getTime()));
            stmt.setString(3 ,String.valueOf(cliente.getSexo()));
            stmt.setString(4 ,cliente.getNacionalidade());
            stmt.setString(5 ,cliente.getTelefone1());
            stmt.setString(6 ,cliente.getTelefone2());
            stmt.setString(7 ,cliente.getEmail1());
            stmt.setString(8 ,cliente.getEmail2());
            stmt.setString(9 ,cliente.getCpf());
            stmt.setString(10 ,cliente.getRg());
            stmt.setString(11 ,cliente.getPassaporte());
            stmt.setString(12 ,cliente.getEndRua());
            stmt.setInt(13 ,cliente.getEndNro());
            stmt.setString(14 ,cliente.getEndBairro());
            stmt.setString(15 ,cliente.getEndCidade());
            stmt.setString(16 ,cliente.getEndUf());
            stmt.setString(17 ,cliente.getEndCompl());
            stmt.setString(18 ,cliente.getSenhaAcesso());
            stmt.setString(19 ,String.valueOf(cliente.getStatus()));
            
            stmt.setInt(20 ,cliente.getCodCliente());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao alterar um cliente. Origem="+ex.getMessage());
        }finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();;}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};               
        }
    }

}
