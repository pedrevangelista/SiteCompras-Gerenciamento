
package tccrazy;

import Dao.ConnectionFactory;
import Dao.VendasDao;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Venda;


public class VendasController implements Initializable {
    
    private List<Venda> listVenda = new ArrayList<>();
    private ObservableList<Venda> observableListVenda;
    @FXML
    private Button btnVoltar;
    @FXML
    private TableView<Venda> tblVendas;
    @FXML
    private TableColumn<Integer, Venda> colId_venda;
    @FXML
    private TableColumn<Integer, Venda> colId_grade;
    @FXML
    private TableColumn<String, Venda> colCpf;
    @FXML
    private TableColumn<Date, Venda> colDataCompra;
    @FXML
    private TableColumn<Float, Venda> colPreco;
    @FXML
    private Button btnEnviado;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId_venda.setCellValueFactory(new PropertyValueFactory<>("idVenda"));
        colId_grade.setCellValueFactory(new PropertyValueFactory<>("idGrade"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colDataCompra.setCellValueFactory(new PropertyValueFactory<>("dataCompra"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        
        carregaTabela("SELECT * FROM vendas WHERE enviado like 'n'");
        
        btnEnviado.setVisible(false);
    } 
    
    private void carregaTabela(String sql){
        try {
            listVenda.clear();
            observableListVenda.clear();
            tblVendas.getItems().clear(); 
        } catch (Exception erro) {
            System.out.println("erro ao carregar tabela: " + erro);
        }
        try {
            Connection conexao = ConnectionFactory.CriaConexao();
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.execute();
            ResultSet rs = comando.executeQuery();
            rs.first();
            do {
                int idVenda = rs.getInt("id_venda");
                int idGrade = rs.getInt("id_grade");
                String cpf = rs.getString("cpf");
                Date dataCompra = rs.getDate("data_compra");
                float preco = rs.getFloat("preco");
                Venda venda = new Venda(idVenda, idGrade, cpf, dataCompra, preco);
                listVenda.add(venda);
            } while (rs.next());
            conexao.close();
            observableListVenda = FXCollections.observableArrayList(listVenda);
            tblVendas.setItems(observableListVenda);
            } catch (Exception erro) {
            System.out.println("Problema ao tentar conectar com o banco de dados: " + erro);
        }
    }

    @FXML
    private void btnVoltarClick(ActionEvent event) {
        TCCrazy.trocaTela("inicio");
    }

    @FXML
    private void tblVendasClick(MouseEvent event) {
        int i = tblVendas.getSelectionModel().getSelectedIndex();
                
        if (i>=0){
            btnEnviado.setVisible(true);
        }
        else{
            
            btnEnviado.setVisible(false);
            Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
            dialogoErro.setTitle("Diálogo de Erro");
            dialogoErro.setHeaderText("Seleção de Ítens...");
            dialogoErro.setContentText("Atenção: Não há items selecionados.!");
            dialogoErro.showAndWait();
        }
    }

    @FXML
    private void btnEnviadoClick(ActionEvent event) throws SQLException {
         int i = tblVendas.getSelectionModel().getSelectedIndex();
         int idVenda = tblVendas.getSelectionModel().getSelectedItem().getIdVenda();
        if (i>=0){
            VendasDao.EnviaVenda(idVenda);
            carregaTabela("SELECT * FROM vendas WHERE enviado like 'n'");
        }
        else{
            
            btnEnviado.setVisible(false);
            Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
            dialogoErro.setTitle("Diálogo de Erro");
            dialogoErro.setHeaderText("Seleção de Ítens...");
            dialogoErro.setContentText("Atenção: Não há items selecionados.!");
            dialogoErro.showAndWait();
        }  
    }
    
}
