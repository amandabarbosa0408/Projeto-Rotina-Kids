package Dao;

import Controller.LoginController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.Login;
import model.Usuario;

public class LoginDao implements ILogin {

	static Connection conexao;
	
        public LoginDao(Connection conexaoBD) {
		this.conexao = conexaoBD;     
	}

  	public boolean validar(Login login) throws SQLException {
		try {
			PreparedStatement pstmt = conexao.prepareStatement("select * from responsavel where login ='"+login.getLogin()+"' and senha ='"+login.getSenha()+"'");
			
			ResultSet rs = pstmt.executeQuery();
			
			String nome = null;
			String senha = null;
			
			while(rs.next()) {
				nome = rs.getString("login");
				senha = rs.getString("senha");
			}
			
			if(nome != null && senha != null) {
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

	 //Posso passar uma query que nÃ£o retorna nada (insert, update, delete)
    public static boolean executa(String sql){
       
        Statement stmt = null;
       
        try{
            stmt = conexao.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("executando "+sql);
            //bd.con.close();
        }
        catch(Exception e){
            System.out.println("Erro="+e);
            return false;
        }
        return true;
    }

   
    
    @Override
	public void listar() throws SQLException {
            try {
                PreparedStatement pstmt = conexao.prepareStatement("select * from usuario");	
                ResultSet rs = pstmt.executeQuery();
                        
                while(rs.next())  
                {  
                    String nome= rs.getString("nome_usuario");   
                    System.out.println(nome);
                }

		} catch(SQLException e) {
                    System.out.println("Erro = "+e.getMessage());
		}
	}
	
}
