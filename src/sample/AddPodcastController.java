package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddPodcastController implements Initializable {

  @FXML
  private TextField urlInput;

  @Override
  public void initialize (URL url, ResourceBundle rb) {
    urlInput = new TextField();
  }

  public AddPodcastController (String URL) throws Exception {
    final Parent root = FXMLLoader.load(getClass().getResource("add-podcast.fxml"));
    final Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setTitle("TextFX demo");
    stage.setScene(scene);
    stage.centerOnScreen();
    stage.setOnCloseRequest(e -> {
      e.consume();
      stage.hide();
    });

    stage.showAndWait();
  }

  public static String display () {
    return "asdsad";
  }

  public String getURL () {
    return urlInput.toString();
  }
}
