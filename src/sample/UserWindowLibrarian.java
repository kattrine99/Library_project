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

public class UserWindowLibrarian implements Initializable {
    private String URL = "jdbc:mysql://localhost:3306/users?useSSL=false&serverTimezone=UTC";
    private String USER = "root";
    private  String PASSWORD = "MySQL";
    private Connection connection;

    private ObservableList<User> usersData = FXCollections.observableArrayList();
    @FXML
    public TableView<User> TableUsers;
    @FXML
    private TableColumn<User,String> CID;

    @FXML
    private TableColumn<User,String> CName;

    @FXML
    private TableColumn<User,String> CPassword;
    @FXML
    private TableColumn<User, String> CStatus;
    @FXML
    private TextField SearchField;

    public void Modify(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifyWindow.fxml"));
        Parent root2 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(new Scene(root2));
        stage.show();
        stage.setResizable(false);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Connection Established!");}
            Statement statement = connection.createStatement();
            String query = "SELECT * from login_users where user_status = 'Librarian' or user_status='Student';";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                usersData.add(new User(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4))); }

            CID.setCellValueFactory(new PropertyValueFactory<>("id"));
            CName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            CPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
            CStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
            TableUsers.setItems(usersData);}
        catch (SQLException e) {
            e.printStackTrace();}
    }

    @FXML
    void Refresh(MouseEvent event) {
        TableUsers.getItems().clear();
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            String query = "SELECT * from login_users where user_status = 'Librarian' or user_status='Student';";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                usersData.add(new User(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4))); }

            CID.setCellValueFactory(new PropertyValueFactory<>("id"));
            CName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            CPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
            CStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
            TableUsers.setItems(usersData);}
        catch (SQLException e) {
            e.printStackTrace();}
    }

    @FXML
    void Search(MouseEvent event) throws InterruptedException {
        TableUsers.getItems().clear();
        String ID = SearchField.getText();

        String query= " SELECT * from login_users where id = "+ID+"";

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                usersData.add(new User(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4))); }

            CID.setCellValueFactory(new PropertyValueFactory<>("id"));
            CName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            CPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
            CStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
            TableUsers.setItems(usersData);
            SearchField.clear();}
        catch (SQLException e) {
            System.out.println("Invalid search");}
    }
    @FXML
    void Clear(MouseEvent event) {TableUsers.getItems().clear();}

    public void DeleteUser(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteUser.fxml"));
        Parent root2 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(new Scene(root2));
        stage.show();

    }

    public void AddUser(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddUser.fxml"));
        Parent root2 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(new Scene(root2));
        stage.show();

    }
}