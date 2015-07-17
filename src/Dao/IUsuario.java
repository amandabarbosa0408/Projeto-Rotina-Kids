
package Dao;

import java.sql.SQLException;
import model.Filho;
import model.Login;
import model.Usuario;

public interface IUsuario {
    Usuario buscarDados(Login login) throws SQLException;
    public Filho buscarDadosFilho(Filho filho) throws SQLException;
}
