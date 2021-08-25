/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tccrazy;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Lucas
 */
public class InicioController implements Initializable {

    @FXML
    private Button btnProdutos;
    @FXML
    private Button btnVendas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }    

    @FXML
    private void btnProdutosClick(ActionEvent event) {
        TCCrazy.trocaTela("principal");
    }

    @FXML
    private void btnVendasClick(ActionEvent event) {
        TCCrazy.trocaTela("vendas");
    }
    
}
