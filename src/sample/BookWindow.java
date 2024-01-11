package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class BookWindow  implements Initializable {
    private String URL = "jdbc:mysql://localhost:3306/users?useSSL=false&serverTimezone=UTC";
    private String USER = "root";
    private String PASSWORD = "MySQL";
    private Connection connection;

    private ObservableList<Book> BookData = FXCollections.observableArrayList();
    @FXML
    private TableView<Book> TableBook;

    @FXML
    private TextField SearchField;

    @FXML
    private TableColumn<Book, String> CID;

    @FXML
    private TableColumn<Book, String> CName;

    @FXML
    private TableColumn<Book, String> CDescript;

    @FXML
    private TableColumn<Book, Integer> CQuantity;

    @FXML
    private TableColumn<Book, Double> CFee;

    @FXML
    void AddBook(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddBook.fxml"));
        Parent root2 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Registration");
        stage.setScene(new Scene(root2));
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        stage.setResizable(false);

    }

    @FXML
    void Clear(MouseEvent event) {
        TableBook.getItems().clear();
    }

    @FXML
    void DeleteBook(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteBook.fxml"));
        Parent root2 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(new Scene(root2));
        stage.show();

    }

    @FXML
    void Modify(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifyBook.fxml"));
        Parent root2 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(new Scene(root2));
        stage.show();

    }

    @FXML
    void Refresh(MouseEvent event) {
        TableBook.getItems().clear();
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Connection Established!");
            }
            Statement statement = connection.createStatement();
            String query = "SELECT * from books ";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                BookData.add(new Book(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),
                        resultSet.getInt(4),resultSet.getDouble(5)));}


            CID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            CName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
            CDescript.setCellValueFactory(new PropertyValueFactory<>("BookDesc"));
            CQuantity.setCellValueFactory(new PropertyValueFactory<>("BookQuantity"));
            CFee.setCellValueFactory(new PropertyValueFactory<>("BookFee"));
            TableBook.setItems(BookData);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void Search(MouseEvent event) {
        TableBook.getItems().clear();
        String ID = SearchField.getText();
        String query= " SELECT * from books where book_id = "+ID+"";
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Connection Established!");
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                BookData.add(new Book(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),
                        resultSet.getInt(4),resultSet.getDouble(5)));}


            CID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            CName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
            CDescript.setCellValueFactory(new PropertyValueFactory<>("BookDesc"));
            CQuantity.setCellValueFactory(new PropertyValueFactory<>("BookQuantity"));
            CFee.setCellValueFactory(new PropertyValueFactory<>("BookFee"));
            TableBook.setItems(BookData);
            SearchField.clear();
        } catch (SQLException e) {
           System.out.println("search request is invalid!!");}

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            String query = "SELECT * from books ";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                BookData.add(new Book(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),
                        resultSet.getInt(4),resultSet.getDouble(5)));}


            CID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            CName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
            CDescript.setCellValueFactory(new PropertyValueFactory<>("BookDesc"));
            CQuantity.setCellValueFactory(new PropertyValueFactory<>("BookQuantity"));
            CFee.setCellValueFactory(new PropertyValueFactory<>("BookFee"));
            TableBook.setItems(BookData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}