package sample;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;

public class Reservation {

    @FXML
    private TextField BookID;

    @FXML
    private DatePicker InitDate;

    @FXML
    private DatePicker FinalDate;



    @FXML
    void Cancel(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();}

    @FXML
    void Confirm(MouseEvent event) {
        String URL = "jdbc:mysql://localhost:3306/users?useSSL=false&serverTimezone=UTC";
        String USER = "root";
        String PASSWORD = "MySQL";

        // Transmitor
        Transmitor tr = Transmitor.getInstance();
        //


        try {
            Connection connection;
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();


            // for know i collect id and name of student
            String BookId = BookID.getText();
            String BookName = "X";
            double Fee=0.0;


            String query1 = "select book_name,book_fee from books where book_id ="+BookId+";";
            ResultSet resultSet1 = statement.executeQuery(query1);
            while(resultSet1.next()){
                BookName= resultSet1.getString(1);
                Fee = resultSet1.getDouble(2);}
            // from this moment i have id name of user and id name of book
            // only date left..
            LocalDate init = InitDate.getValue();
            LocalDate Final = FinalDate.getValue();

            // suppose that it is working  now i should join everything into one query
            String reg = "insert into fee_info (user_id,user_name,book_id,book_name,initial_date,final_date,fee_value)" +
                    "values(?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(reg);
            preparedStatement.setString(1,tr.getID());
            preparedStatement.setString(2,tr.GetName());
            preparedStatement.setString(3,BookId);
            preparedStatement.setString(4,BookName);
            preparedStatement.setDate(5, Date.valueOf(init));
            preparedStatement.setDate(6, Date.valueOf(Final));
            preparedStatement.setDouble(7,Fee);
            preparedStatement.execute();

            //reduce quantity of book
            int Quantity=0;
            String oldQ ="select book_quantity from users.books where book_id ="+BookId+"";
            ResultSet resultSet2 = statement.executeQuery(oldQ);
            while (resultSet2.next()){
                Quantity=Integer.parseInt(resultSet2.getString(1));//extract Quantity from database
            }
            Quantity=Quantity-1;
            String newQ = "update books set book_quantity = ? where book_id =? ";
            PreparedStatement preparedStatement1 = connection.prepareStatement(newQ);
            preparedStatement1.setInt(1,Quantity);
            preparedStatement1.setString(2,BookId);
            preparedStatement1.execute(); // pass new value of Quantity to database
            //

        }
        catch (Exception e) {
            System.out.println("Invalid request, check again...");}

        //close scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    }
}

