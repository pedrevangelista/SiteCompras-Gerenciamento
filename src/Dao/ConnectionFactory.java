/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import model.Produtos;

public class ConnectionFactory {

    
    Produtos produtos;

    public static Connection CriaConexao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String urlConexao = "jdbc:mysql://localhost:3306/SUTILEZA";
            String usuario = "root";
            String senha = "";
            Connection conexao
                    = DriverManager.getConnection(urlConexao, usuario, senha);
            System.out.println("Conectado com sucesso");
            return conexao;

        } catch (Exception erro) {
            System.out.println("Problema ao tentar conectar com o banco de dados: " + erro);
        }
        return null;

    }
}
