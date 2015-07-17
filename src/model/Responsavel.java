package model;

import java.sql.Connection;


public class Responsavel extends Usuario{
    private int pk_responsavel; // chave estrangeira de usuario
    private String login;
    private String senha;
    
    public Responsavel(Usuario usuario,Login login) {
        super(usuario.getNome_usuario(), usuario.getCpf());
        this.login = login.getLogin();
        this.senha = login.getSenha();
        this.pk_responsavel = usuario.getPk_usuario();
       
    }

    public Responsavel(String nome_responsavel,String cpf_responsavel, String login, String senha) {
        super(nome_responsavel, cpf_responsavel);
        this.pk_responsavel = pk_responsavel;
        this.login = login;
        this.senha = senha;
    }

    public int getPk_responsavel() {
        return pk_responsavel;
    }

    public void setPk_responsavel(int pk_responsavel) { 
        this.pk_responsavel = pk_responsavel;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
