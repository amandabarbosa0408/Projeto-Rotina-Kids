package Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Filho;
import model.Responsavel;


public interface IResponsavel {
    
    boolean validarDados(Responsavel responsavel) throws SQLException;
    String inserirResponsavel(Responsavel responsavel) throws SQLException;
    int buscarId(Responsavel responsavel)throws SQLException; 
    public ArrayList<Filho> buscarFilhosDoResponsavel(Responsavel responsavel) throws SQLException;
    
  
}
