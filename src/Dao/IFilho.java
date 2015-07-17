package Dao;

import java.sql.SQLException;
import model.Filho;
import model.Responsavel;


public interface IFilho {
    
    public boolean validarDados(Filho filho, Responsavel responsavel) throws SQLException;
    public int buscarId(Filho filho) throws SQLException; 
    public String inserirFilho(Filho filho) throws SQLException;
    public boolean AlterarDadosFilho(Filho filho, int novaIdade,String novoNome, String novoCpf) throws SQLException;
    public boolean excluirFilho(Filho filho) throws SQLException;
    
}
