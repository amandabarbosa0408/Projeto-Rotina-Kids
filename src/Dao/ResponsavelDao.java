package Dao;

import static Dao.LoginDao.conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Filho;
import model.Responsavel;

public class ResponsavelDao implements IResponsavel{

                                                                                                                                                                                                        
    static Connection conexao;
	
    public ResponsavelDao(Connection conexaoBD) {
        this.conexao = conexaoBD;
              
    }

    @Override
    public boolean validarDados(Responsavel responsavel) throws SQLException {	
        try {
            PreparedStatement pstmt = conexao.prepareStatement("select * from responsavel where login ='"+responsavel.getLogin()+"'");
	    ResultSet rs = pstmt.executeQuery();
			
            String login = null;
			
            while(rs.next()) {
                login = rs.getString("login");
            }
		
            if(login != null) {
                return true;
            }
            else {
                return false;
            }
	}catch(SQLException e) {
            System.out.println(e.getMessage());
	    return false;
	}
    }
    
    
    @Override
    public int buscarId(Responsavel responsavel) throws SQLException {	
       int id = 0;
        try {
            PreparedStatement pstmt = conexao.prepareStatement("select pk_usuario from usuario where cpf ='"+responsavel.getCpf()+"'");
	    ResultSet rs = pstmt.executeQuery();
                        
            while(rs.next())  
            {  
                id = rs.getInt("pk_usuario");   
            }

        }catch(SQLException e) {
            System.out.println("Erro = "+e.getMessage());
        }		
        return id;
    }

    @Override
    public String inserirResponsavel(Responsavel responsavel) throws SQLException {
        try{
            Statement statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             
            String sqlCadastraUsuario = "INSERT INTO USUARIO (nome_usuario, cpf) VALUES ('"+responsavel.getNome_usuario()+"','"+responsavel.getCpf()+"');";
            statement.executeUpdate(sqlCadastraUsuario);
            
            responsavel.setPk_responsavel(buscarId(responsavel)); 
            String sqlCadastraResponsavel = "INSERT INTO RESPONSAVEL (pk_responsavel,login, senha) VALUES ('"+responsavel.getPk_responsavel()+"','"+responsavel.getLogin()+"','"+responsavel.getSenha()+"');";        
            statement.executeUpdate(sqlCadastraResponsavel);
            } 
         
        catch(SQLException e) {
            System.out.println("Erro = "+e.getMessage());
        }
        return "Cadastro de Responsavel Realizado com Sucesso!";
    }    

    @Override
    public ArrayList<Filho> buscarFilhosDoResponsavel(Responsavel responsavel) throws SQLException {
    
        int contadorFilhos = 0,contador =0;
        int quantidadeDeFilhos = 0; 
 
        PreparedStatement pstmt = conexao.prepareStatement("select * from filho where cod_responsavel ='"+responsavel.getPk_responsavel()+"'");	
        ResultSet rs = pstmt.executeQuery();
       
        while(rs.next()){
            quantidadeDeFilhos++;       
        }
        if(quantidadeDeFilhos == 0){
            return null;
        }
        else{
            
            int cod_filho[] = new int[quantidadeDeFilhos];
            int cod_responsavel[] = new int[quantidadeDeFilhos];
            int idade[] = new int[quantidadeDeFilhos];

            rs = pstmt.executeQuery();
            while(rs.next())  
            {  
                cod_filho[contadorFilhos] = rs.getInt("pk_filho");
                cod_responsavel[contadorFilhos] = rs.getInt("cod_responsavel");
                idade[contadorFilhos] = rs.getInt("idade_filho"); 
                contadorFilhos++;
            }

            String nome[] = new String[quantidadeDeFilhos];
            String cpf[] = new String[quantidadeDeFilhos];

            int contadorParaUsuario = contadorFilhos,con=0;
            do{
                PreparedStatement pstmt2= conexao.prepareStatement("select * from usuario where pk_usuario ='"+cod_filho[con]+"'");
                ResultSet rs2 = pstmt2.executeQuery();

                while(rs2.next())  
                {                      
                    nome[contador] = rs2.getString("nome_usuario");
                    cpf[contador] = rs2.getString("cpf");   
                    contador++;
                }
                con++;
                contadorParaUsuario--;

            }while(contadorParaUsuario > 0);

            ArrayList ArrayFilho = new ArrayList();

            for(int cont=0;cont<contadorFilhos;cont++){

                Filho filho = new Filho(cod_filho[cont],nome[cont],cpf[cont],cod_responsavel[cont],idade[cont]);

                filho.setIdade_filho(idade[cont]);
                filho.setPk_filho(cod_filho[cont]);
                filho.setNome_usuario(nome[cont]);
                filho.setCpf(cpf[cont]);
                filho.setCod_responsavel(cod_responsavel[cont]);

                ArrayFilho.add(filho);    
                }
            return ArrayFilho;
        }
    }
}
