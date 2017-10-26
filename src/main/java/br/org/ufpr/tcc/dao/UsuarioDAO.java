package br.org.ufpr.tcc.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import br.org.ufpr.tcc.dto.UsuarioFiltroDTO;
import br.org.ufpr.tcc.entity.Pagina;
import br.org.ufpr.tcc.entity.Usuario;
import br.org.ufpr.tcc.util.Util;



public class UsuarioDAO extends LazarusDAO<Usuario> {

	public List<Usuario> listar(UsuarioFiltroDTO filtros) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
        Root<Usuario> root = cq.from(Usuario.class);
        Predicate[] predicados = buildPredicatePesquisa(filtros, cb, root);

        cq.where(cb.and(predicados));

        Pagina pagina = filtros.getPagina();

        return findByCriteriaQuery(cq, pagina);
    }

    private Predicate[] buildPredicatePesquisa(UsuarioFiltroDTO filtros, CriteriaBuilder cb,
        Root<Usuario> root) {
        Predicate[] predicados = { };
        
        Path<String> pathCampoTexto = root.get(Usuario.NOME);
        
        if(StringUtils.isNotBlank(filtros.getNome())){
            predicados = Util.add(predicados, cb.like(pathCampoTexto, Util.likeFormat(filtros.getNome())));
        }
        
        //... outros predicados/filtros se houver
        
        return predicados;
    }

	/*
    private Logger log = Logger.getLogger(this.getClass().getCanonicalName());

    private final String stmtInserir = "INSERT INTO usuarios(NOME, ATIVO, DT_NASC, SEXO, NACIONALIDADE, PERFIL, TELEFONE, EMAIL, CPF, RG, PASSAPORTE, END_RUA, END_NRO, END_BAIRRO, END_CIDADE, END_UF, END_COMPL, SENHA, STATUS) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String stmtObter = "SELECT * FROM usuarios WHERE cod_usuario = ?";
    private final String stmtExcluir = "UPDATE usuarios SET STATUS = 'E' WHERE cod_usuario = ? ";
    private final String stmtListar = "SELECT * FROM usuarios";
    private final String stmtListarPaginado = "SELECT * FROM usuarios";
    private final String stmtAlterar = "UPDATE usuarios SET NOME = ?, ATIVO = ?, DT_NASC = ?, SEXO = ?, NACIONALIDADE = ?, PERFIL = ?, TELEFONE = ?, EMAIL = ?, CPF = ?, RG = ?, PASSAPORTE = ?, END_RUA = ?, END_NRO = ?, END_BAIRRO = ?, END_CIDADE = ?, END_UF = ?, END_COMPL = ?, SENHA = ?, STATUS = ? WHERE COD_USUARIO = ? ";

    public void inserir(Usuario usuario) {
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtInserir,PreparedStatement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1 ,usuario.getNome());
            stmt.setString(2 ,String.valueOf(usuario.getAtivo()));
            stmt.setDate(3 , new java.sql.Date(usuario.getDtNasc().getTime()));
            stmt.setInt(4 ,usuario.getSexo());
            stmt.setString(5 ,usuario.getNacionalidade());
            stmt.setString(6 ,usuario.getPerfil());
            stmt.setString(7 ,usuario.getTelefone());
            stmt.setString(8 ,usuario.getEmail());
            stmt.setString(9 ,usuario.getCpf());
            stmt.setString(10 ,usuario.getRg());
            stmt.setString(11 ,usuario.getPassaporte());
            stmt.setString(12 ,usuario.getEndRua());
            stmt.setInt(13 ,usuario.getEndNro());
            stmt.setString(14 ,usuario.getEndBairro());
            stmt.setString(15 ,usuario.getEndCidade());
            stmt.setString(16 ,usuario.getEndUf());
            stmt.setString(17 ,usuario.getEndCompl());
            stmt.setString(18 ,usuario.getSenha());
            stmt.setString(19 ,String.valueOf(usuario.getStatus()));

            stmt.executeUpdate();
            usuario.setCodUsuario(lerIdUsuario(stmt));
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir um Usuario no banco de dados.", ex);
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
     }
    
    private Integer lerIdUsuario(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    public Usuario obter(Integer id) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuarioLido;
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtObter);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if(rs.next()){
            	
                usuarioLido = extrairUsuarioDoResultSet(rs);
                
                return usuarioLido;
                
            }else{
                throw new RuntimeException("Não existe Usuario com este id. Id="+id);
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar um Usuario no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{rs.close();}catch(Exception ex){System.out.println("Erro ao fechar result set. Ex="+ex.getMessage());};
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();;}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }

    }

	private Usuario extrairUsuarioDoResultSet(ResultSet rs) throws SQLException {
		Usuario usuarioLido;
		usuarioLido = new Usuario();
		
		usuarioLido.setCodUsuario(rs.getInt("COD_CLIENTE"));
		usuarioLido.setNome(rs.getString("NOME"));
		usuarioLido.setAtivo(rs.getString("ATIVO").charAt(0));
		usuarioLido.setDtNasc(rs.getDate("DT_NASC"));
		usuarioLido.setSexo(rs.getInt("SEXO"));
		usuarioLido.setNacionalidade(rs.getString("NACIONALIDADE"));
		usuarioLido.setPerfil(rs.getString("PERFIL"));
		usuarioLido.setTelefone(rs.getString("TELEFONE"));
		usuarioLido.setEmail(rs.getString("EMAIL"));
		usuarioLido.setCpf(rs.getString("CPF"));
		usuarioLido.setRg(rs.getString("RG"));
		usuarioLido.setPassaporte(rs.getString("PASSAPORTE"));
		usuarioLido.setEndRua(rs.getString("END_RUA"));
		usuarioLido.setEndNro(rs.getInt("END_NRO"));
		usuarioLido.setEndBairro(rs.getString("END_BAIRRO"));
		usuarioLido.setEndCidade(rs.getString("END_CIDADE"));
		usuarioLido.setEndUf(rs.getString("END_UF"));
		usuarioLido.setEndCompl(rs.getString("END_COMPL"));
		usuarioLido.setSenha(rs.getString("SENHA"));
		usuarioLido.setStatus(rs.getString("STATUS").charAt(0));
		return usuarioLido;
	}

    public List<Usuario> listar() throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> lista = new ArrayList();
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtListar);
            rs = stmt.executeQuery();
            
            Usuario usuario = null;
            
            while(rs.next()){
            	
            	usuario = extrairUsuarioDoResultSet(rs);
                
                lista.add(usuario);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de Usuarios. Origem="+ex.getMessage());
        }finally{
            try{rs.close();}catch(Exception ex){System.out.println("Erro ao fechar result set. Ex="+ex.getMessage());};
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();;}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};               
        }

    }
    
    public List<Usuario> listar(UsuarioFiltroDTO filtros) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> lista = new ArrayList();
        
        String logMsg = "Iniciando a listagens de Usuario DAO";
        log.info(logMsg);
        
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtListar);
            rs = stmt.executeQuery();
            
            
            //TODO: tratar os filtros aqui!
            
            Usuario usuario = null;
            
            while(rs.next()){
            	
            	usuario = extrairUsuarioDoResultSet(rs);
                
                lista.add(usuario);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de Usuarios. Origem="+ex.getMessage());
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
            throw new RuntimeException("Erro ao excliur um Usuario. Origem="+ex.getMessage());
        }finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();;}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};               
        }
    }
    
        public void alterar(Usuario usuario) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        List<Usuario> lista = new ArrayList();
        try{
            con = ConnectionManager.getConnection();
            stmt = con.prepareStatement(stmtAlterar);
            
            stmt.setString(1 ,usuario.getNome());
            stmt.setString(2 ,String.valueOf(usuario.getAtivo()));
            stmt.setDate(3 , new java.sql.Date(usuario.getDtNasc().getTime()));
            stmt.setString(4 ,String.valueOf(usuario.getSexo()));
            stmt.setString(5 ,usuario.getNacionalidade());
            stmt.setString(6 ,usuario.getPerfil());
            stmt.setString(7 ,usuario.getTelefone());
            stmt.setString(8 ,usuario.getEmail());
            stmt.setString(9 ,usuario.getCpf());
            stmt.setString(10 ,usuario.getRg());
            stmt.setString(11 ,usuario.getPassaporte());
            stmt.setString(12 ,usuario.getEndRua());
            stmt.setInt(13 ,usuario.getEndNro());
            stmt.setString(14 ,usuario.getEndBairro());
            stmt.setString(15 ,usuario.getEndCidade());
            stmt.setString(16 ,usuario.getEndUf());
            stmt.setString(17 ,usuario.getEndCompl());
            stmt.setString(18 ,usuario.getSenha());
            stmt.setString(19 ,String.valueOf(usuario.getStatus()));
            
            stmt.setInt(20 ,usuario.getCodUsuario());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao alterar um Usuario. Origem="+ex.getMessage());
        }finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();;}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};               
        }
    }
*/
}
