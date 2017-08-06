package sample;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.util.Pair;

import java.awt.event.ActionEvent;
import java.util.Optional;

public class Controller {

  @FXML
  private GridPane PodcastListPane;

  @FXML
  private PodcastList podcastList;

  @FXML
  private Dialog<String> dialog;

  @FXML
  private ButtonType addButton;
  @FXML
  private ButtonType cancelButton;
  @FXML
  private GridPane grid;
  @FXML
  private TextField podcastURL;

  public Controller () {
  }

  public Boolean ControllerTest () {
    return true;
  }

  public void populatePodcasts () {
  }

  @FXML
  public void closeApplication () throws Exception {
    Platform.exit();
  }

  @FXML
  public void showAddPodcastDialog () throws Exception {
    Optional<String> URL = displayDialogBox();
    handleAddDialogResponse(URL);
  }

  private Optional<String> displayDialogBox () {
    // Create the Dialog Window
    dialog = new Dialog<>();
    dialog.setTitle("Add iTunes Podcast");
    dialog.setHeaderText(null);

    // Creates the "Add" button
    addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
    cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
    dialog.getDialogPane().getButtonTypes().addAll(addButton, cancelButton);

    // Create a grid pane for adding widgets to
    grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20, 10, 10, 10));
    grid.setMinHeight(100);
    grid.setMinWidth(450);

    // Creates the text input for the URL and place it on the grid pane
    podcastURL = new TextField();
    podcastURL.setPromptText("http://example.com/rss");
    podcastURL.setPrefColumnCount(40);
    grid.add(new Label("URL:"), 0, 0);
    grid.add(podcastURL, 0, 1);

    // Set add button to disabled on initialization so user cannot submit empty strings
    Node addButtonNode = dialog.getDialogPane().lookupButton(addButton);
    addButtonNode.setDisable(true);

    // Disable the "Add" button if the user clears the text input, dont allow empty string submit
    // This is a good usability practice to make it clear to the user that we expect something
    // on input.
    podcastURL.textProperty().addListener((observable, oldValue, newValue) -> {
      addButtonNode.setDisable(newValue.trim().isEmpty());
    });

    dialog.getDialogPane().setContent(grid);

    dialog.setResultConverter(dialogButton -> {
      if (dialogButton == addButton) {
        return podcastURL.getText();
      }
      return null;
    });

    return dialog.showAndWait();
  }

  private void handleAddDialogResponse (Optional<String> URL) {
    if (URL.isPresent()) {
      podcastList.addiTunesPodcast(URL.get());
    }
  }
}
