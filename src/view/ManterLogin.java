package view;

import Controller.LoginController;
import Dao.ConexaoMySQLDao;
import Dao.LoginDao;
import Dao.ResponsavelDao;
import Dao.UsuarioDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import model.Login;
import model.Responsavel;
import model.Usuario;

public class ManterLogin {
    static Connection conexao;
    
    public ManterLogin(Connection conexaoBD) {
		this.conexao = conexaoBD;
              
	}
        
    public void telaLogin() throws SQLException{
        
        Scanner entrada_usuario = new Scanner(System.in);
        Scanner entrada_senha = new Scanner(System.in);
        String usuario, senha;

            System.out.println(" ________________________________________");
            System.out.println("|                                        |");
            System.out.println("|           ROTINA KIDS - LOGIN          |");
            System.out.println("|________________________________________|");
            
            System.out.println("  Usuario:");
            usuario = entrada_usuario.nextLine();
            System.out.println("  Senha:");
            senha = entrada_senha.nextLine();
            
            Login login = new model.Login(usuario,senha);
            login.setLogin(usuario);
            login.setSenha(senha);
  
            LoginController loginController = new LoginController();

            if(loginController.validar(login)){
                LoginDao loginDao;
                loginDao = new LoginDao(conexao);
                            
                if(loginDao.validar(login)){
                    System.out.println("Autenticação realizada com sucesso!");
                    
                    UsuarioDao usuarioDao = new UsuarioDao(conexao);
                    Usuario usuarioLogado = usuarioDao.buscarDados(login);

                    Responsavel responsavel = new Responsavel(usuarioLogado,login);
                    
                    ManterResponsavel ManterResponsavel = new ManterResponsavel(conexao,responsavel);
                    ManterResponsavel.telaPrincipalResponsavel(responsavel);

                }
                else{
                    System.out.println("Usuario e/ou Senha Incorretos!");
                }   
            }
            else{
                System.out.println("Dados nao inseridos!");
            }  
    
    }
    
}
