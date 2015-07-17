
package Controller;

import java.sql.SQLException;
import Dao.ILogin;
import Dao.IResponsavel;
import model.Login;
import model.Responsavel;

public class ResponsavelController{

    public boolean validarDados(Responsavel responsavel) throws SQLException {
        
         if( responsavel.getSenha().equals("") || responsavel.getNome_usuario().equals("") || responsavel.getCpf().equals("") ||  responsavel.getLogin().equals("")){
		return false;
	}
         else if(responsavel.getCpf().length() > 30){
             return false;
         }
	else {
		return true;
	}
  
    } 
}

