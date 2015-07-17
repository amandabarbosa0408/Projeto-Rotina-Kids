package view;

import Controller.AtividadeController;
import Dao.AtividadeAtribuidaDao;
import Dao.AtividadeDao;
import Dao.ResponsavelDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import model.Atividade;
import model.Filho;
import model.Responsavel;
import static view.ManterAtividade.conexao;


public class ManterAtividadeAtribuida {
    
    static Connection conexao;
    Responsavel responsavel;
    private Object entrada_grupoEtario;
    
    public ManterAtividadeAtribuida(Connection conexaoBD, Responsavel responsavel) {
	this.conexao = conexaoBD;
        this.responsavel = responsavel;
    }
    
    
     void realizarAtividades(Filho filhoEscolhido) throws SQLException {
        
        System.out.println(" ________________________________________");
        System.out.println("|                                        |");
        System.out.println("|  ROTINA KIDS - ATIVIDADES REALIZADAS   |");
        System.out.println("| Responsavel: "+responsavel.getNome_usuario());
        System.out.println("| Filho: "+filhoEscolhido.getNome_usuario());
        System.out.println("|________________________________________|");
        
        AtividadeDao atividadeDao = new AtividadeDao(conexao);
        ArrayList<Atividade> listaAtividades = new ArrayList();           
        listaAtividades = atividadeDao.buscarListaDeAtividadesParaFilho(filhoEscolhido);

        int status=0;
        Scanner entrada_status = new Scanner(System.in);
        AtividadeController atividadeController = new AtividadeController();
        
        if(listaAtividades.size()== 0){
            System.out.println("Nao existem Atividades cadastradas para o filho!");
        }
        else{
            System.out.println("Digite\n 1 - Realizada\n 0 - Nao Realizada");
            System.out.println("__________________________________________");
            
            for(int contador=0;contador<listaAtividades.size();contador++){   
                
                System.out.println(""+contador+" - "+listaAtividades.get(contador).getNome_atividade()); 
                
                status = entrada_status.nextInt();
                if(atividadeController.validarStatus(status)){
                    AtividadeAtribuidaDao atividadeAtribuidaDao = new AtividadeAtribuidaDao(conexao);
                    if(atividadeAtribuidaDao.cadastrarAtividadeAtribuida(status, filhoEscolhido, listaAtividades.get(contador))){
                        System.out.println("Atividade Atribuida cadastrada com sucesso!");
                    } 
                    else{
                         System.out.println("Filho já realizou esta atividade hoje!!");
                    }
                }
                else{
                    System.out.println("Valor Incorreto!");
                }  
            }  
        }   
    }
     
      void telaRelatorioAtividadesAtribuidas(Responsavel responsavel) throws SQLException {
        
        ResponsavelDao responsavelDao = new ResponsavelDao(conexao);
        ArrayList arrayFilhos  = new ArrayList();
        arrayFilhos = responsavelDao.buscarFilhosDoResponsavel(responsavel);
        
        if(arrayFilhos == null){
            System.out.println("Nao existem filhos cadastrados!");      
        }
        else{
            AtividadeAtribuidaDao atividadeAtribuidaDao = new AtividadeAtribuidaDao(conexao);
            atividadeAtribuidaDao.relatorioPorFilho(arrayFilhos);
        }
    }
    
}
