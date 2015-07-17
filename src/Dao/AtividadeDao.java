package Dao;

import static Dao.ResponsavelDao.conexao;
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
import model.Responsavel;

public class AtividadeDao implements IAtividade{
                                                                                                                                                                                                            
    static Connection conexao;
	
    public AtividadeDao(Connection conexaoBD) {
        this.conexao = conexaoBD;    
    }
    
    @Override
    public boolean validarDados(Atividade atividade) throws SQLException {	
        try {
			PreparedStatement pstmt = conexao.prepareStatement("select * from atividade where nome_atividade ='"+atividade.getNome_atividade()+"' and grupoEtario_atividade ='"+atividade.getGrupoEtario_atividade()+"'");
                        ResultSet rs = pstmt.executeQuery();
			String nome = null;
                        int grupo_etario;
			
			while(rs.next()) {
                            nome = rs.getString("nome_atividade");
                            grupo_etario = rs.getInt("grupoEtario_atividade");
			}
			
			if(nome != null) {
				return true;
			}
			else {
				return false;
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
    }
    
    @Override 
    public int buscarIdAtividade(Atividade atividade) throws SQLException {	
       int id = 0;
        try {
		PreparedStatement pstmt = conexao.prepareStatement("select pk_atividade from atividade where nome_atividade ='"+atividade.getNome_atividade()+"'");
			
		ResultSet rs = pstmt.executeQuery();
                        
                while(rs.next())  
                {  
                   id = rs.getInt("pk_atividade");   
                }

		} catch(SQLException e) {
            System.out.println("Erro = "+e.getMessage());
      
		}		
        return id;
    }
    
    @Override
    public String inserirAtividade(Atividade atividade) throws SQLException {
         try{
            Statement statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             
            String sqlCadastraUsuario = "INSERT INTO ATIVIDADE (nome_atividade, descricao_atividade,grupoEtario_atividade,qtdMoedas_atividade) VALUES ('"+atividade.getNome_atividade()+"','"+atividade.getDescricao_atividade()+"','"+atividade.getGrupoEtario_atividade()+"','"+atividade.getQtdMoedas_atividade()+"');";
            statement.executeUpdate(sqlCadastraUsuario);
            
           
            atividade.setPk_atividade(buscarIdAtividade(atividade));

            return "Cadastro de Atividade Realizado com Sucesso!";
        }
        catch(SQLException e){
            return e.toString();
        }     
    }
    
    @Override
    public ArrayList<Atividade> buscarListaDeAtividadesParaFilho(Filho filho){
        int idadeDoFilho = filho.getIdade_filho(), grupoEtario;
        ArrayList<Atividade> listaAtividades = new ArrayList();
        
        if(idadeDoFilho <= 3){
             grupoEtario = 0;       
        }
        else if(idadeDoFilho <= 5){
            grupoEtario = 1; 
        }
        else if(idadeDoFilho <= 8){
            grupoEtario = 2; 
        }
        else if(idadeDoFilho <= 11){
            grupoEtario = 3; 
        }
        else{
            grupoEtario = 4; 
        }
   
        String nome = null, descricao = null;
        int qtdMoedas=0, pk_atividade = 0;
        
        try {
            PreparedStatement pstmt = conexao.prepareStatement("select * from atividade where grupoEtario_atividade ='"+grupoEtario+"'");
            ResultSet rs = pstmt.executeQuery();
            
			
            while(rs.next()) {

                Atividade atividade = new Atividade(nome,descricao,grupoEtario,qtdMoedas);
                atividade.setDescricao_atividade(rs.getString("descricao_atividade"));
                atividade.setGrupoEtario_atividade(rs.getInt("grupoEtario_atividade"));
                atividade.setQtdMoedas_atividade(rs.getInt("qtdMoedas_atividade"));
                atividade.setPk_atividade(rs.getInt("pk_atividade"));
                atividade.setNome_atividade(rs.getString("nome_atividade"));
                listaAtividades.add(atividade);
            }
   
	}catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    return listaAtividades;
    }
    
    @Override
    public ArrayList<Atividade> buscarListaDeAtividadesCadastradas()throws SQLException{
    ArrayList<Atividade> arrayAtividades = new ArrayList();
      try {
               PreparedStatement pstmt = conexao.prepareStatement("select * from atividade ORDER BY grupoEtario_atividade");
               ResultSet rs = pstmt.executeQuery();
               
               String nome = null, descricao = null;
               int qtdMoedas=0, pk_atividade = 0, grupoEtario=10;


               while(rs.next()) {

                   Atividade atividade = new Atividade(nome,descricao,grupoEtario,qtdMoedas);
                   atividade.setDescricao_atividade(rs.getString("descricao_atividade"));
                   atividade.setGrupoEtario_atividade(rs.getInt("grupoEtario_atividade"));
                   atividade.setQtdMoedas_atividade(rs.getInt("qtdMoedas_atividade"));
                   atividade.setPk_atividade(rs.getInt("pk_atividade"));
                   atividade.setNome_atividade(rs.getString("nome_atividade"));
                   arrayAtividades.add(atividade);
               }

           }catch(SQLException e) {
               System.out.println(e.getMessage());
           }
    
    return arrayAtividades;
    
    }
    @Override
     public  boolean excluirAtividade(Atividade atividade) throws SQLException{
     
        try {
            Statement statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            String sql = "DELETE FROM atividade_atribuida WHERE cod_atividade = '"+atividade.getPk_atividade()+"'";
            statement.executeUpdate(sql);
            
            sql = "DELETE FROM atividade WHERE pk_atividade = '"+atividade.getPk_atividade()+"'";
            statement.executeUpdate(sql);
            
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
   
}

