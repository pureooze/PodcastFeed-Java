package episodeList;


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
  private TableView<EpisodeListEntry> episodeList;

  private ObservableList<EpisodeListEntry> episodeListContents;
  private TableColumn title;
  private TableColumn date;
  private TableColumn type;
  private TableColumn URL;


  @Override
  public void initialize (URL initURL, ResourceBundle rb) {
    episodeList.setItems(episodeListContents);

    episodeList.getColumns().add(title);
    episodeList.getColumns().add(date);
    episodeList.getColumns().add(type);
    episodeList.getColumns().add(URL);
    episodeList.setPlaceholder(new Label("No Episodes Found"));
  }

  public EpisodeList () {
    episodeListContents = FXCollections.observableArrayList();

    title = new TableColumn<>("Title");
    title.setMinWidth(250);
    title.setCellValueFactory(new PropertyValueFactory<>("name"));

    date = new TableColumn<>("Date");
    date.setMinWidth(250);
    date.setCellValueFactory(new PropertyValueFactory<>("date"));

    type = new TableColumn<>("Type");
    type.setMinWidth(100);
    type.setCellValueFactory(new PropertyValueFactory<>("type"));

    URL = new TableColumn<>("URL");
    URL.setMinWidth(250);
    URL.setCellValueFactory(new PropertyValueFactory<>("URL"));

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

    for (EpisodeListEntry episode : episodeList.getItems()) {
      episodes.add(episode.getName());
    }

    return episodes;
  }

  public void setEpisodes (List<EpisodeListEntry> episodes) {
    episodeListContents.clear();

    for (EpisodeListEntry episode : episodes) {
      episodeListContents.add(episode);
    }

    episodeList.setItems(episodeListContents);
  }

}
