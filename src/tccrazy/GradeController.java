package tccrazy;

import Dao.ConnectionFactory;
import Dao.GradeDao;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import model.Grade;
import model.Produtos;

/**
 * FXML Controller class
 *
 * @author aluno
 */
public class GradeController implements Initializable {
    
    private List<Produtos> listProdutos = new ArrayList<>();
    private ObservableList<Produtos> observableListProdutos;
    
    private List<Grade> listGrade = new ArrayList<>();
    private ObservableList<Grade> observableListGrade;

    @FXML
    private Button btnAlterarImagem;
    @FXML
    private ImageView imgView;
    @FXML
    private Button btnCadastrar;
    @FXML
    private TextField edtPreco;
    @FXML
    private TextField edtCor;
    @FXML
    private TextField edtQnt;
    @FXML
    private TextField edtTamanho;
    @FXML
    private TextField edtPesquisa;
    @FXML
    private ComboBox cbPesquisa;
    @FXML
    private Button btnLimpar;
    @FXML
    private Button btnDeletar;
    @FXML
    private Button btnAlterar;
    @FXML
    private TableView<Grade> tblGrade;
    @FXML
    private TableColumn<Integer, Grade> colId;
    @FXML
    private TableColumn<Integer, Grade> colIdGrade;
    @FXML
    private TableColumn<String, Grade> colCor;
    @FXML
    private TableColumn<String, Grade> colTamanho;
    @FXML
    private TableColumn<Integer, Grade> colQuantidade;
    @FXML
    private TableColumn<Float, Grade> colPreco;
    @FXML
    private TableView<Produtos> tblProduto;
    @FXML
    private TableColumn<Integer, Produtos> colCodigo;
    @FXML
    private TableColumn<String, Produtos> colNome;
    @FXML
    private Button btnVoltar;
    @FXML
    private TableColumn<Float, Grade> colPromo;
    @FXML
    private TextField edtPromo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
            cbPesquisa.getItems().add("ID_Grade");
            cbPesquisa.getItems().add("Preço");
            cbPesquisa.getItems().add("Cor");
            cbPesquisa.getItems().add("Tamanho");
        
            edtPreco.setVisible(false);
            edtQnt.setVisible(false);
            edtTamanho.setVisible(false);
            edtCor.setVisible(false);
        
            btnAlterarImagem.setVisible(false);
            btnAlterar.setVisible(false);
            btnDeletar.setVisible(false);
        
            colCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
            colNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
            
            colPromo.setCellValueFactory(new PropertyValueFactory("promo"));
            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colIdGrade.setCellValueFactory(new PropertyValueFactory<>("id_grade"));
            colCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
            colTamanho.setCellValueFactory(new PropertyValueFactory<>("tamanho"));
            colQuantidade.setCellValueFactory(new PropertyValueFactory<>("qnt"));
            colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
            
        carregaTabelaProduto("SELECT * FROM PRODUTOS");
        
        try {
            deletaImagemInicio();
        } catch (SQLException ex) {
            Logger.getLogger(GradeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        FileInputStream input = null;
        
         try {
            input = new FileInputStream("C:\\Xampp\\htdocs\\site\\prod//branca.png");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
            Image image = new Image(input);
            imgView.setImage(image);
    }

    private void carregaTabelaProduto(String comandoSql){
    
        try {
            listProdutos.clear();
            observableListProdutos.clear();
            tblProduto.getItems().clear(); 
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
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                 Produtos produtos = new Produtos(id, nome);
                listProdutos.add(produtos);
            } while (rs.next());
            conexao.close();
            observableListProdutos = FXCollections.observableArrayList(listProdutos);
            tblProduto.setItems(observableListProdutos);
           
        } catch (Exception erro) {
            System.out.println("Problema ao tentar conectar com o banco de dados: " + erro);
        }
    }  
    
     private void carregaTabelaGrade(int codigo){
    
        try {
            listGrade.clear();
            observableListGrade.clear();
            tblGrade.getItems().clear(); 
        } catch (Exception erro) {
            System.out.println("erro ao carregar tabela: " + erro);
        }
        try {
            Connection conexao = ConnectionFactory.CriaConexao();
            PreparedStatement comando = conexao.prepareStatement("SELECT * FROM grade where id ="+codigo);
            comando.execute();
            ResultSet rs = comando.executeQuery();
            rs.first();
            do {
                int id_grade = rs.getInt("id_grade");
                String cor = rs.getString("cor");
                String tamanho = rs.getString("tamanho");
                float preco = rs.getFloat("preco");
                int quantidade = rs.getInt("quantidade");
                float promo = rs.getFloat("promocao");
                Grade grade = new Grade(codigo, id_grade, cor, tamanho, preco, quantidade, promo);
                listGrade.add(grade);
            } while (rs.next());
            conexao.close();
            observableListGrade = FXCollections.observableArrayList(listGrade);
            tblGrade.setItems(observableListGrade);
            tblGrade.getSelectionModel().select(0);
            
        } catch (Exception erro) {
            System.out.println("Problema ao tentar conectar com o banco de dados: " + erro);
        }
    }
     
      private void carregaTabelaGrade(String sql){
    
        try {
            listGrade.clear();
            observableListGrade.clear();
            tblGrade.getItems().clear(); 
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
                int id = rs.getInt("id");
                int id_grade = rs.getInt("id_grade");
                String cor = rs.getString("cor");
                String tamanho = rs.getString("tamanho");
                float preco = rs.getFloat("preco");
                int quantidade = rs.getInt("quantidade");
                float promo = rs.getFloat("promocao");
                Grade grade = new Grade(id, id_grade, cor, tamanho, preco, quantidade, promo);
                listGrade.add(grade);
            } while (rs.next());
            conexao.close();
            observableListGrade = FXCollections.observableArrayList(listGrade);
            tblGrade.setItems(observableListGrade);
            tblGrade.getSelectionModel().select(0);
            
        } catch (Exception erro) {
            System.out.println("Problema ao tentar conectar com o banco de dados: " + erro);
        }
    }

    @FXML
    private void btnAlterarImagemClick(ActionEvent event) throws SQLException {
        int id = tblGrade.getSelectionModel().getSelectedItem().getId_grade();
        
        File file = new File("C:\\Xampp\\htdocs\\site\\prod//" + String.valueOf(id) + ".png");
        
        file.renameTo(new File("C:\\Xampp\\htdocs\\site\\prod//delete1.png"));
            
          final FileChooser fileChooser = new FileChooser();
          setExtentionFilters(fileChooser);
          File fileA = fileChooser.showOpenDialog(null);
          if( fileA != null){
              String nomeImagem;
              nomeImagem = String.valueOf(id);
              try {
                  openNewImageWindow(fileA, nomeImagem);
              } catch (IOException ex) {
                  Logger.getLogger(TCCrazy.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
    }

    @FXML
    private void btnCadastrarClick(ActionEvent event) throws SQLException {
        int codigo = tblProduto.getSelectionModel().getSelectedItem().getId();
        
        Grade grade = new Grade(codigo, edtCor.getText(), edtTamanho.getText(),Float.valueOf(edtPreco.getText()), Integer.valueOf(edtQnt.getText()), Float.valueOf(edtPromo.getText()));
        GradeDao.InserirGradeBancoDados(grade);
        carregaTabelaGrade(codigo);
        
        final FileChooser fileChooser = new FileChooser();
        setExtentionFilters(fileChooser);
          File file = fileChooser.showOpenDialog(null);
          if(file != null){
          String nomeImagem;
          nomeImagem = GradeDao.proximaId_grade();
              try {
                  openNewImageWindow(file, nomeImagem);
              } catch (IOException ex) {
                  Logger.getLogger(TCCrazy.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
    }

    @FXML
    private void edtPesquisaKeyReleased(KeyEvent event) {
        if (cbPesquisa.getValue() == "ID_Grade"){
            String sql = "select * from GRADE where id_grade like '%" + edtPesquisa.getText() + "%' order by id";
            listGrade.clear();
            observableListGrade.clear();
            tblGrade.getItems().clear();
            tblGrade.refresh();
            carregaTabelaGrade(sql);           
        }
        else if (cbPesquisa.getValue() == "Preço"){
            String sql = "select * from GRADE where nome like '%" + edtPesquisa.getText() + "%' order by id";
            listGrade.clear();
            observableListGrade.clear();
            tblGrade.getItems().clear();
            tblGrade.refresh();
            carregaTabelaGrade(sql);
        }
        else if (cbPesquisa.getValue() == "Cor"){
            String sql = "select * from GRADE where nome like '%" + edtPesquisa.getText() + "%' order by id";
            listGrade.clear();
            observableListGrade.clear();
            tblGrade.getItems().clear();
            tblGrade.refresh();
            carregaTabelaGrade(sql);
        }
        else if (cbPesquisa.getValue() == "Tamanho"){
            String sql = "select * from GRADE where nome like '%" + edtPesquisa.getText() + "%' order by id";
            listGrade.clear();
            observableListGrade.clear();
            tblGrade.getItems().clear();
            tblGrade.refresh();
            carregaTabelaGrade(sql);
        }
        else edtPesquisa.clear();
    }

    @FXML
    private void btnLimparClick(ActionEvent event) {
        edtPreco.clear();
        edtQnt.clear();
        edtCor.clear();
        edtTamanho.clear();
        edtPromo.clear();
    }

    @FXML
    private void btnDeletarClick(ActionEvent event) throws FileNotFoundException {
        int i = tblGrade.getSelectionModel().getSelectedIndex();
        Grade grade = tblGrade.getSelectionModel().getSelectedItem();
        if (i >= 0) {
            listGrade.remove(i);
            tblGrade.setItems(FXCollections.observableArrayList(listGrade));
            tblGrade.getSelectionModel().selectFirst();
            
            File file = new File("C:\\Xampp\\htdocs\\site\\prod//" + grade.getId_grade() + ".png");        
            file.renameTo(new File("C:\\Xampp\\htdocs\\site\\prod//delete1.png"));
            
            GradeDao.ApagarGradeBancoDados(grade.getId_grade());
            
            FileInputStream input = new FileInputStream("C:\\Xampp\\htdocs\\site\\prod//branca.png");
            Image image = new Image(input);
            imgView.setImage(image);
                   
            
        } else {
            Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
            dialogoErro.setTitle("Diálogo de Erro");
            dialogoErro.setHeaderText("Remoção de Ítens...");
            dialogoErro.setContentText("Atenção: Não há items para serem removidos.!");
            dialogoErro.showAndWait();
        }
    }

    @FXML
    private void btnAlterarClick(ActionEvent event) throws SQLException {
        int i = tblGrade.getSelectionModel().getSelectedIndex();
        int id = tblGrade.getSelectionModel().getSelectedItem().getId_grade();
        
        if(i>=0){
            Grade grade = new Grade(id, edtCor.getText(), edtTamanho.getText(), Float.valueOf(edtPreco.getText()), Integer.parseInt(edtQnt.getText()), Float.valueOf(edtPromo.getText()));
            GradeDao.AlterarItemBancoDados(grade);
            carregaTabelaGrade(id);
            tblGrade.getSelectionModel().selectFirst();
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
    private void tblProdutoClick(MouseEvent event) {
       int i = tblProduto.getSelectionModel().getSelectedIndex();
       int codigo = tblProduto.getSelectionModel().getSelectedItem().getId();
       
       if (i>=0){
           carregaTabelaGrade(codigo);
           edtPreco.setVisible(true);
           edtQnt.setVisible(true);
           edtTamanho.setVisible(true);
           edtCor.setVisible(true);
       }
       else{
          edtPreco.setVisible(false);
           edtQnt.setVisible(false);
           edtTamanho.setVisible(false);
           edtCor.setVisible(false); 
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
    private void tblGradeClick(MouseEvent event) throws FileNotFoundException {
        int i = tblGrade.getSelectionModel().getSelectedIndex();
        Grade grade = tblGrade.getSelectionModel().getSelectedItem();
        
        if (i>=0){
          btnDeletar.setVisible(true);
          btnAlterar.setVisible(true);
          btnAlterarImagem.setVisible(true);
          
          edtCor.setText(grade.getCor());
          edtQnt.setText(String.valueOf(grade.getQnt()));
          edtTamanho.setText(grade.getTamanho());
          edtPreco.setText(String.valueOf(grade.getPreco()));  
          edtPromo.setText(String.valueOf(grade.getPromo()));

           File file = new File("C:\\Xampp\\htdocs\\site\\prod//" + String.valueOf(grade.getId_grade()) + ".png");
            
            if (!file.exists()){
                FileInputStream input = new FileInputStream("C:\\Xampp\\htdocs\\site\\prod//branca.png");
                Image image = new Image(input);
                imgView.setImage(image);
            }
            else{
              System.out.println("C:\\Xampp\\htdocs\\site\\prod" + String.valueOf(grade.getId_grade()) + ".png");
              FileInputStream input = new FileInputStream("C:\\Xampp\\htdocs\\site\\prod//" + String.valueOf(grade.getId_grade()) + ".png");
              Image image = new Image(input);
              imgView.setImage(image);
            }
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
    
    
    
     private void setExtentionFilters(FileChooser c) {
    
        c.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Arquivos de imagens","*.*"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
           }
    
    private void openNewImageWindow(File file, String nome) throws IOException, SQLException {
         int largura = 963;
         int altura = 640;
         BufferedImage image = null;
         try{
         image = new BufferedImage(largura,altura,BufferedImage.TYPE_INT_ARGB);
         image = ImageIO.read(file);
         System.out.println("Leitura completa."); 
         
         }catch(IOException e){
         System.out.println("Erro:"+e);
         }
         try{
         File f = new File("C:\\Xampp\\htdocs\\site\\prod//"+nome+".png");
         boolean teste = ImageIO.write(image,"png", f);
         FileInputStream input = new FileInputStream("C:\\Xampp\\htdocs\\site\\prod//" + nome + ".png");
            Image imagemCarro = new Image(input);
            imgView.setImage(imagemCarro);
         System.out.println("Gravação completa.");
         }catch(IOException e){
         System.out.println("Erro: "+e);
         }
           }
    
        private void deletaImagemInicio() throws SQLException{
            
          Grade grade = new Grade();
          Connection conexao = ConnectionFactory.CriaConexao();
          PreparedStatement comando = conexao.prepareStatement("select * from grade order by id_grade desc");
          comando.execute();
          ResultSet rs = comando.executeQuery();
            
            
          if(rs.first()){
            int a = rs.getInt("id_grade");
            comando.close();
            conexao.close();
            
            Connection conexao2 = ConnectionFactory.CriaConexao();
            PreparedStatement c = conexao2.prepareStatement("select * from grade order by id_grade");
            c.execute();
            ResultSet rs2 = c.executeQuery();
            rs2.first();
            
            for (int i=1;i<=a;i++){
                File file = new File("C:\\Xampp\\htdocs\\site\\prod//" + String.valueOf(i) + ".png");
               if (file.exists()){
                int id2 = rs2.getInt("id_grade");
                  if (id2 > i){
                     file.delete();
                  }
                  else rs2.next();
               }
            }
            c.close();
            conexao2.close();}
          
          else{
             for (int i=1; i<100000;i++){
                File file = new File("C:\\Xampp\\htdocs\\site\\prod//" + String.valueOf(i) + ".png");
                File file2 = new File("C:\\Xampp\\htdocs\\site\\prod//delete" + String.valueOf(i) + ".png");
                if (file.exists()){
                   file.delete();
               }
                if(file2.exists()){
                   file2.delete();
                }
             }
          }
    }

    @FXML
    private void btnVoltar(ActionEvent event) {
        TCCrazy.trocaTela("principal");
    }

    @FXML
    private void OnMenuRequested(ContextMenuEvent event) {
        carregaTabelaProduto("SELECT * from Produtos");
    }


}