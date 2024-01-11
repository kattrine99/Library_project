package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class SecondPageLibrarian {


    @FXML
    private BorderPane borderpane;
    @FXML
    private Label LabelName;

    @FXML
    private Label LabelStatus;
    @FXML
    void FeeWindowShow(MouseEvent event) {loadUI("FeeWindowLibrarian");}
    @FXML
    void UserWindowShow(MouseEvent event) {loadUI("UserWindowLibrarian");}
    @FXML
    void BookWindowShow(MouseEvent event) {loadUI("BookWindowLibrarian");}

    //Download UI in the center
    private void loadUI(String ui){
        Parent root=null;
        //Possible mistakes
        try {
            root = FXMLLoader.load(getClass().getResource(ui+".fxml"));}
        catch (IOException e) {
            System.out.println("Cant load UI");}
        borderpane.setCenter(root);}

    public void Clear(MouseEvent event) {borderpane.setCenter(null);}
    @FXML
    void LogOut(MouseEvent event) throws IOException {
        System.out.println("LogoutButton was activated");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Sample.fxml"));
        Parent root2 =  fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setTitle("First Page");
        stage.setScene(new Scene(root2));
        stage.show();
        stage.setResizable(false);

    }
    public void function(String text) throws SQLException {
        LabelName.setText("Name: "+text);

        String URL = "jdbc:mysql://localhost:3306/users?useSSL=false&serverTimezone=UTC";
        String USER = "root";
        String PASSWORD = "MySQL";
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        String query = "SELECT * from login_users ";
        ResultSet resultSet = statement.executeQuery(query);

        String Status="none";
        String DBName;
        String DBStatus;
        while(resultSet.next()){
            DBName = resultSet.getString(2);
            DBStatus = resultSet.getString(4);
            if(text.equals(DBName)){
                Status = DBStatus;
                break;}
        }
        LabelStatus.setText("Status: "+Status);
    }
}
