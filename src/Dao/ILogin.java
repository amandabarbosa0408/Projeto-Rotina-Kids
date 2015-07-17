package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Login;

public interface ILogin {
    
    	boolean validar(Login login) throws SQLException;	
	void listar() throws SQLException;
   
}
