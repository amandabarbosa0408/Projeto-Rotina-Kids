package Dao;

import static Dao.AtividadeDao.conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.Atividade;
import model.Filho;

public class AtividadeAtribuidaDao implements IAtividadeAtribuida{
    
    static Connection conexao;
	
    public AtividadeAtribuidaDao(Connection conexaoBD) {
        this.conexao = conexaoBD;    
    }

    
    @Override
     public boolean cadastrarAtividadeAtribuida(int status,Filho filho,Atividade atividade)throws SQLException{
  
      
        String dataAtividade = retornaDataDoDia();
        try{
            Statement statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             
            String sqlCadastraAtividade = "INSERT INTO ATIVIDADE_ATRIBUIDA (data_atividade_atividadeAtribuida, cod_filho,status_atividade_atividadeAtribuida,cod_atividade,qntdMoedas_atividade) VALUES ('"+dataAtividade+"','"+filho.getPk_filho()+"','"+status+"','"+atividade.getPk_atividade()+"','"+atividade.getQtdMoedas_atividade()+"');";
            statement.executeUpdate(sqlCadastraAtividade);

            int saldo = 0; 
            PreparedStatement pstmt = conexao.prepareStatement("select * from banco_moedas where cod_filho ='"+filho.getPk_filho()+"'");	
            ResultSet rs = pstmt.executeQuery();
            
         
            while(rs.next())  
            {  
               saldo = rs.getInt("saldo");        
            }
           
            int novoSaldo = (int) (saldo + atividade.getQtdMoedas_atividade());                  
                    
            String sql = "UPDATE banco_moedas SET saldo = '" +novoSaldo+ "' WHERE cod_filho = '"+filho.getPk_filho()+"'";
            statement.executeUpdate(sql);

        } 
         
        catch(SQLException e) {
            return false;
        }
        return true;
    } 
    
    public String retornaDataDoDia(){
        
        Date hoje = new Date(); 
        SimpleDateFormat df; 
        df = new SimpleDateFormat("dd/MM/yyyy"); 
        String data = df.format(hoje);   
        return data;
    }
    
     public void relatorioPorFilho(ArrayList<Filho> filhos) throws SQLException{
     

        for(int contador=0;contador<filhos.size();contador++){
            PreparedStatement pstmt = conexao.prepareStatement("select * from atividade_atribuida where cod_filho ='"+filhos.get(contador).getPk_filho()+"'");	
            ResultSet rs = pstmt.executeQuery();
            
            String data = null;
            int status=0, qtdMoedas=0, cod_atividade = 0;
            
            while(rs.next())  
            {  
                data = rs.getString("data_atividade_atividadeAtribuida");
                status = rs.getInt("status_atividade_atividadeAtribuida");
                qtdMoedas = rs.getInt("qntdMoedas_atividade"); 
                cod_atividade = rs.getInt("cod_atividade");       
            }
            if(data == null){
                System.out.println("Filho:"+filhos.get(contador).getNome_usuario());
                        
                        
                System.out.println("Não existem atividades realizadas por: "+filhos.get(contador).getNome_usuario());           
            }
            else{
                 pstmt = conexao.prepareStatement("select * from atividade where pk_atividade ='"+cod_atividade+"'");
                rs = pstmt.executeQuery();
                String nomeAtividade= null;

                while(rs.next())  
                {  
                    nomeAtividade = rs.getString("nome_atividade");
                }
                String statusAtividade = null;
                if(status == 1){
                    statusAtividade = "Concluida";
                }
                else{
                    statusAtividade = "Nao Concluida";
                }

                System.out.println("Filho:"+filhos.get(contador).getNome_usuario());
                System.out.println("Data: "+data+"| Atividade: "+nomeAtividade+"| Status: "+statusAtividade+"| Quantidade de Moedas:"+qtdMoedas);
            } 
        }   
    }   
}