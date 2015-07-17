package Dao;

import static Dao.ResponsavelDao.conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Filho;
import model.Login;
import model.Responsavel;
import model.Usuario;


public class UsuarioDao implements IUsuario{
    
        
    static Connection conexao;
	
    public UsuarioDao(Connection conexaoBD) {
        this.conexao = conexaoBD;           
    }

    public Usuario buscarDados(Login login) throws SQLException {	
        Usuario usuario = null;
        String nome = null,cpf = null;
        int id = 0,id_responsavel = 0;
        try {
            
		PreparedStatement pstmt = conexao.prepareStatement("select * from responsavel where login ='"+login.getLogin()+"'");	
		ResultSet rs = pstmt.executeQuery();
                    
                while(rs.next())  
                {  
                    id = rs.getInt("pk_responsavel"); 
                }
           
                pstmt = conexao.prepareStatement("select * from usuario where pk_usuario ='"+id+"'");
		rs = pstmt.executeQuery();
                        
                while(rs.next())  
                {  
                   id_responsavel = rs.getInt("pk_usuario"); 
                   nome = rs.getString("nome_usuario"); 
                   cpf = rs.getString("cpf"); 

                }
                usuario = new Usuario(id_responsavel,nome,cpf);
		}catch(SQLException e) {
                    System.out.println("Erro = "+e.getMessage());
		}		
        return usuario;
    }   
    
    
     public Filho buscarDadosFilho(Filho filho) throws SQLException {	
        Filho filhoComDados = null;
        String nome = null,cpf = null;
        int id = 0,id_filho = 0;
       
        try {            
            PreparedStatement pstmt = conexao.prepareStatement("select * from usuario where pk_usuario ='"+filho.getPk_filho()+"'");	
            ResultSet rs = pstmt.executeQuery();
                 
            while(rs.next())  
            {  
                id_filho = rs.getInt("pk_usuario"); 
                nome = rs.getString("nome_usuario"); 
                cpf = rs.getString("cpf"); 
            }
            filhoComDados = new Filho(id_filho,nome,cpf,filho.getCod_responsavel(),filho.getIdade_filho());

	}catch(SQLException e) {
            System.out.println("Erro = "+e.getMessage());
        }		
        return filhoComDados;
    }   
}
