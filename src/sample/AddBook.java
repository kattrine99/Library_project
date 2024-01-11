package sample;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddBook {

    @FXML
    private TextField IDField;

    @FXML
    private TextField NameField;

    @FXML
    private TextField DescriptionField;

    @FXML
    private TextField QuantityField;

    @FXML
    private TextField FeeField;

    @FXML
    void CancelAction(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();

    }

    @FXML
    void ConfirmAction(MouseEvent event) {
        String URL = "jdbc:mysql://localhost:3306/users?useSSL=false&serverTimezone=UTC";
        String USER = "root";
        String PASSWORD = "MySQL";
        Connection connection;
        String reg = "insert into books (book_id,book_name,book_description,book_quantity,book_fee) values(?,?,?,?,?);";

        String ID = IDField.getText();
        String Name = NameField.getText();
        String Description = DescriptionField.getText();
        String Q = QuantityField.getText();
        String F = FeeField.getText();
        boolean flag=false;
        boolean flag1=false;
        boolean flag3=false;
        boolean flag4=false;
        boolean flag5=false;

        int Quantity = Integer.parseInt(Q); //Transform string Q into integer
        double Fee = Double.parseDouble(F);//Transform string F into double

        if(ID != null){
            flag=true;
        }
        if(Name != null){
            flag1=true;
        }
        if(Description != null){
            flag3=true;
        }
        if(Quantity!=0){
            flag4=true;
        }
        if(Fee != 0){
            flag5=true;
        }

        if(flag&&flag1&&flag3&&flag4&&flag5){
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(reg);
            preparedStatement.setString(1, ID);
            preparedStatement.setString(2, Name);
            preparedStatement.setString(3, Description);
            preparedStatement.setInt(4, Quantity);
            preparedStatement.setDouble(5, Fee);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Invalid request, try again....");
        }}
        else{
            System.out.println("Something went wrong, try again...");
        }

        //close scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    }

}
