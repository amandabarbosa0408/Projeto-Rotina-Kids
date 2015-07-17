
package Controller;


public class AtividadeAtribuidaController {
    
    public boolean validarOpcaoDeAtividadeParaExclusao(int opcao, int tamanhoListaDeAtividades){
        if(opcao <= tamanhoListaDeAtividades){
            return true;
        }
        else{
            return false;
        }
    }   
}
