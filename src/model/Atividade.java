package model;


public class Atividade {
    private int pk_atividade;
    private String nome_atividade;
    private String descricao_atividade;
    private int grupoEtario_atividade;
    private double qtdMoedas_atividade;

    public Atividade(String nome_atividade, String descricao_atividade, int grupoEtario_atividade, int qtdMoedas_atividade) {
   
        this.nome_atividade = nome_atividade;
        this.descricao_atividade = descricao_atividade;
        this.grupoEtario_atividade = grupoEtario_atividade;
        this.qtdMoedas_atividade = qtdMoedas_atividade;
    }
    
    public int getPk_atividade() {
        return pk_atividade;
    }

    public void setPk_atividade(int pk_atividade) {
        this.pk_atividade = pk_atividade;
    }

    public String getNome_atividade() {
        return nome_atividade;
    }

    public void setNome_atividade(String nome_atividade) {
        this.nome_atividade = nome_atividade;
    }

    public String getDescricao_atividade() {
        return descricao_atividade;
    }

    public void setDescricao_atividade(String descricao_atividade) {
        this.descricao_atividade = descricao_atividade;
    }

    public int getGrupoEtario_atividade() {
        return grupoEtario_atividade;
    }

    public void setGrupoEtario_atividade(int grupoEtario_atividade) {
        this.grupoEtario_atividade = grupoEtario_atividade;
    }

    public double getQtdMoedas_atividade() {
        return qtdMoedas_atividade;
    }

    public void setQtdMoedas_atividade(int qtdMoedas_atividade) {
        this.qtdMoedas_atividade = qtdMoedas_atividade;
    }   
}
