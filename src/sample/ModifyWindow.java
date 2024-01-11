package sample;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModifyWindow {

    @FXML
    private TextField IDField;

    @FXML
    private TextField NameField;

    @FXML
    private TextField PasswordField;

    @FXML
    private TextField StatusField;

    public void Cancel(MouseEvent event) {
        System.out.println("Cancel Button activated");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
     }

    public void Configure(MouseEvent event)  {

        //Connection block
        String URL = "jdbc:mysql://localhost:3306/users?useSSL=false&serverTimezone=UTC";
        String USER = "root";
        String PASSWORD = "MySQL";
        Connection connection;


        String ID = IDField.getText();
        String Name = NameField.getText();
        String Password = PasswordField.getText();
        String Status = StatusField.getText();


        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            String query = "update login_users set user_name = ?, user_password = ?, user_status = ? where id =? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(4,ID);
            preparedStatement.setString(1,Name);
            preparedStatement.setString(2,Password);
            preparedStatement.setString(3,Status);
            preparedStatement.execute();
            }
        catch (SQLException e) {
            System.out.println("Something went wrong, try again...");}

        //close scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    }
}
