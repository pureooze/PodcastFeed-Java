package sample;


import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

  @FXML
  private GridPane PodcastListPane;

  @FXML
  private PodcastList podcastList;

  @FXML
  private EpisodeList episodeList;

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

  @Override
  public void initialize (URL url, ResourceBundle rb) {
    VBox vbox = (VBox) podcastList.getChildren().get(0);
    ListView<String> podcasts = (ListView<String>) vbox.getChildren().get(1);
    podcasts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed (ObservableValue<? extends String> observable, String oldValue, String newValue) {
        episodeList.setEpisodes(podcastList.getEpisodesForCurrentPodcast());
      }
    });
  }

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
    dialog.setTitle("Add Podcast");
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
    grid.add(new Label("Enter Podcast URL:"), 0, 0);
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
