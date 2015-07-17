package Controller;

import model.Filho;

public class FilhoController {

    public boolean validarDados(Filho filho) {
         if(filho.getNome_usuario().equals("") || filho.getCpf().equals("")){   
		return false;
	}
         
         else if(filho.getIdade_filho() < 2){
             return false;
         }
	else {
		return true;
	}
    }
    
}
