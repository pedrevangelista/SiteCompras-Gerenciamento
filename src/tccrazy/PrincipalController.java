package tccrazy;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.util.ResourceBundle;
import javafx .collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import Dao.ConnectionFactory;
import Dao.ProdutosDao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Produtos;

/**
 * FXML Controller class
 *
 * @author aluno
 */
public class PrincipalController implements Initializable {

    static final Runtime run = Runtime.getRuntime();
    static Process pro;
    static BufferedReader read;
    
    private List<Produtos> listProdutos = new ArrayList<>();
    private ObservableList<Produtos> observableListProdutos;     
    @FXML
    private TableView<Produtos> tblProdutos;
    @FXML
    private TableColumn<String, Produtos> colCod;
    @FXML
    private TableColumn<String, Produtos> colNome;
    @FXML
    private TableColumn<String, Produtos> colDesc;
    private TextField edtTamanho;
    private TextField edtQnt;
    @FXML
    private TextField edtNome;
    private TextField edtCor;
    @FXML
    private TextArea edtDescricao;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnDeletar;
    @FXML
    private Button btnLimpar;
    @FXML
    private ComboBox cbPesquisa;
    @FXML
    private TextField edtPesquisa;
    private ImageView imgView;
    private Button btnAlterarImagem;
    @FXML
    private TableColumn<String, Produtos> colSexo;
    private TextField edtPreco;
    @FXML
    private ComboBox cbSexo;
    @FXML
    private Button btnGrade;
    @FXML
    private Button btnVoltar;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
            colCod.setCellValueFactory(new PropertyValueFactory<>("Id"));
            colNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
            colSexo.setCellValueFactory(new PropertyValueFactory<>("Sexo"));
            colDesc.setCellValueFactory(new PropertyValueFactory<>("Descricao"));
            
            carregaTabela("select * from PRODUTOS");
        
        try {
            deletaImagemInicio();
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            btnAlterar.setVisible(false);
            btnDeletar.setVisible(false);
            
            
            cbPesquisa.getItems().add("Código");
            cbPesquisa.getItems().add("Nome");
            cbPesquisa.getItems().add("Cor");
            cbPesquisa.getItems().add("Tamanho");
            
            cbSexo.getItems().add("Masculino");
            cbSexo.getItems().add("Feminino");
            cbSexo.getItems().add("Unissex");
            
     
    }    
    private void carregaTabela(String comandoSql){
    
        try {
            listProdutos.clear();
            observableListProdutos.clear();
            tblProdutos.getItems().clear(); 
        } catch (Exception erro) {
            System.out.println("erro ao carregar tabela: " + erro);
        }
        try {
            Connection conexao = ConnectionFactory.CriaConexao();
            PreparedStatement comando = conexao.prepareStatement(comandoSql);
            comando.execute();
            ResultSet rs = comando.executeQuery();
            rs.first();
            do {
                int id = rs.getInt("Id");
                String nome = rs.getString("Nome");
                 String descricao = rs.getString("Descricao");
                 String sexo = rs.getString("Sexo");
                 Produtos produtos = new Produtos(id, nome,sexo, descricao);
                listProdutos.add(produtos);
            } while (rs.next());
            conexao.close();
            observableListProdutos = FXCollections.observableArrayList(listProdutos);
            tblProdutos.setItems(observableListProdutos);
            tblProdutos.getSelectionModel().select(0);
            
        } catch (Exception erro) {
            System.out.println("Problema ao tentar conectar com o banco de dados: " + erro);
        }
    }

    @FXML
    private void btnCadastrarClick(ActionEvent event) throws SQLException {
        
        Produtos produto = new Produtos(edtNome.getText(), voltaSexo(), edtDescricao.getText());
        ProdutosDao.InserirProdutosBancoDados(produto);
        carregaTabela("select * from PRODUTOS");
        
    }
        
   

    @FXML
    private void tblProdutosMouseClick(MouseEvent event) throws FileNotFoundException {
        int i = tblProdutos.getSelectionModel().getSelectedIndex();
        Produtos produto = tblProdutos.getSelectionModel().getSelectedItem();
        
        if (i>=0){
          btnDeletar.setVisible(true);
          btnAlterar.setVisible(true);
          
          edtNome.setText(produto.getNome());
          edtDescricao.setText(produto.getDescricao());
          if (produto.getSexo()== "F")
              cbSexo.setValue("Feminino");
          if (produto.getSexo()== "M")
              cbSexo.setValue("Masculino");
          if (produto.getSexo()== "U")
              cbSexo.setValue("Unissex");   

          

        }
        else{
          btnAlterar.setVisible(false);
          btnDeletar.setVisible(false);
          Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
            dialogoErro.setTitle("Diálogo de Erro");
            dialogoErro.setHeaderText("Seleção de Ítens...");
            dialogoErro.setContentText("Atenção: Não há items selecionados.!");
            dialogoErro.showAndWait();
        }
    }

    @FXML
    private void btnAlterarClick(ActionEvent event) throws SQLException {
        int i = tblProdutos.getSelectionModel().getSelectedIndex();
        int id = tblProdutos.getSelectionModel().getSelectedItem().getId();
        
        if(i>=0){
            Produtos produto = new Produtos(id,edtNome.getText(), voltaSexo(), edtDescricao.getText());
            ProdutosDao.AlterarItemBancoDados(produto);
            carregaTabela("select * from PRODUTOS");
            tblProdutos.getSelectionModel().selectFirst();
        }
        else{
          Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
            dialogoErro.setTitle("Diálogo de Erro");
            dialogoErro.setHeaderText("Seleção de Ítens...");
            dialogoErro.setContentText("Atenção: Não há items selecionados.!");
            dialogoErro.showAndWait();
        }
    }

    

    @FXML
    private void btnDeletarClick(ActionEvent event) throws FileNotFoundException, IOException {
        int i = tblProdutos.getSelectionModel().getSelectedIndex();
        Produtos produto = tblProdutos.getSelectionModel().getSelectedItem();
        if (i >= 0) {
            listProdutos.remove(i);
            tblProdutos.setItems(FXCollections.observableArrayList(listProdutos));
            tblProdutos.getSelectionModel().selectFirst();
            ProdutosDao.ApagarItemBancoDados(produto.getId());
                   
            
        } else {
            Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
            dialogoErro.setTitle("Diálogo de Erro");
            dialogoErro.setHeaderText("Remoção de Ítens...");
            dialogoErro.setContentText("Atenção: Não há items para serem removidos.!");
            dialogoErro.showAndWait();
        }
    }
    
    
    

    @FXML
    private void btnLimparClick(ActionEvent event) {
        edtNome.clear();
        edtDescricao.clear();
        cbSexo.setValue("Sexo");
    }

    @FXML
    private void edtPesquisaKeyReleased(KeyEvent event) {
        if (cbPesquisa.getValue() == "Código"){
            String sql = "select * from PRODUTOS where id like '%" + edtPesquisa.getText() + "%' order by id";
            listProdutos.clear();
            observableListProdutos.clear();
            tblProdutos.getItems().clear();
            tblProdutos.refresh();
            carregaTabela(sql);           
        }
        else if (cbPesquisa.getValue() == "Nome"){
            String sql = "select * from PRODUTOS where nome like '%" + edtPesquisa.getText() + "%' order by id";
            listProdutos.clear();
            observableListProdutos.clear();
            tblProdutos.getItems().clear();
            tblProdutos.refresh();
            carregaTabela(sql);
        }
        else edtPesquisa.clear();
                 
    }
    
    private void deletaImagemInicio() throws SQLException{
            
          Produtos produto = new Produtos();
          Connection conexao = ConnectionFactory.CriaConexao();
          PreparedStatement comando = conexao.prepareStatement("select * from PRODUTOS order by id desc");
          comando.execute();
          ResultSet rs = comando.executeQuery();
            
            
          if(rs.first()){
            int a = rs.getInt("id");
            comando.close();
            conexao.close();
            
            Connection conexao2 = ConnectionFactory.CriaConexao();
            PreparedStatement c = conexao2.prepareStatement("select * from PRODUTOS");
            c.execute();
            ResultSet rs2 = c.executeQuery();
            rs2.first();
            
            for (int i=1;i<=a;i++){
                File file = new File("c://Imagens//" + String.valueOf(i) + ".png");
               if (file.exists()){
                int id2 = rs2.getInt("id");
                  if (id2 != i){
                     file.delete();
                  }
                  else{
                     rs2.next();
                  }
               }    
            }
            c.close();
            conexao2.close();}
          
          else{
             for (int i=1; i<100000;i++){
                File file = new File("c://Imagens//" + String.valueOf(i) + ".png");
                File file2 = new File("c://Imagens//delete" + String.valueOf(i) + ".png");
                if (file.exists()){
                   file.delete();
               }
                if(file2.exists()){
                   file2.delete();
                }
             }
          }
    }

    
    private String voltaSexo(){
        String a = "";
        if (cbSexo.getValue() == "Feminino") a="F";
        else if(cbSexo.getValue() == "Masculino") a = "M";
        else if(cbSexo.getValue() == "Unissex") a = "U";
        return a;
    }

    @FXML
    private void btnGradeClick(ActionEvent event) {
        TCCrazy.trocaTela("grade");
    }

    @FXML
    private void btnVoltarClick(ActionEvent event) {
        TCCrazy.trocaTela("inicio");
    }
}