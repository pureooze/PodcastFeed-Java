package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.awt.event.ActionEvent;

public class Controller {

  @FXML
  private GridPane PodcastListPane;

  public Controller () {
  }

  public Boolean ControllerTest () {
    return true;
  }

  public void populatePodcasts () {
  }

  @FXML
  public void showAddPodcastDialog () throws Exception {

    final Parent root = FXMLLoader.load(getClass().getResource("add-podcast.fxml"));
    final Scene scene = new Scene(root);
    Stage addPodcast = new Stage();

    addPodcast.initModality(Modality.APPLICATION_MODAL);
    addPodcast.setTitle("TextFX demo");
    addPodcast.setScene(scene);
    addPodcast.centerOnScreen();
    addPodcast.showAndWait();
  }
}
