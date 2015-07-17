
package model;
import java.util.Date;


public class AtividadeAtribuida {
    private int pk_atividadeAtribuida;
    private int fk_atividade;
    private int fk_filho;
    private int status_atividadeAtribuida; // 0- Não realizada, 1- Realizada;
    private Date data_atividadeAtribuida;

    public AtividadeAtribuida(int pk_atividadeAtribuida, int fk_atividade, int fk_filho, int status_atividadeAtribuida, Date data_atividadeAtribuida) {
        this.pk_atividadeAtribuida = pk_atividadeAtribuida;
        this.fk_atividade = fk_atividade;
        this.fk_filho = fk_filho;
        this.status_atividadeAtribuida = status_atividadeAtribuida;
        this.data_atividadeAtribuida = data_atividadeAtribuida;
    }

    public int getPk_atividadeAtribuida() {
        return pk_atividadeAtribuida;
    }

    public void setPk_atividadeAtribuida(int pk_atividadeAtribuida) {
        this.pk_atividadeAtribuida = pk_atividadeAtribuida;
    }

    public int getFk_atividade() {
        return fk_atividade;
    }

    public void setFk_atividade(int fk_atividade) {
        this.fk_atividade = fk_atividade;
    }

    public int getFk_filho() {
        return fk_filho;
    }

    public void setFk_filho(int fk_filho) {
        this.fk_filho = fk_filho;
    }

    public int getStatus_atividadeAtribuida() {
        return status_atividadeAtribuida;
    }

    public void setStatus_atividadeAtribuida(int status_atividadeAtribuida) {
        this.status_atividadeAtribuida = status_atividadeAtribuida;
    }

    public Date getData_atividadeAtribuida() {
        return data_atividadeAtribuida;
    }

    public void setData_atividadeAtribuida(Date data_atividadeAtribuida) {
        this.data_atividadeAtribuida = data_atividadeAtribuida;
    }
    
    
    
    
}
