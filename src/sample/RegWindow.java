package sample;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.sql.*;

public class RegWindow {
        @FXML
        private TextField IDField;

        @FXML
        private TextField NameField;

        @FXML
        private TextField PassWordField;

    public void CancelAction(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    stage.setResizable(false);
    }

    public void ConfirmAction(MouseEvent event) throws SQLException {
        String URL = "jdbc:mysql://localhost:3306/users?useSSL=false&serverTimezone=UTC";
        String USER = "root";
        String PASSWORD = "MySQL";
        String reg = "insert into login_users (id,user_name,user_password) values(?,?,?);";
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                String ID = IDField.getText();
                String Name = NameField.getText();
                String Password = PassWordField.getText();

            PreparedStatement preparedStatement = connection.prepareStatement(reg);
            preparedStatement.setString(1,ID);
            preparedStatement.setString(2,Name);
            preparedStatement.setString(3,Password);
            preparedStatement.execute();}
        catch (SQLException e) {
            e.printStackTrace();
        }


        //close scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setResizable(false);
    }
}
