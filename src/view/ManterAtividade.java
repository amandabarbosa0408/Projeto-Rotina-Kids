package view;

import Controller.AtividadeAtribuidaController;
import Controller.AtividadeController;
import Dao.AtividadeAtribuidaDao;
import Dao.AtividadeDao;
import Dao.FilhoDao;
import Dao.ResponsavelDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import model.Atividade;
import model.Filho;
import model.Responsavel;
import static view.ManterFilho.conexao;


public class ManterAtividade {
    
    static Connection conexao;
    Responsavel responsavel;
    private Object entrada_grupoEtario;
    
    public ManterAtividade(Connection conexaoBD, Responsavel responsavel) {
	this.conexao = conexaoBD;
        this.responsavel = responsavel;
    }

    void telaPrincipalAtividade() throws SQLException{
       
        int opcao = 10;
        Scanner entrada_opcao = new Scanner(System.in);
        do{
            
            System.out.println(" ________________________________________");
            System.out.println("|                                        |");
            System.out.println("|           ROTINA KIDS - ATIVIDADE      |");
            System.out.println("| Responsavel: "+responsavel.getNome_usuario());
            System.out.println("|________________________________________|");
            System.out.println("| 1- Realizar Atividades do Dia          |");
            System.out.println("| 2- Cadastrar                           |");
            System.out.println("| 3- Remover                             |");           
            System.out.println("| 4- Listar Atividades Cadastradas       |");
            System.out.println("| 5- Voltar                              |");        
            System.out.println("|________________________________________|");
            
            opcao = entrada_opcao.nextInt();
            
            
            if(opcao == 1){  
   
                ResponsavelDao responsavelDao = new ResponsavelDao(conexao);
                ArrayList<Filho> arrayFilhos  = new ArrayList();
                arrayFilhos = responsavelDao.buscarFilhosDoResponsavel(responsavel);
                
                if(arrayFilhos == null){
                     System.out.println("Nao existem filho(s) cadastrados!");
                }
                else{
                    ManterFilho manterFilho = new ManterFilho(conexao,responsavel);
                    Filho filhoEscolhido = manterFilho.telaSelecionarFilho(arrayFilhos);
                    
                    ManterAtividadeAtribuida manterAtividadeAtribuida = new ManterAtividadeAtribuida(conexao,responsavel);
                    manterAtividadeAtribuida.realizarAtividades(filhoEscolhido);
                }           
            }
            else if(opcao == 2){
                telaCadastroAtividade();
            }
            else if(opcao == 3){
                
                AtividadeDao atividadeDao = new AtividadeDao(conexao);
                ArrayList<Atividade> arrayAtividades  = new ArrayList();
                arrayAtividades = atividadeDao.buscarListaDeAtividadesCadastradas();
                
                if(arrayAtividades == null){
                    System.out.println("Nao existem atividades cadastradas!");
                }
                else{
                     
                    Atividade AtividadeASerExcluida = telaSelecionarAtividadeParaExcluir(arrayAtividades);

                    if(AtividadeASerExcluida == null){
                        System.out.println("Opcao Invalida!");
                    }
                    else{
                        System.out.println("Deseja realmente excluir!? (1-SIM/0-NAO): "+AtividadeASerExcluida.getNome_atividade());
                        Scanner entrada_confirmacaoExclusao = new Scanner(System.in);
                        int opcaoExclusao;
                        opcaoExclusao = entrada_confirmacaoExclusao.nextInt();

                        if(opcaoExclusao == 1){

                            if(atividadeDao.excluirAtividade(AtividadeASerExcluida)){
                                System.out.println("Atividade excluida com sucesso!");
                            }
                            else{
                                System.out.println("A atividade nao foi excluida!");
                            }
                        }
                        else{
                            System.out.println("Exclusao cancelada!");
                        }
                    }   
                }
            }  
            else if(opcao == 4){
                 AtividadeDao atividadeDao = new AtividadeDao(conexao);
                 ArrayList<Atividade> arrayAtividades  = new ArrayList();
                 arrayAtividades = atividadeDao.buscarListaDeAtividadesCadastradas();
                    
                 for(int contador=0;contador<arrayAtividades.size();contador++){   
                        System.out.println(""+contador+" - "+arrayAtividades.get(contador).getNome_atividade()+"| Grupo Etario: "+arrayAtividades.get(contador).getGrupoEtario_atividade()+"| Quantidade de Moedas:"+arrayAtividades.get(contador).getQtdMoedas_atividade());
                    } 
            }
            else if(opcao == 5){
                ManterResponsavel manterResponsavel = new ManterResponsavel(conexao);
                manterResponsavel.telaPrincipalResponsavel(responsavel);

            }
            else{
                System.out.println("Opcao Invalida!!");

            }  
        }while(opcao!=10); 
    }
    

    
     public void telaCadastroAtividade() throws SQLException{
               
         
        System.out.println(" ________________________________________");
        System.out.println("|                                        |");
        System.out.println("|  ROTINA KIDS - CADASTRO DE ATIVIDADE   |");
        System.out.println("|________________________________________|");
        

        Scanner entrada_nome_atividade = new Scanner(System.in);
        Scanner entrada_descricao_atividade = new Scanner(System.in);
        Scanner entrada_grupoEtario_atividade = new Scanner(System.in);
	Scanner entrada_qtdMoedas_atividade = new Scanner(System.in);
        Scanner entrada_opcao = new Scanner(System.in);
        
        String nome_atividade;
	String descricao_atividade;
	int qtdMoedas_atividade;
        int grupoEtario_atividade;
        
        
        System.out.println("Nome da Atividade:");
        nome_atividade = entrada_nome_atividade.nextLine();
        System.out.println("Descricao:");
        descricao_atividade = entrada_descricao_atividade.nextLine();
        System.out.println("Grupo Etario\n 0 - 2 a 3 anos \n 1 - 4 a 5 anos \n 2 - 6 a 8 anos \n 3 - 9 a 11 anos \n 4 - 12 a 14 anos\n");
        grupoEtario_atividade = entrada_grupoEtario_atividade.nextInt();
	System.out.println("Quantidade de moedas:");
        qtdMoedas_atividade = entrada_qtdMoedas_atividade.nextInt();
          
	Atividade atividade = new Atividade(nome_atividade,descricao_atividade,grupoEtario_atividade,qtdMoedas_atividade);
        atividade.setDescricao_atividade(descricao_atividade);
        atividade.setGrupoEtario_atividade(grupoEtario_atividade);
        atividade.setNome_atividade(nome_atividade);
        atividade.setQtdMoedas_atividade(qtdMoedas_atividade);
        
        
        AtividadeController atividadeController = new AtividadeController();
      
        
        if(atividadeController.validarDados(atividade)){
            AtividadeDao atividadeDao = new AtividadeDao(conexao);
          
                        
            if(atividadeDao.validarDados(atividade) == false){ 
                System.out.println(atividadeDao.inserirAtividade(atividade));
            }
            else{
                System.out.println("Dados Invalidos!");
            }
         }
    }  

    private Atividade telaSelecionarAtividadeParaExcluir(ArrayList<Atividade> arrayAtividades) {
         Scanner entrada_atividadeEscolhida = new Scanner(System.in);
        int atividadeEscolhida;
         
        System.out.println(" ___________________________________________________");
        System.out.println("|                                                   |");
        System.out.println("|  ROTINA KIDS - SELECIONAR ATIVIDADE PARA EXCLUSAO |");
        System.out.println("| Responsavel: "+responsavel.getNome_usuario());
        System.out.println("|___________________________________________________|");
        
        for(int contador=0;contador<arrayAtividades.size();contador++){   
            System.out.println(""+contador+" - "+arrayAtividades.get(contador).getNome_atividade()+"| Grupo Etario: "+arrayAtividades.get(contador).getGrupoEtario_atividade()+"| Quantidade de Moedas:"+arrayAtividades.get(contador).getQtdMoedas_atividade());
        } 
        atividadeEscolhida = entrada_atividadeEscolhida.nextInt();
        
        AtividadeAtribuidaController atividadeAtribuidaController = new AtividadeAtribuidaController();
        if(atividadeAtribuidaController.validarOpcaoDeAtividadeParaExclusao(atividadeEscolhida,arrayAtividades.size())){
            return arrayAtividades.get(atividadeEscolhida);
        }
        else{
            return null;
        }  
    }
}

