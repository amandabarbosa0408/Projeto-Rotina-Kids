package Controller;

import model.Atividade;


public class AtividadeController {
    
    public boolean validarDados(Atividade atividade) {
        if(atividade.getNome_atividade().equals("")){   
		return false;
	}
         else if(atividade.getGrupoEtario_atividade() > 4){
             return false;
         }
	else {
		return true;
	}
    }
    
    public boolean validarStatus(int status){
        if(status == 0 || status == 1){
            return true;
        }
        else{
            return false;
        }
    } 
}
