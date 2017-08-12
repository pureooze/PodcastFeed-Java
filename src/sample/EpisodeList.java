package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EpisodeList extends AnchorPane implements Initializable {

  @FXML
  private TableView<episodeListEntry> episodeList;

  private ObservableList<episodeListEntry> episodeListContents;
  private TableColumn title;


  @Override
  public void initialize (URL url, ResourceBundle rb) {
    episodeList.setItems(episodeListContents);

    episodeList.getColumns().add(title);
    episodeList.setPlaceholder(new Label("No Episodes Found"));
  }

  public EpisodeList () {
    episodeListContents = FXCollections.observableArrayList();

    title = new TableColumn<>("Title");
    title.setMinWidth(250);
    title.setCellValueFactory(new PropertyValueFactory<>("name"));

    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
      "episode-list.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    try {
      fxmlLoader.load();
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  public List<String> getEpisodes () {
    List<String> episodes = new ArrayList<String>();

    for (episodeListEntry episode : episodeList.getItems()) {
      episodes.add(episode.getName());
    }

    return episodes;
  }

  public void setEpisodes (List<String> episodes) {

    for (String name : episodes) {
      episodeListContents.add(new episodeListEntry(name));
    }

    episodeList.setItems(episodeListContents);
  }

}
