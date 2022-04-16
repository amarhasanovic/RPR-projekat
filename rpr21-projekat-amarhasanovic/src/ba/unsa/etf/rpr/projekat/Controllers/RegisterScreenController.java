package ba.unsa.etf.rpr.projekat.Controllers;

import ba.unsa.etf.rpr.projekat.Beans.AdvertismentModel;
import ba.unsa.etf.rpr.projekat.Beans.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class RegisterScreenController {
    public AnchorPane sideBar;
    private Stage stage;
    private double x = 0, y = 0;
    AdvertismentModel advertismentModel;

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField passwordConfirmation;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField email;
    @FXML
    private TextField address;


    public void initialize(){
        screenDragSetUp();

    }

    private void screenDragSetUp() {
        sideBar.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        sideBar.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });
    }

    @FXML
    private void exitAction(ActionEvent actionEvent) {
        System.exit(0);
    }
    @FXML
    private void registerAction(ActionEvent actionEvent) {
        if(formValidation()){
            int id = advertismentModel.getNextUserId();
            User newUser = new User(id, firstName.getText(), lastName.getText(), username.getText(), email.getText(), password.getText());
            advertismentModel.registerNewUser(newUser);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginScreen.fxml"));
            LoginScreenController ctrl = new LoginScreenController();
            loader.setController(ctrl);
            Parent root = null;
            try {
                Stage secondaryStage = new Stage();
                root = loader.load();
                secondaryStage.setTitle("Login screen");

                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                secondaryStage.initStyle(StageStyle.TRANSPARENT);
                secondaryStage.setScene(scene);

                secondaryStage.setScene(scene);
                ctrl.setStage(secondaryStage);
                this.stage.hide();
                secondaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private boolean usernameValidation(){
        if(username.getText().trim().isEmpty())
            return false;
        User currentUser = advertismentModel.getAllUsers().stream()
                .filter(user -> user.getUsername().equals(username.getText()))
                .findAny()
                .orElse(null);
        if(currentUser != null)
            return false;
        return true;
    }
    private boolean formValidation(){
        boolean valid = true;
        if(firstName.getText().trim().isEmpty()){
            firstName.getStyleClass().removeAll("t-field");
            firstName.getStyleClass().add("t-field-invalid-input");
            valid= false;
        }else{
            firstName.getStyleClass().removeAll("t-field-invalid-input");
            firstName.getStyleClass().add("t-field");
        }
        if(lastName.getText().trim().isEmpty()){
            lastName.getStyleClass().removeAll("t-field");
            lastName.getStyleClass().add("t-field-invalid-input");
            valid= false;
        }else{
            lastName.getStyleClass().removeAll("t-field-invalid-input");
            lastName.getStyleClass().add("t-field");
        }
        if(!usernameValidation()){
            username.getStyleClass().removeAll("t-field");
            username.getStyleClass().add("t-field-invalid-input");
            valid= false;
        }else{
            username.getStyleClass().removeAll("t-field-invalid-input");
            username.getStyleClass().add("t-field");
        }
        if(email.getText().trim().isEmpty()){
            email.getStyleClass().removeAll("t-field");
            email.getStyleClass().add("t-field-invalid-input");
            valid= false;
        }else {
            email.getStyleClass().removeAll("t-field-invalid-input");
            email.getStyleClass().add("t-field");
        }
        if(password.getText().trim().isEmpty()){
            password.getStyleClass().removeAll("t-field");
            password.getStyleClass().add("t-field-invalid-input");
            valid= false;
        }else{
            password.getStyleClass().removeAll("t-field-invalid-input");
            password.getStyleClass().add("t-field");
        }
        if(passwordConfirmation.getText().isEmpty() || !password.getText().equals(passwordConfirmation.getText())){
            passwordConfirmation.getStyleClass().removeAll("t-field");
            passwordConfirmation.getStyleClass().add("t-field-invalid-input");
            valid= false;
        }else{
            passwordConfirmation.getStyleClass().removeAll("t-field-invalid-input");
            passwordConfirmation.getStyleClass().add("t-field");
        }
        return valid;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setAdvertismentModel(AdvertismentModel advertismentModel){
        this.advertismentModel = advertismentModel;
    }
}
