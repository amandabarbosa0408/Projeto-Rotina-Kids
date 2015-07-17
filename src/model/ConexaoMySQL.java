package model;

import java.sql.Connection;

public class ConexaoMySQL {
    private String usuario;
    private String senha;
    private static Connection conexao;
	
  
    public ConexaoMySQL(String usuario, String senha) {
	this.usuario = usuario;
	this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static Connection getConexao() {
        return conexao;
    }

    public static void setConexao(Connection conexao) {
        ConexaoMySQL.conexao = conexao;
    }
    
 
    
}
