/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tccrazy;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author aluno
 */
public class TCCrazy extends Application {
    
    private static Stage stage;
    private static Scene cenaPrincipal;
    private static Scene cenaGrade;
    private static Scene cenaInicio;
    private static Scene cenaVendas;
    
    @Override
    public void start(Stage primaryStage) throws IOException {

        stage = primaryStage;
        
        Parent raiz;
        raiz = FXMLLoader.load(getClass().getResource("/view/Inicio.fxml"));
        cenaInicio = new Scene(raiz);

        Parent fxmlCenaPrincipal;
        fxmlCenaPrincipal = FXMLLoader.load(getClass().getResource("/view/Principal.fxml"));
        cenaPrincipal = new Scene(fxmlCenaPrincipal);
        
        
        Parent fxmlCenaGrade;
        fxmlCenaGrade = FXMLLoader.load(getClass().getResource("/view/Grade.fxml"));
        cenaGrade = new Scene(fxmlCenaGrade);
        
        Parent fxmlCenaVendas;
        fxmlCenaVendas = FXMLLoader.load(getClass().getResource("/view/Vendas.fxml"));
        cenaVendas = new Scene(fxmlCenaVendas);

        primaryStage.setScene(cenaInicio);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }
    
    public static void trocaTela(String tela){

        switch (tela){
            case "inicio":
                stage.setScene(cenaInicio);
                stage.centerOnScreen();
                break;
            case "principal":
                stage.setScene(cenaPrincipal);
                stage.centerOnScreen();
                break;
            case "grade":
                stage.setScene(cenaGrade);
                stage.centerOnScreen();
                break;
            case "vendas":
                stage.setScene(cenaVendas);
                stage.centerOnScreen();
        }
    
    }
    
}
