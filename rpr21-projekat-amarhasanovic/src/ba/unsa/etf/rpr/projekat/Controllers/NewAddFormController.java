package ba.unsa.etf.rpr.projekat.Controllers;

import ba.unsa.etf.rpr.projekat.Beans.Advertisment;
import ba.unsa.etf.rpr.projekat.Beans.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NewAddFormController {
    @FXML
    private TextField titleTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TextField companyTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField priceTextFIeld;
    @FXML
    private ChoiceBox ODchoiceBox;
    @FXML
    private ImageView ODimageView;
    @FXML
    private AnchorPane sideBar;

    private Advertisment newAdvertisment = null;
    private User currentUser;
    private Stage stage;
    private double x = 0;
    private double y = 0;

    @FXML
    private void initialize(){
        screenDragSetUp();
        ODchoiceBox.getItems().add("Demand");
        ODchoiceBox.getItems().add("Offer");
        ODchoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, old, newChoice) -> {
            if(ODchoiceBox.getSelectionModel().getSelectedItem().equals("Offer"))
                ODimageView.setImage(new Image("/pictures/jobOffer.png", true));
            else if(ODchoiceBox.getSelectionModel().getSelectedItem().equals("Demand"))
                ODimageView.setImage(new Image("/pictures/jobDemand.png", true));
        });
    }

    public void setStage(Stage stage) {
        this.stage = stage;
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
    public void setCurrentUser(User currentUser){
        this.currentUser = currentUser;
    }

    @FXML
    private void cancelBtn(ActionEvent actionEvent){
        this.stage.hide();
    }
    @FXML
    private void addNewBtn(ActionEvent actionEvent){
        if(validation()){
            newAdvertisment = new Advertisment(titleTextField.getText(), descriptionTextArea.getText(), (String) ODchoiceBox.getSelectionModel().getSelectedItem(), currentUser);
            this.stage.hide();
        }
    }
    public Advertisment getNewAdvertisment(){
        return newAdvertisment;
    }
    private boolean validation(){
        boolean valid = true;
        if(titleTextField.getText().trim().isEmpty()){
            titleTextField.getStyleClass().removeAll("t-field");
            titleTextField.getStyleClass().add("t-field-invalid-input");
            valid= false;
        }else{
            titleTextField.getStyleClass().removeAll("t-field-invalid-input");
            titleTextField.getStyleClass().add("t-field");
        }
        if(companyTextField.getText().trim().isEmpty()){
            companyTextField.getStyleClass().removeAll("t-field");
            companyTextField.getStyleClass().add("t-field-invalid-input");
            valid= false;
        }else{
            companyTextField.getStyleClass().removeAll("t-field-invalid-input");
            companyTextField.getStyleClass().add("t-field");
        }
        if(addressTextField.getText().trim().isEmpty()){
            addressTextField.getStyleClass().removeAll("t-field");
            addressTextField.getStyleClass().add("t-field-invalid-input");
            valid= false;
        }else{
            addressTextField.getStyleClass().removeAll("t-field-invalid-input");
            addressTextField.getStyleClass().add("t-field");
        }
        if(phoneTextField.getText().trim().isEmpty()){
            phoneTextField.getStyleClass().removeAll("t-field");
            phoneTextField.getStyleClass().add("t-field-invalid-input");
            valid= false;
        }else{
            phoneTextField.getStyleClass().removeAll("t-field-invalid-input");
            phoneTextField.getStyleClass().add("t-field");
        }
        if(priceTextFIeld.getText().trim().isEmpty()){
            priceTextFIeld.getStyleClass().removeAll("t-field");
            priceTextFIeld.getStyleClass().add("t-field-invalid-input");
            valid= false;
        }else{
            priceTextFIeld.getStyleClass().removeAll("t-field-invalid-input");
            priceTextFIeld.getStyleClass().add("t-field");
        }
        if(ODchoiceBox.getSelectionModel().getSelectedItem() == null)
            valid = false;
        return valid;
    }
}
