package Dao;

import static Dao.AtividadeDao.conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Filho;

public class BancoDeMoedasDao {
    static Connection conexao;
	
    public BancoDeMoedasDao(Connection conexaoBD) {
        this.conexao = conexaoBD;    
    }

    public int  mostrarSaldo(Filho filho) {
      
        int saldo = 0;
        try {
		PreparedStatement pstmt = conexao.prepareStatement("select saldo from banco_moedas where cod_filho ='"+filho.getPk_filho()+"'");
			
		ResultSet rs = pstmt.executeQuery();
                        
                while(rs.next())  
                {  
                   saldo = rs.getInt("saldo");   
                }

		} catch(SQLException e) {
                    System.out.println("Erro = "+e.getMessage());
		}		
        return saldo;
    }
    
    
    
}
