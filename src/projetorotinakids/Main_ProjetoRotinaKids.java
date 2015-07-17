package projetorotinakids;

import Controller.LoginController;
import Dao.ConexaoMySQLDao;
import Dao.LoginDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import model.Login;
import view.ManterLogin;
import view.ManterResponsavel;

public class Main_ProjetoRotinaKids {

    public static void main(String[] args) throws SQLException {
        
        
        ConexaoMySQLDao conexaoMySQLDao = new  ConexaoMySQLDao();
        Scanner entrada_opcaoMenuPrincial = new Scanner(System.in);
        int opcaoMenuPrincipal = 0;
        
       
        do{
            
            System.out.println(" ________________________________________");
            System.out.println("|                                        |");
            System.out.println("|           ROTINA KIDS - LOGIN          |");
            System.out.println("|________________________________________|");
            System.out.println("|  Digite uma das opcoes:                |");
            System.out.println("| 1- Fazer Login                         |");
            System.out.println("| 2- Cadastrar Responsavel               |");
            System.out.println("| 3- Sair                                |");
            System.out.println("|________________________________________|");

            opcaoMenuPrincipal = entrada_opcaoMenuPrincial.nextInt();

            if(opcaoMenuPrincipal == 1){                            
                ManterLogin Manterlogin = new ManterLogin(conexaoMySQLDao.getConexao());
                Manterlogin.telaLogin();
            }
            else if(opcaoMenuPrincipal == 2){
                ManterResponsavel ManterResponsavel = new ManterResponsavel(conexaoMySQLDao.getConexao());
                ManterResponsavel.telaCadastroResponsavel();
            }
            else if(opcaoMenuPrincipal == 3){
                System.out.println("Obrigado por acessar o sistema Rotina Kids");
                break;
            }
            else{
                System.out.println("Digite uma opcao valida!");
            }
        }while(opcaoMenuPrincipal != 3);
    }  
}
