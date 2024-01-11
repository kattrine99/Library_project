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
import java.util.Date;
import java.sql.*;
import java.util.ResourceBundle;

public class FeeWindow  implements Initializable {
    private String URL = "jdbc:mysql://localhost:3306/users?useSSL=false&serverTimezone=UTC";
    private String USER = "root";
    private String PASSWORD = "MySQL";
    private Connection connection;

    private ObservableList<FeeInfo> FeeData = FXCollections.observableArrayList();


    @FXML
    private TextField SearchField;
    @FXML
    private TableView<FeeInfo> FeeTable;


    @FXML
    private TableColumn<FeeInfo, String> CUID;

    @FXML
    private TableColumn<FeeInfo, String> CUName;

    @FXML
    private TableColumn<FeeInfo, String> CBID;

    @FXML
    private TableColumn<FeeInfo, String> CBName;

    @FXML
    private TableColumn<FeeInfo, Date> CIDate;

    @FXML
    private TableColumn<FeeInfo, Date> CFDate;

    @FXML
    private TableColumn<FeeInfo, Double> CFee;

    @FXML
    void DeleteUserFee(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteFeeWindow.fxml"));
        Parent root2 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(new Scene(root2));
        stage.show();
        stage.setResizable(false);
    }

    @FXML
    void RefreshTable(MouseEvent event) {
        FeeTable.getItems().clear();
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            String query = "SELECT * from fee_info ";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                FeeData.add(new FeeInfo(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),
                        resultSet.getString(4),resultSet.getDate(5),resultSet.getDate(6),resultSet.getDouble(7)));}

            CUID.setCellValueFactory(new PropertyValueFactory<>("UserID"));
            CUName.setCellValueFactory(new PropertyValueFactory<>("UserName"));
            CBID.setCellValueFactory(new PropertyValueFactory<>("BookID"));
            CBName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
            CIDate.setCellValueFactory(new PropertyValueFactory<>("IDate"));
            CFDate.setCellValueFactory(new PropertyValueFactory<>("FinalDate"));
            CFee.setCellValueFactory(new PropertyValueFactory<>("Fee"));
            FeeTable.setItems(FeeData);

        }
        catch (SQLException e) {
            e.printStackTrace();}
    }

    @FXML
    void SearchFee(MouseEvent event) {

        FeeTable.getItems().clear();
        String ID = SearchField.getText();
        String query= " SELECT * from fee_info where user_id = "+ID+"";
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                FeeData.add(new FeeInfo(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),
                        resultSet.getString(4),resultSet.getDate(5),resultSet.getDate(6),resultSet.getDouble(7)));}

            CUID.setCellValueFactory(new PropertyValueFactory<>("UserID"));
            CUName.setCellValueFactory(new PropertyValueFactory<>("UserName"));
            CBID.setCellValueFactory(new PropertyValueFactory<>("BookID"));
            CBName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
            CIDate.setCellValueFactory(new PropertyValueFactory<>("IDate"));
            CFDate.setCellValueFactory(new PropertyValueFactory<>("FinalDate"));
            CFee.setCellValueFactory(new PropertyValueFactory<>("Fee"));
            FeeTable.setItems(FeeData);
            SearchField.clear();}
        catch (SQLException e) {
            System.out.println("search request is invalid!!"); }
    }

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            String query = "SELECT * from fee_info ";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                FeeData.add(new FeeInfo(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),
                        resultSet.getString(4),resultSet.getDate(5),resultSet.getDate(6),resultSet.getDouble(7)));}

            CUID.setCellValueFactory(new PropertyValueFactory<>("UserID"));
            CUName.setCellValueFactory(new PropertyValueFactory<>("UserName"));
            CBID.setCellValueFactory(new PropertyValueFactory<>("BookID"));
            CBName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
            CIDate.setCellValueFactory(new PropertyValueFactory<>("IDate"));
            CFDate.setCellValueFactory(new PropertyValueFactory<>("FinalDate"));
            CFee.setCellValueFactory(new PropertyValueFactory<>("Fee"));
            FeeTable.setItems(FeeData); }
        catch (SQLException e) {
            e.printStackTrace();}
    }
}
