package sample;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModifyBook {

    @FXML
    private TextField IDField;

    @FXML
    private TextField NameField;

    @FXML
    private TextArea DescriptionField;

    @FXML
    private TextField QuantityField;

    @FXML
    private TextField FeeField;

    @FXML
    void Cancel(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();}

    @FXML
    void Confirm(MouseEvent event) {
        //Connection block
        String URL = "jdbc:mysql://localhost:3306/users?useSSL=false&serverTimezone=UTC";
        String USER = "root";
        String PASSWORD = "MySQL";
        Connection connection;


        String ID = IDField.getText();
        String Name = NameField.getText();
        String Description = DescriptionField.getText();
        String Q = QuantityField.getText();
        String F = FeeField.getText();

        try {
            int Quantity = Integer.parseInt(Q);
            double Fee = Double.parseDouble(F);
            String query = "update books set book_name = ?, book_description = ?, book_quantity = ?, book_fee = ? where book_id =? ";

            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(5,ID);
            preparedStatement.setString(1,Name);
            preparedStatement.setString(2,Description);
            preparedStatement.setInt(3,Quantity);
            preparedStatement.setDouble(4,Fee);
            preparedStatement.execute();
            }
        catch (Exception e) {
            System.out.println("Something went wrong, try again...");}

        //close scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    }

}

