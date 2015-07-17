package Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Atividade;
import model.Filho;

public interface IAtividade {
    
    public  boolean excluirAtividade(Atividade atividade) throws SQLException;
    public ArrayList<Atividade> buscarListaDeAtividadesCadastradas() throws SQLException;
    public ArrayList<Atividade> buscarListaDeAtividadesParaFilho(Filho filho);
    public String inserirAtividade(Atividade atividade) throws SQLException;
    public int buscarIdAtividade(Atividade atividade) throws SQLException;
    public boolean validarDados(Atividade atividade) throws SQLException;
 
}
