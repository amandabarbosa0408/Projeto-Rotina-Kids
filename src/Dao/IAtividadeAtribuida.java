package Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Atividade;
import model.Filho;

public interface IAtividadeAtribuida {
    public boolean cadastrarAtividadeAtribuida(int status,Filho filho,Atividade atividade)throws SQLException;
    public void relatorioPorFilho(ArrayList<Filho> filhos) throws SQLException;
}
