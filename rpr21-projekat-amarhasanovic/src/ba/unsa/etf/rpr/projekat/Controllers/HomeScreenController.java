package ba.unsa.etf.rpr.projekat.Controllers;

import ba.unsa.etf.rpr.projekat.Beans.Advertisment;
import ba.unsa.etf.rpr.projekat.Beans.AdvertismentModel;
import ba.unsa.etf.rpr.projekat.Beans.User;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HomeScreenController {
    private Stage stage;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private TilePane tilePane;
    @FXML
    private GridPane topBar;
    @FXML
    private SplitMenuButton usernameSMB;
    private Button btnSelektovanaSlika;
    private double x = 0, y = 0;
    private User currentUser;
    private AdvertismentModel advertismentModel;

    @FXML
    public void initialize(){
        try {
            screenDragSetUp();
            sideBarSetUp();
            usernameSMB.setText(currentUser.getUsername());
            setTilePane();
//            tilePane.getChildren().clear();
//            tilePane.getChildren().add(advertisment("demand"));
//            tilePane.getChildren().add(advertisment("offer"));
//            tilePane.getChildren().add(advertisment("demand"));
//            tilePane.getChildren().add(advertisment("offer"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void screenDragSetUp() {
        topBar.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        topBar.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });
    }
    private VBox advertisment(Advertisment advertisment) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Advertisment.fxml"));
        AdvertismentController ctrl = null;
        ctrl = new AdvertismentController(advertisment);
        loader.setController(ctrl);
        return loader.load();
    }
    private void setTilePane() throws IOException {
        tilePane.getChildren().clear();
        for (Advertisment ad: advertismentModel.getAllAdvertisment()) {
            tilePane.getChildren().add(advertisment(ad));
        }
    }
    private void sideBarSetUp() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/fxml/DrawerContent.fxml"));
        drawer.setSidePane(anchorPane);
        HamburgerBackArrowBasicTransition burgerTask = new HamburgerBackArrowBasicTransition(hamburger);
        burgerTask.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            burgerTask.setRate(burgerTask.getRate() * (-1));
            burgerTask.play();
            if(drawer.isOpened()){
                drawer.close();
            }else{
                drawer.open();
            }
        });
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setCurrentUser(User user) { this.currentUser = user; }
    public void setAdvertismentModel(AdvertismentModel advertismentModel){
        this.advertismentModel = advertismentModel;
    }

    @FXML
    private void logOutAction (ActionEvent actionEvent){
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
    @FXML
    private void newAddAction (ActionEvent actionEvent){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NewAddForm.fxml"));
        NewAddFormController ctrl = new NewAddFormController();
        loader.setController(ctrl);
        Parent root = null;
        try {
            Stage secondaryStage = new Stage();
            root = loader.load();
            secondaryStage.setTitle("Add screen");

            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            secondaryStage.initStyle(StageStyle.TRANSPARENT);
            secondaryStage.setScene(scene);

            secondaryStage.setScene(scene);
            ctrl.setStage(secondaryStage);
            ctrl.setCurrentUser(this.currentUser);
            secondaryStage.show();
            secondaryStage.setOnHiding(windowEvent -> {
                if(ctrl.getNewAdvertisment() != null){
                    Advertisment newAdvertisment = ctrl.getNewAdvertisment();
                    newAdvertisment.setId(advertismentModel.getNextAdvertismentId());
                    this.advertismentModel.addNewAdvertisment(newAdvertisment);
                }
                try {
                    setTilePane();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
