package Controller;

import Dao.ILogin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Login;
import model.Usuario;

public class LoginController{
   
  
    public boolean validar(Login login) throws SQLException {
       if(login.getLogin().equals("") || login.getSenha().equals("")) {
		return false;
	}
	else {
		return true;
	}
    }

}
