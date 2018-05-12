package dao;

import conexao.Conexao;
import entidades.Usuario;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DAOUsuario {
    private final Conexao conexao;
    
    public DAOUsuario() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        this.conexao = new Conexao();
    }

    public void inserirPessoa(Usuario pessoa) throws ClassNotFoundException, IllegalAccessException, InstantiationException,
            SQLException, NoSuchAlgorithmException {

        PreparedStatement stmt = conexao.getConnection().prepareStatement("INSERT INTO Usuario(nome, email, cpf, login, senha) " +
				"VALUES (?, ?, ?, ?, ?)");
            try{
                stmt.setString(1, pessoa.getNome());
                stmt.setString(2, pessoa.getEmail());
                stmt.setString(3, pessoa.getCpf());
                stmt.setString(4, pessoa.getLogin());
                stmt.setString(5, pessoa.getSenha());
                stmt.executeUpdate();
            }catch (SQLException u) { 
                throw new RuntimeException(u);
            } 
    }

    public Usuario consultaPessoa(String cpf)  throws ClassNotFoundException, IllegalAccessException, InstantiationException,
            SQLException {
        Usuario pessoa = new Usuario(null, null, null, null, null);
        String sql = "SELECT email, senha FROM pessoa WHERE pessoa.email AND pessoa.senha = " + cpf 
                + " AND pessoa.cod_endereco = endereco.cod_endereco;";
        PreparedStatement pstm = conexao.getConnection().prepareStatement(sql);        
        ResultSet rs = pstm.executeQuery();
        //return if rs.getRow() > 0;
        while (rs.next()){
            pessoa.setNome(rs.getString("nome"));
            pessoa.setEmail(rs.getString("email"));
            pessoa.setCpf(rs.getString("cpf"));
            pessoa.setLogin(rs.getString("login"));
            pessoa.setSenha(rs.getString("senha"));
        }
        return pessoa;
    }
    
    public Usuario verificacaoUsuarioLogin(String email, String senha) throws SQLException {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
        PreparedStatement pstm = conexao.getConnection().prepareStatement(sql);
        try{
        pstm.setObject(1, email);
        pstm.setObject(2, senha);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            usuario = new Usuario(null, null, null, null, null);
            usuario.setNome(rs.getString("nome"));
            usuario.setEmail(rs.getString("email"));
            usuario.setCpf(rs.getString("cpf"));
            usuario.setLogin(rs.getString("login"));
            usuario.setSenha(rs.getString("senha"));
        }
        return usuario;
        }catch (SQLException u) { 
            throw new RuntimeException(u);
        } 
    }
        public Usuario verificacaoUsuarioRecuperaSenha(String email, String cpf) throws SQLException {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE email = ? AND cpf = ?";
        PreparedStatement pstm = conexao.getConnection().prepareStatement(sql);
        try{
        pstm.setObject(1, email);
        pstm.setObject(2, cpf);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            usuario = new Usuario(null, null, null, null, null);
            usuario.setNome(rs.getString("nome"));
            usuario.setEmail(rs.getString("email"));
            usuario.setCpf(rs.getString("cpf"));
            usuario.setLogin(rs.getString("login"));
            usuario.setSenha(rs.getString("senha"));
        }
        return usuario;
        }catch (SQLException u) { 
            throw new RuntimeException(u);
        } 
    }
  /*  public Usuario verificacaoUsuarioRecuperaSenha(String cpf, String email) throws SQLException {
        Usuario usuario = null;
        String sql = "SELECT * FROM Usuario WHERE cpf = ? AND email = ?";
        PreparedStatement pstm = conexao.getConnection().prepareStatement(sql);
        pstm.setObject(1, cpf);
        pstm.setObject(2, email);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            usuario = new Usuario(null, null, null, null, null);
            usuario.setNome(rs.getString("nome"));
            usuario.setEmail(rs.getString("email"));
            usuario.setCpf(rs.getString("cpf"));
            usuario.setLogin(rs.getString("login"));
            usuario.setSenha(rs.getString("senha"));
        }
        System.out.println("ta na função");
        return usuario;
    }*/
    
    public List<Usuario> consultarPessoa()  throws SQLException {
        List<Usuario> lista = new ArrayList();
        String sql = "SELECT * FROM usuario";
        PreparedStatement pstm = conexao.getConnection().prepareStatement(sql);        
        ResultSet rs = pstm.executeQuery();
        while (rs.next()){
            Usuario pessoa = new Usuario(null, null, null, null, null);
            pessoa.setNome(rs.getString("nome"));
            pessoa.setEmail(rs.getString("email"));
            pessoa.setCpf(rs.getString("cpf"));
            pessoa.setLogin(rs.getString("login"));
            pessoa.setSenha(rs.getString("senha"));
            lista.add(pessoa);
        }
        return lista;
    }
        public void atualizaSenha(Usuario pessoa, String senhaNova)  throws ClassNotFoundException, IllegalAccessException, InstantiationException,
            SQLException {
   
        String sql = "UPDATE usuario SET senha = ? WHERE cpf = ?";
        PreparedStatement pstm = conexao.getConnection().prepareStatement(sql);         
        
        try{
        pstm.setString(1, senhaNova);
        pstm.setString(2, pessoa.getCpf());
        
        
        pstm.execute();
        }catch (SQLException u) { 
            throw new RuntimeException(u);
        } 
    }
}

