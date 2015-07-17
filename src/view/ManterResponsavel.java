package view;

import Controller.LoginController;
import Controller.ResponsavelController;
import Dao.LoginDao;
import Dao.ResponsavelDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import model.Responsavel;
import projetorotinakids.Main_ProjetoRotinaKids;
import static view.ManterLogin.conexao;

public class ManterResponsavel{
    
    static Connection conexao;
    Responsavel responsavel;
    
    Scanner entrada_nome = new Scanner(System.in);
    Scanner entrada_cpf = new Scanner(System.in);
    Scanner entrada_login = new Scanner(System.in);
    Scanner entrada_senha = new Scanner(System.in);
    Scanner entrada_opcao = new Scanner(System.in);
    
    String nome, cpf, login, senha;
    int opcao;
    

    public ManterResponsavel(Connection conexaoBD, Responsavel responsavel) {
        this.conexao = conexaoBD;
        this.responsavel = responsavel;
    }
    public ManterResponsavel(Connection conexaoBD) {
        this.conexao = conexaoBD;
    }


    public void telaCadastroResponsavel() throws SQLException{
        
        System.out.println(" ________________________________________");
        System.out.println("|                                        |");
        System.out.println("|  ROTINA KIDS - CADASTRO DE RESPONSAVEL |");
        System.out.println("|________________________________________|");
        
        System.out.println("Nome:");
        nome = entrada_nome.nextLine();
        System.out.println("Cpf:");
        cpf = entrada_cpf.nextLine();
        System.out.println("Login:");
        login = entrada_login.nextLine();
        System.out.println("Senha:");
        senha = entrada_senha.nextLine();

        Responsavel responsavel = new Responsavel(nome,cpf,login,senha);
        responsavel.setCpf(cpf);
        responsavel.setLogin(login);
        responsavel.setSenha(senha);
        responsavel.setNome_usuario(nome);

        
        ResponsavelController responsavelMock = new ResponsavelController();
        
        
        if(responsavelMock.validarDados(responsavel)){
            ResponsavelDao responsavelDao = new ResponsavelDao(conexao);
           
            if(responsavelDao.validarDados(responsavel)){
                System.out.println("Responsavel ("+responsavel.getNome_usuario()+")  cadastrado com sucesso!");
            }
            else{
                System.out.println("Login Inválido!");
            }
        } 
        else{
            System.out.println("Nao foram inseridos todos os campos!\nResponsavel nao cadastrado!");
        }
    }
    
    public void telaPrincipalResponsavel(Responsavel responsavel) throws SQLException{
       
        int opcao = 10;
        Scanner entrada_opcao = new Scanner(System.in);
        do{
            
            System.out.println(" ________________________________________");
            System.out.println("|                                        |");
            System.out.println("|           ROTINA KIDS - HOME           |");
            System.out.println("| Responsavel: "+responsavel.getNome_usuario());
            System.out.println("|________________________________________|");
            System.out.println("| 1- Filho                               |");
            System.out.println("| 2- Atividades                          |");
            System.out.println("| 3- Relatorio de Atividades Atribuidas  |");
            System.out.println("| 4- Sair                                |");
            System.out.println("|________________________________________|");
            
            opcao = entrada_opcao.nextInt();

            if(opcao == 1){
                ManterFilho ManterFilho = new ManterFilho(conexao,responsavel);    
                ManterFilho.telaPrincipalFilho();
                break;
        
            }
            else if(opcao == 2){
                ManterAtividade manterAtividade = new ManterAtividade(conexao,responsavel);
                manterAtividade.telaPrincipalAtividade();
            }
            else if(opcao == 3){
                ManterAtividadeAtribuida manterAtividadeAtribuida = new ManterAtividadeAtribuida(conexao,responsavel);
                manterAtividadeAtribuida.telaRelatorioAtividadesAtribuidas(responsavel);
            }
            else if(opcao == 4){
                String[] args = null;
                Main_ProjetoRotinaKids.main(args);

            }
            else{

            }
        
        
        }while(opcao!=0);
           
    }

}
    