package view;

import Controller.FilhoController;
import Controller.ResponsavelController;
import Dao.BancoDeMoedasDao;
import Dao.FilhoDao;
import Dao.ResponsavelDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import model.Filho;
import model.Responsavel;
import static view.ManterResponsavel.conexao;

public class ManterFilho {
    
        static Connection conexao;
        Responsavel responsavel;
        
        public ManterFilho(Connection conexaoBD, Responsavel responsavel) {
            this.responsavel = responsavel;
	    this.conexao = conexaoBD;      
	}

    void telaPrincipalFilho() throws SQLException {
       
        int opcao = 10;
        Scanner entrada_opcao = new Scanner(System.in);
        do{
            
            System.out.println(" ________________________________________");
            System.out.println("|                                        |");
            System.out.println("|           ROTINA KIDS - FILHO          |");
            System.out.println("| Responsavel: "+responsavel.getNome_usuario());
            System.out.println("|________________________________________|");
            System.out.println("| 1- Cadastrar                           |");
            System.out.println("| 2- Alterar Dados                       |");
            System.out.println("| 3- Remover                             |");
            System.out.println("| 4- Listar Filhos Cadastrados           |");
            System.out.println("| 5- Verificar Saldo do Banco de Moedas  |");
            System.out.println("| 6- Voltar                              |");
            System.out.println("|________________________________________|");
            
            opcao = entrada_opcao.nextInt();
            
            if(opcao == 1){     
                 telaCadastroFilho();       
            }
            else if(opcao == 2){
                ResponsavelDao responsavelDao = new ResponsavelDao(conexao);
                ArrayList arrayFilhos  = new ArrayList();
                arrayFilhos = responsavelDao.buscarFilhosDoResponsavel(responsavel);
                
                if(arrayFilhos == null){
                    System.out.println("O responsavel nao possui filhos cadastrados!");
                }
                else{
                     
                    Filho filhoEscolhido = telaSelecionarFilho(arrayFilhos);

                    Scanner entrada_novoNome = new Scanner(System.in);
                    Scanner entrada_novoCpf = new Scanner(System.in);
                    Scanner entrada_novaIdade = new Scanner(System.in);
                    String novoNome=null, novoCpf=null;
                    int novaIdade=0;

                    System.out.println("Digite os novos dados:\nNome:");
                    novoNome = entrada_novoNome.nextLine();
                    System.out.println("CPF:");
                    novoCpf = entrada_novoCpf.nextLine();
                    System.out.println("Idade:");
                    novaIdade = entrada_novaIdade.nextInt();

                    Filho novosDadosFilho = new Filho(novoNome,novoCpf,responsavel.getPk_responsavel(),novaIdade);
                    novosDadosFilho.setCod_responsavel(responsavel.getPk_responsavel());
                    novosDadosFilho.setCpf(novoCpf);
                    novosDadosFilho.setIdade_filho(novaIdade);
                    novosDadosFilho.setNome_usuario(novoNome);

                    FilhoController novosDadosfilhoController = new FilhoController();

                    if(novosDadosfilhoController.validarDados(novosDadosFilho)){
                        FilhoDao filhoDao = new FilhoDao(conexao,responsavel);
                        if(filhoDao.AlterarDadosFilho(filhoEscolhido, novaIdade, novoNome, novoCpf)){
                            System.out.println("Dados alterados com sucesso!");
                        }
                        else{
                            System.out.println("Dados nao alterados!");
                        }
                    }
                    else{
                        System.out.println("Dados incorretos!!");
                    }

                }
            }
            else if(opcao == 3){
                ResponsavelDao responsavelDao = new ResponsavelDao(conexao);
                ArrayList arrayFilhos  = new ArrayList();
                arrayFilhos = responsavelDao.buscarFilhosDoResponsavel(responsavel);
                
                if(arrayFilhos == null){
                    System.out.println("O responsavel nao possui filhos cadastrados!");
                }
                else{
                     
                    Filho filhoEscolhido = telaSelecionarFilho(arrayFilhos);

                    System.out.println("Deseja realmente excluir!? (1-SIM/0-NAO): "+filhoEscolhido.getNome_usuario());
                    Scanner entrada_confirmacaoExclusao = new Scanner(System.in);
                    int opcaoExclusao;
                    opcaoExclusao = entrada_confirmacaoExclusao.nextInt();
                    
                    if(opcaoExclusao == 1){
                        FilhoDao filhoDao = new FilhoDao(conexao,responsavel);
                        if(filhoDao.excluirFilho(filhoEscolhido)){
                            System.out.println("Filho excluido com sucesso!");
                        }
                        else{
                            System.out.println("O filho nao foi excluido!");
                        }
                    }
                    else{
                        System.out.println("Exclusao cancelada!");
                    }
                }

            }
            else if(opcao == 4){

                ResponsavelDao responsavelDao = new ResponsavelDao(conexao);
                ArrayList<Filho> arrayFilhos  = new ArrayList();
                arrayFilhos = responsavelDao.buscarFilhosDoResponsavel(responsavel);
                
                if(arrayFilhos == null){
                    System.out.println("O responsavel nao possui filhos cadastrados!");
                }
                else{
                    for(int contador = 0;contador < arrayFilhos.size();contador++){
                        System.out.println(""+contador+" - "+arrayFilhos.get(contador).getNome_usuario()+"| CPF: "+arrayFilhos.get(contador).getCpf()+"| Idade: "+arrayFilhos.get(contador).getIdade_filho());                 
                    }
                }

            }
            else if(opcao == 5){
                ResponsavelDao responsavelDao = new ResponsavelDao(conexao);
                ArrayList<Filho> arrayFilhos  = new ArrayList();
                arrayFilhos = responsavelDao.buscarFilhosDoResponsavel(responsavel);
                
                if(arrayFilhos == null){
                    System.out.println("O responsavel nao possui filhos cadastrados!");
                }
                else{
                    BancoDeMoedasDao bancoDeMoedasDao = new BancoDeMoedasDao(conexao); 
                    for(int contador = 0;contador < arrayFilhos.size();contador++){
                        System.out.println(""+contador+" - "+arrayFilhos.get(contador).getNome_usuario()+"| Saldo: "+bancoDeMoedasDao.mostrarSaldo(arrayFilhos.get(contador))+"");                       

                    }

                    
                }

            }
            else if(opcao == 6){
                ManterResponsavel manterResponsavel = new ManterResponsavel(conexao);
                manterResponsavel.telaPrincipalResponsavel(responsavel);  
            }
            else{
                System.out.println("Digite uma opcao valida!");
            }

        }while(opcao!=10); 
    }
    
     public void telaCadastroFilho() throws SQLException{
               
         
        System.out.println(" ________________________________________");
        System.out.println("|                                        |");
        System.out.println("|  ROTINA KIDS - CADASTRO DE FILHO       |");
        System.out.println("| Responsavel: "+responsavel.getNome_usuario());
        System.out.println("|________________________________________|");
        
        Scanner entrada_nome = new Scanner(System.in);
        Scanner entrada_cpf = new Scanner(System.in);
        Scanner entrada_idade = new Scanner(System.in);
        Scanner entrada_opcao = new Scanner(System.in);
        
        String nome, cpf;
        int idade;
        
        
        System.out.println("Nome:");
        nome = entrada_nome.nextLine();
        System.out.println("Cpf:");
        cpf = entrada_cpf.nextLine();
        System.out.println("Idade:");
        idade = entrada_idade.nextInt();
        
                
        Filho filho = new Filho(nome,cpf,responsavel.getPk_responsavel(),idade);
        filho.setCod_responsavel(responsavel.getPk_responsavel());
        filho.setCpf(cpf);
        filho.setIdade_filho(idade);
        filho.setNome_usuario(nome);

        FilhoController filhoMock = new FilhoController();
        
        if(filhoMock.validarDados(filho)){
            FilhoDao filhoDao = new FilhoDao(conexao,responsavel);
                     
            if(filhoDao.validarDados(filho,responsavel) == false){ // não existe login cadastrado com o mesmo valor

                    System.out.println(filhoDao.inserirFilho(filho));
            }
            else{
                System.out.println("Dados Inválidos!");
            }
         }  
    }  
     
    public Filho telaSelecionarFilho(ArrayList<Filho> filhos) { 
  
        Scanner entrada_filhoEscolhido = new Scanner(System.in);
        int filhoEscolhido;
        
         
        System.out.println(" ________________________________________");
        System.out.println("|                                        |");
        System.out.println("|  ROTINA KIDS - SELECIONAR FILHO        |");
        System.out.println("| Responsavel: "+responsavel.getNome_usuario());
        System.out.println("|________________________________________|");
        
        for(int contador=0;contador<filhos.size();contador++){   
            System.out.println(""+contador+" - "+filhos.get(contador).getNome_usuario());;
        } 
        filhoEscolhido = entrada_filhoEscolhido.nextInt();
        
        Filho FilhoEscolhido = filhos.get(filhoEscolhido);
        FilhoEscolhido.setPk_filho(filhos.get(filhoEscolhido).getPk_filho());
        FilhoEscolhido.setCod_responsavel(filhos.get(filhoEscolhido).getCod_responsavel());
        FilhoEscolhido.setCpf(filhos.get(filhoEscolhido).getCpf());
        FilhoEscolhido.setIdade_filho(filhos.get(filhoEscolhido).getIdade_filho());
        FilhoEscolhido.setNome_usuario(filhos.get(filhoEscolhido).getNome_usuario());
     
       return FilhoEscolhido;
  
    }  

   
}

