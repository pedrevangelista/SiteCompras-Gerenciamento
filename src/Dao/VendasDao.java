package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VendasDao {
    
    public static void EnviaVenda(int idVenda) throws SQLException{
        Connection conexao = ConnectionFactory.CriaConexao();
        PreparedStatement comando = conexao.prepareStatement("UPDATE `vendas` SET `enviado`= 's' WHERE `id_venda` = "+idVenda);
        comando.execute();
        comando.close();
    }
    
}
