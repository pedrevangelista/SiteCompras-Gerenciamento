package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Grade;

public class GradeDao {
    public static void InserirGradeBancoDados (Grade grade) throws SQLException{
      try{
        Connection conexao = ConnectionFactory.CriaConexao();
        PreparedStatement comando = conexao.prepareStatement("INSERT INTO `grade`(`id`,`cor`,`tamanho`,`preco`,`quantidade`, `promocao`) VALUES (?,?,?,?,?,?)");
        comando.setInt(1,grade.getId());
        comando.setString(2, grade.getCor());
        comando.setString(3, grade.getTamanho());
        comando.setFloat(4, grade.getPreco());
        comando.setInt(5, grade.getQnt());
        comando.setFloat(6, grade.getPromo());
        comando.execute();
        comando.close();
      }
      
      catch (SQLException erro){
          System.out.println("Erro ao conectar com o banco de dados: " + erro);
      }
     
    }
    
    public static void ApagarGradeBancoDados(int id_grade) {
        try {
            Connection conexao = ConnectionFactory.CriaConexao();
            PreparedStatement comando
                    = conexao.prepareStatement("delete from grade where id_grade=?");
            comando.setInt(1, id_grade);
            comando.execute();
            conexao.close();

        } catch (SQLException erro) {
            System.out.println("Erro ao conectar com o banco de dados: " + erro);
        }
    }
    
   public static void AlterarItemBancoDados (Grade grade) throws SQLException{
       Connection conexao = ConnectionFactory.CriaConexao();
       PreparedStatement comando = conexao.prepareStatement("UPDATE `grade` SET `Cor`= ?,`Tamanho`= ?,`Preco`= ?,`Quantidade`= ?, `Promocao`=? WHERE id_grade = " + grade.getId_grade());
       comando.setString(1, grade.getCor());
       comando.setString(2, grade.getTamanho());
       comando.setFloat(3, grade.getPreco());
       comando.setInt(4, grade.getQnt());
       comando.setFloat(5, grade.getPromo());
       comando.execute();
       comando.close();
   }
  public static String proximaId_grade() throws SQLException{
       String id = "1";
            Connection conexao = ConnectionFactory.CriaConexao();
            PreparedStatement comando = conexao.prepareStatement("select * from grade order by id_grade desc");
            comando.execute();
            ResultSet rs = comando.executeQuery();
            
            if(rs.first()){
            id = String.valueOf(rs.getInt("id_grade"));
          }
            
       return id;    
    } 
}
