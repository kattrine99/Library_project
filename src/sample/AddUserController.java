package sample;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddUserController {

    @FXML
    private TextField IDField;

    @FXML
    private TextField NameField;

    @FXML
    private TextField PassWordField;

    public void CancelAction(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
     }

    public void ConfirmAction(MouseEvent event)  {
        String URL = "jdbc:mysql://localhost:3306/users?useSSL=false&serverTimezone=UTC";
        String USER = "root";
        String PASSWORD = "MySQL";
        String reg = "insert into login_users (id,user_name,user_password) values(?,?,?);";

        String ID = IDField.getText();
        String Name = NameField.getText();
        String Password = PassWordField.getText();


          try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(reg);
            preparedStatement.setString(1,ID);
            preparedStatement.setString(2,Name);
            preparedStatement.setString(3,Password);
            preparedStatement.execute();}
        catch (SQLException e) {
            System.out.println("Something went wrong, try again...");}

        //close scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();

    }
}
