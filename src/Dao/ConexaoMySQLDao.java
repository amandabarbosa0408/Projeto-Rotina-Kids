package Dao;

import model.ConexaoMySQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//Início da classe de conexão//

public class ConexaoMySQLDao implements IConexaoMySQLDao{
    
    ConexaoMySQL conexaoMySQL = new ConexaoMySQL("root","");


     private static Connection conexao;
     
     @Override
     public Connection getConexao() {
            
                conexaoMySQL.setSenha("");
                conexaoMySQL.setUsuario("root");
            
                if(conexaoMySQL.getUsuario() == "" || conexaoMySQL.getUsuario() == null ) {
		System.out.println(" nao Conectado");	
                    return null;
		}
		try {
			if(conexaoMySQL.getConexao() == null) {
				conexao = DriverManager.getConnection("jdbc:mysql://localhost/_rotinaKids", conexaoMySQL.getUsuario(), conexaoMySQL.getSenha());
			}
			return conexao;
	     } catch (SQLException e) {
	    	 	throw new RuntimeException("Erro na conexão: "+e.getMessage());
	    }
	}
}