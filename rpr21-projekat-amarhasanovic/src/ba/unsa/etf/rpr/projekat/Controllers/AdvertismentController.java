package ba.unsa.etf.rpr.projekat.Controllers;

import ba.unsa.etf.rpr.projekat.Beans.Advertisment;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AdvertismentController {
    @FXML
    private ImageView jobImageView;
    @FXML
    private JFXButton titleBtn;
    @FXML
    private Label descriptionLabel;
    private Image picture;
    private Advertisment advertisment;

    @FXML
    private void initialize(){
        jobImageView.setImage(picture);
        descriptionLabel.setText(advertisment.getDescription());
        titleBtn.setText(advertisment.getTitle());
    }
    public AdvertismentController(Advertisment advertisment) {
        this.advertisment = advertisment;
        if(advertisment.getType().equals("Offer"))
            picture = new Image("/pictures/jobOffer.png", true);
        else if(advertisment.getType().equals("Demand"))
            picture = new Image("/pictures/jobDemand.png", true);
    }
}
