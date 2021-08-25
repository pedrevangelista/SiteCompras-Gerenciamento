package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Produtos;


public class ProdutosDao {
    
     public static void InserirProdutosBancoDados (Produtos produto) throws SQLException{
      try{
        Connection conexao = ConnectionFactory.CriaConexao();
        PreparedStatement comando = conexao.prepareStatement("INSERT INTO `produtos`(`Nome`,`Sexo`,`Descricao`) VALUES (?,?,?)");
        comando.setString(1, produto.getNome());
        comando.setString(2, produto.getSexo());
        comando.setString(3, produto.getDescricao());
        comando.execute();
        comando.close();
     
    }
      catch (SQLException erro){
          System.out.println("Erro ao conectar com o banco de dados: " + erro);
      }
    }
    
   public static void ApagarItemBancoDados(int id) {
        try {
            Connection conexao = ConnectionFactory.CriaConexao();
            PreparedStatement comando
                    = conexao.prepareStatement("delete from PRODUTOS where id=?");
            comando.setInt(1, id);
            comando.execute();
            conexao.close();

        } catch (SQLException erro) {
            System.out.println("Erro ao conectar com o banco de dados: " + erro);
        }
    }
   public static void AlterarItemBancoDados (Produtos produto) throws SQLException{
       Connection conexao = ConnectionFactory.CriaConexao();
       PreparedStatement comando = conexao.prepareStatement("UPDATE `produtos` SET `id`= ?,`Nome`= ?,`Sexo`= ?,`Descricao`= ? WHERE id = '" + produto.getId()+"'");
       comando.setInt(1, produto.getId());
       comando.setString(2, produto.getNome());
       comando.setString(3, produto.getSexo());
       comando.setString(4, produto.getDescricao());
       comando.execute();
       comando.close();
   }
   
}
