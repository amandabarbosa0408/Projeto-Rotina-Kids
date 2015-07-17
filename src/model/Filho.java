package model;

public class Filho extends Usuario{
    private int pk_filho;
    private int cod_responsavel;
    private int idade_filho;

    public Filho(String nome_filho,String cpf, int cod_responsavel,int idade_filho) {
        super(nome_filho,cpf);
        this.cod_responsavel = cod_responsavel;
        this.idade_filho = idade_filho;
        this.cod_responsavel = cod_responsavel;
    }

    public Filho(int pk_filho, String nome_filho, String cpf, int cod_responsavel, int idade_filho) {
        super(nome_filho,cpf);
        this.pk_filho = pk_filho;
        this.cod_responsavel = cod_responsavel;
        this.idade_filho = idade_filho;
        this.cod_responsavel = cod_responsavel;
    }


    public int getPk_filho() {
        return pk_filho;
    }

    public void setPk_filho(int pk_filho) { 
        this.pk_filho = pk_filho;
    }

    public int getCod_responsavel() {
        return cod_responsavel;
    }

    public void setCod_responsavel(int cod_responsavel) {
        this.cod_responsavel = cod_responsavel;
    }
    public int getIdade_filho() {
        return idade_filho;
    }

    public void setIdade_filho(int idade_filho) {
        this.idade_filho = idade_filho;
    }
    
    
    
}
