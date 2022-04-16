package ba.unsa.etf.rpr.projekat.Controllers;

import ba.unsa.etf.rpr.projekat.Beans.AdvertismentModel;
import ba.unsa.etf.rpr.projekat.Beans.User;
import com.jfoenix.controls.JFXDecorator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class LoginScreenController {
    public AnchorPane sideBar;
    private Stage stage;
    private double x = 0, y = 0;
    private AdvertismentModel advertismentModel = AdvertismentModel.getInstance();

    @FXML
    private TextField username;
    @FXML
    private TextField password;

    public void initialize(){
        screenDragSetUp();
    }

    @FXML
    private void logInAction(ActionEvent actionEvent) {
        User currentUser = advertismentModel.getAllUsers().stream()
                .filter(user -> user.getUsername().equals(username.getText()))
                .findAny()
                .orElse(null);
        if(currentUser != null && currentUser.getPassword().equals(password.getText())){
            username.getStyleClass().removeAll("t-field-invalid-input");
            username.getStyleClass().add("t-field");
            password.getStyleClass().removeAll("t-field-invalid-input");
            password.getStyleClass().add("t-field");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HomeScreen.fxml"));
            HomeScreenController ctrl = new HomeScreenController();
            ctrl.setCurrentUser(currentUser);
            ctrl.setAdvertismentModel(this.advertismentModel);
            loader.setController(ctrl);
            Parent root = null;
            try {
                Stage secondaryStage = new Stage();
                root = loader.load();
                secondaryStage.setTitle("Home screen");

                JFXDecorator decorator = new JFXDecorator(secondaryStage, root);
                Scene scene = new Scene(decorator);

                secondaryStage.setScene(scene);
                ctrl.setStage(secondaryStage);
                this.stage.hide();
                secondaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            username.getStyleClass().removeAll("t-field");
            username.getStyleClass().add("t-field-invalid-input");
            password.getStyleClass().removeAll("t-field");
            password.getStyleClass().add("t-field-invalid-input");
        }
    }
    @FXML
    private void exitAction(ActionEvent actionEvent) {
        System.exit(0);
    }
    @FXML
    private void createAccountAction(ActionEvent actionEvent){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterScreen.fxml"));
        RegisterScreenController ctrl = new RegisterScreenController();
        ctrl.setAdvertismentModel(this.advertismentModel);
        loader.setController(ctrl);
        Parent root = null;
        try {
            Stage secondaryStage = new Stage();
            root = loader.load();
            secondaryStage.setTitle("Register");

            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            secondaryStage.initStyle(StageStyle.TRANSPARENT);
            secondaryStage.setScene(scene);
            ctrl.setStage(secondaryStage);
            secondaryStage.show();
            this.stage.hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
