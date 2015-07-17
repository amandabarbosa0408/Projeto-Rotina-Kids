package model;

public class BancoDeMoedas {
    private int pf_bancoMoedas;
    private int cod_filho;
    private double saldo;

    public BancoDeMoedas(int pf_bancoMoedas, int cod_filho, double saldo) {
        this.pf_bancoMoedas = pf_bancoMoedas;
        this.cod_filho = cod_filho;
        this.saldo = saldo;
    }

    public int getPf_bancoMoedas() {
        return pf_bancoMoedas;
    }

    public void setPf_bancoMoedas(int pf_bancoMoedas) {
        this.pf_bancoMoedas = pf_bancoMoedas;
    }

    public int getCod_filho() {
        return cod_filho;
    }

    public void setCod_filho(int cod_filho) {
        this.cod_filho = cod_filho;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    
    
}
