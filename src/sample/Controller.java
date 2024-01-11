package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.*;

public class Controller {
    public Button AdminModeButton;
    private boolean admin; // admin mode status
    public Label FailLog;
    public Label PasswordFailLog;
    @FXML
    private TextField NameField;
    @FXML
    private PasswordField PasswordField;
    private String id;



    @FXML
    void LoginButton(MouseEvent event) throws IOException {
        //Flag of the authentication
        boolean auth = false; // verification of name
        boolean auth1 = false; // verification of password
        boolean ScreenSwitch= false; // Screen admin/user
        boolean LibrarianMode = false; // Switch to librarian
        String URL = "jdbc:mysql://localhost:3306/users?useSSL=false&serverTimezone=UTC";
        String USER = "root";
        String PASSWORD = "MySQL";

       // Step 1: check if Admin mode is on/off
        if(admin){
            //if admin == TRUE then ignore all validation
            auth = true;
            auth1 = true;
            ScreenSwitch=true;
        }
        else{
            //if admin ==FALSE then check name and password of the user
            String Name = NameField.getText();
            String password= PasswordField.getText();
            String DBName; // DB Name
            String DBPassword; // DB password
            String Admin = "Admin";
            String Librarian = "Librarian";
            String DBStatus;

            try {
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                if (!connection.isClosed()) {
                    System.out.println("Connection Established!");
                }
                Statement statement = connection.createStatement();
                String query = "SELECT * from login_users ";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    DBName = resultSet.getString(2);
                    DBPassword = resultSet.getString(3);
                    DBStatus=resultSet.getString(4);

                    //Name auth
                    if (Name.equals(DBName)) {
                        auth = true;}
                    else {
                        FailLog.setText("Invalid name!");}
                    //password auth
                    if (password.equals(DBPassword)) {
                        auth1 = true;}
                    else {
                        PasswordFailLog.setText("Invalid password!");}
                    if(Name.equals(DBName) && password.equals(DBPassword) && Admin.equals(DBStatus)){
                        ScreenSwitch=true;}
                    if(Name.equals(DBName) && password.equals(DBPassword) && Librarian.equals(DBStatus)){
                        LibrarianMode=true;}
                }

                connection.close();
                System.out.println("Connection closed!");
            }catch (SQLException e) {
                //e.printStackTrace();
                System.out.println("No connection with DB, check connection or try Admin mode.");
            }
        }

        //Step 2: check status of auth,auth1. If auth,auth1 ==TRUE then allow user to go further
        if(auth && auth1){
            if(ScreenSwitch) {
                try {

                    System.out.println("LoginButton was activated");
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SecondPage.fxml"));
                    Parent root2 = fxmlLoader.load();

                    //parsing name to second controller
                    ComControllers(fxmlLoader);

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.hide();
                    stage.setTitle("Second Page");
                    stage.setScene(new Scene(root2));
                    stage.show();
                    stage.setResizable(false);
                } catch (Exception e) {
                    System.out.println("Can't load new window, go another way...");
                    System.out.println("LoginButton was activated");
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SecondPage.fxml"));
                    Parent root3 = fxmlLoader.load();

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.hide();
                    stage.setTitle("Second Page");
                    stage.setScene(new Scene(root3));
                    stage.show();

                }
            }
            else {
                if(LibrarianMode){
                    try {

                        System.out.println("LoginButton was activated");
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SecondPageLibrarian.fxml"));
                        Parent root2 = fxmlLoader.load();

                        //parsing name to second controller
                        SecondPageLibrarian secondPageLibrarian = fxmlLoader.getController();
                        secondPageLibrarian.function(NameField.getText());

                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.hide();
                        stage.setTitle("Second Page Librarian");
                        stage.setScene(new Scene(root2));
                        stage.show();
                        stage.setResizable(false);
                    } catch (Exception e) {
                        System.out.println("Can't load new window, go another way...");
                        System.out.println("LoginButton was activated");
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SecondPageLibrarian.fxml"));
                        Parent root3 = fxmlLoader.load();

                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.hide();
                        stage.setTitle("Second Page");
                        stage.setScene(new Scene(root3));
                        stage.show();

                    }
                }
                else{
                    try {

                        System.out.println("LoginButton was activated");
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SecondPageStudent.fxml"));
                        Parent root3 = fxmlLoader.load();
                        //parsing name to second controller
                        SecondPageStudent secondPageStudent = fxmlLoader.getController();
                        secondPageStudent.function(NameField.getText());


                        //transmitor class for trasmitting data to another controller throw the buffer
                        Transmitor tr = Transmitor.getInstance();
                        tr.SetName(NameField.getText());

                        ///
                        String query = "SELECT id from login_users where user_name = ?";
                        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1,NameField.getText());
                        ResultSet resultSet = preparedStatement.executeQuery();
                        String DBID ="0";
                                while (resultSet.next()){
                                    DBID = resultSet.getString(1);
                                }
                                id = DBID;
                        ///
                        tr.setID(id);
                        //

                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.hide();
                        stage.setTitle("Second Page");
                        stage.setScene(new Scene(root3));
                        stage.show();
                        stage.setResizable(false);
                    } catch (Exception e) {
                        //System.out.println("Can't load new window");
                        e.printStackTrace();
                    }
                }

            }
        }

    }

    private void ComControllers(FXMLLoader fxmlLoader) throws SQLException {
        SecondPage secondPage = fxmlLoader.getController();
        secondPage.function(NameField.getText());
    }

    public void RegisterHandle(MouseEvent event) throws IOException {
        System.out.println("RegistrationButton was activated");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegWindow.fxml"));
        Parent root2 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Registration");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(new Scene(root2));
        stage.show();



    }


    ///Admin functions
    public void AdminMode(MouseEvent event) {
        // if button pressed turn off verification
        System.out.println("Admin mode activated");
        AdminModeButton.setStyle("-fx-background-color: green;-fx-background-radius: 30");
        admin = true;
    }
    @FXML
    //Refresh Admin mode
    void R(KeyEvent event) {
        // if button pressed turn on verification
        System.out.println("Admin mode deactivated");
        AdminModeButton.setStyle("-fx-background-color: red; -fx-background-radius: 30");
        admin = false;
    }
    ///
}
