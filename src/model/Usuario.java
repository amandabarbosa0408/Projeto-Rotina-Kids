package model;


public class Usuario {
    private int pk_usuario;
    private String nome_usuario;
    private String cpf;
    
    public Usuario(String nome_usuario, String cpf) {
        this.nome_usuario = nome_usuario;
        this.cpf = cpf;
     
    }
    
    public Usuario(int pk_usuario, String nome_usuario, String cpf) {
        this.pk_usuario = pk_usuario;
        this.nome_usuario = nome_usuario;
        this.cpf = cpf;
     
    }

    public int getPk_usuario() {
        return pk_usuario;
    }

    public void setPk_usuario(int pk_usuario) {
        this.pk_usuario = pk_usuario;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    

    
}
