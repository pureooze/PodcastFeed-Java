package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PodcastPlayer extends AnchorPane implements Initializable {

  @Override
  public void initialize (URL initURL, ResourceBundle rb) {

  }

  public PodcastPlayer () {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
      "podcast-player.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    try {
      fxmlLoader.load();
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

}
