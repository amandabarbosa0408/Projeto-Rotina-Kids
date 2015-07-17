package Dao;

import static Dao.LoginDao.conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Filho;
import model.Responsavel;

public class FilhoDao implements IFilho {
                                                                                                                                                                                                    
    static Connection conexao;
    Responsavel responsavel;
	
    public FilhoDao(Connection conexaoBD,Responsavel responsavel) {
        this.conexao = conexaoBD;
        this.responsavel = responsavel;         
    }

    @Override
    public boolean validarDados(Filho filho, Responsavel responsavel) throws SQLException {	
        try {
			PreparedStatement pstmt = conexao.prepareStatement("select * from usuario where cpf ='"+filho.getCpf()+"'");
			ResultSet rs = pstmt.executeQuery();
			String nome = null;
			
			while(rs.next()) {
                            nome = rs.getString("nome_usuario");
			}
			
			if(nome != null) {
				return true;
			}
			else {
				return false;
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
    }
    
   @Override
    public int buscarId(Filho filho) throws SQLException {	
       int id = 0;
        try {
		PreparedStatement pstmt = conexao.prepareStatement("select pk_usuario from usuario where cpf ='"+filho.getCpf()+"'");
			
		ResultSet rs = pstmt.executeQuery();
                        
                while(rs.next())  
                {  
                   id = rs.getInt("pk_usuario");   
                }

		} catch(SQLException e) {
            System.out.println("Erro = "+e.getMessage());
      
		}		
        return id;
    }

    @Override
    public String inserirFilho(Filho filho) throws SQLException {
         try{
            Statement statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             
            String sqlCadastraUsuario = "INSERT INTO USUARIO (nome_usuario, cpf) VALUES ('"+filho.getNome_usuario()+"','"+filho.getCpf()+"');";
            statement.executeUpdate(sqlCadastraUsuario);
            
            
            filho.setPk_filho(buscarId(filho)); 
            String sqlCadastraFilho = "INSERT INTO FILHO (pk_filho,cod_responsavel,idade_filho) VALUES ('"+filho.getPk_filho()+"','"+filho.getCod_responsavel()+"','"+filho.getIdade_filho()+"');";
            statement.executeUpdate(sqlCadastraFilho);
            
            String sqlCadastraBancoDeMoedas = "INSERT INTO BANCO_MOEDAS (cod_filho,saldo) VALUES ('"+filho.getPk_filho()+"','0');";
            statement.executeUpdate(sqlCadastraBancoDeMoedas);
            

            return "Cadastro de Filho Realizado com Sucesso!";
        }
        catch(SQLException e){
            return e.toString();
        }     
    }


    @Override
    public boolean excluirFilho(Filho filho) throws SQLException{
     
        try {
            Statement statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "DELETE FROM filho WHERE pk_filho = '"+filho.getPk_filho()+"'";
            statement.executeUpdate(sql);
            
            sql = "DELETE FROM usuario WHERE pk_usuario = '" +filho.getPk_filho()+"'";
            statement.executeUpdate(sql);
            
            sql = "DELETE FROM banco_moedas WHERE cod_filho = '" +filho.getPk_filho()+"'";
            statement.executeUpdate(sql);
            
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean AlterarDadosFilho(Filho filho, int novaIdade, String novoNome, String novoCpf) throws SQLException {
        try {
            Statement statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "UPDATE filho SET idade_filho = '" +novaIdade+ "' WHERE pk_filho = " +filho.getPk_filho();
            statement.executeUpdate(sql);
            
            sql = "UPDATE usuario SET nome_usuario = '" +novoNome+ "', cpf = '"+novoCpf+"' WHERE pk_usuario = " +filho.getPk_filho();
            statement.executeUpdate(sql);
            
            return true;
        } catch (SQLException e) {        
            return false;
        }
    }
}
