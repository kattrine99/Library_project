package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.util.Date;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

public class FeeWindowStudent  implements Initializable {
    private String URL = "jdbc:mysql://localhost:3306/users?useSSL=false&serverTimezone=UTC";
    private String USER = "root";
    private String PASSWORD = "MySQL";
    private Connection connection;

    private ObservableList<FeeInfo> FeeData = FXCollections.observableArrayList();

    @FXML
    private TextField SearchField;
    @FXML
    private TableView<FeeInfo> FeeTable;
    private String Test = " ";
    @FXML
    private Label TotalFeeLabel;
    double Total =0;



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
    void RefreshTable(MouseEvent event) {
        FeeTable.getItems().clear();
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();

            //recieving date from transmititer
            Transmitor tr = Transmitor.getInstance();
            Test = tr.GetName();
            String ID = tr.getID();
            //
            Statement statement1 = connection.createStatement();
            String query1 = "SELECT * from fee_info where user_id = "+ID+"";
            ResultSet resultSet = statement1.executeQuery(query1);
            while (resultSet.next()){
                FeeData.add(new FeeInfo(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),
                        resultSet.getString(4),resultSet.getDate(5),resultSet.getDate(6),resultSet.getDouble(7))); }


            CUID.setCellValueFactory(new PropertyValueFactory<>("UserID"));
            CUName.setCellValueFactory(new PropertyValueFactory<>("UserName"));
            CBID.setCellValueFactory(new PropertyValueFactory<>("BookID"));
            CBName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
            CIDate.setCellValueFactory(new PropertyValueFactory<>("IDate"));
            CFDate.setCellValueFactory(new PropertyValueFactory<>("FinalDate"));
            CFee.setCellValueFactory(new PropertyValueFactory<>("Fee"));
            FeeTable.setItems(FeeData);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void SearchFee(MouseEvent event) {

        FeeTable.getItems().clear();
        String BookID = SearchField.getText();

        //transmitor
        Transmitor tr = Transmitor.getInstance();

        //
        try {

            String query= " SELECT * from fee_info where (user_id,book_id) = ("+tr.getID()+","+BookID+")";
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                FeeData.add(new FeeInfo(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),
                        resultSet.getString(4),resultSet.getDate(5),resultSet.getDate(6),resultSet.getDouble(7)));}

            CUID.setCellValueFactory(new PropertyValueFactory<>("UserID"));
            CUName.setCellValueFactory(new PropertyValueFactory<>("UserName"));
            CBID.setCellValueFactory(new PropertyValueFactory<>("BookID"));
            CBName.setCellValueFactory(new PropertyValueFactory("BookName"));
            CIDate.setCellValueFactory(new PropertyValueFactory<>("IDate"));
            CFDate.setCellValueFactory(new PropertyValueFactory<>("FinalDate"));
            CFee.setCellValueFactory(new PropertyValueFactory<>("Fee"));
            FeeTable.setItems(FeeData);
            SearchField.clear();

        } catch (SQLException e) {
            System.out.println("search request is invalid!!");}
    }

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            //recieving date from transmititer
            Transmitor tr = Transmitor.getInstance();
            Test = tr.GetName();
            String ID = tr.getID();
            //
            Statement statement = connection.createStatement();
            String query = "SELECT * from fee_info where user_id = "+ID+"";
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


            String total = "SELECT fee_value from fee_info where user_id = "+ID+"";
            ResultSet resultSetFee = statement.executeQuery(total);
            while(resultSetFee.next()){
                Total += Double.parseDouble(resultSetFee.getString(1));
            }
            TotalFeeLabel.setText(String.valueOf(Total));




        }
        catch (SQLException e) {
            System.out.println("search request is invalid!!");}
    }
}
