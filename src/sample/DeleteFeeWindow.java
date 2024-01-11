package sample;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.*;

public class DeleteFeeWindow {

    @FXML
    private TextField UserIDField;

    @FXML
    private TextField BookIDField;

    @FXML
    void Cancel(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setResizable(false);
    }

    @FXML
    void Confirm(MouseEvent event) {

        String URL = "jdbc:mysql://localhost:3306/users?useSSL=false&serverTimezone=UTC";
        String USER = "root";
        String PASSWORD = "MySQL";
        String ID = UserIDField.getText();
        String BookID = BookIDField.getText();
        String reg = "delete from fee_info where (user_id,book_id) = ("+ID+","+BookID+")";
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(reg);
            preparedStatement.execute();

          //return book in store
          String upd = "select book_quantity from books where book_id = "+BookID+";";
          int DBQuantity=0;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(upd);
            while (resultSet.next()){
                DBQuantity=resultSet.getInt(1);
            }
            DBQuantity = DBQuantity+1;
            String upd1="update books set book_quantity =? where book_id= ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(upd1);
            preparedStatement1.setInt(1,DBQuantity);
            preparedStatement1.setString(2,BookID);
            preparedStatement1.execute();
          //
        }
        catch (SQLException e) {
            System.out.println("Something went wrong, try again..."); }

        //close scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    }

}

