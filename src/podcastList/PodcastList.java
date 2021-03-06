package podcastList;


import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import episodeList.EpisodeListEntry;
import sample.iTunesFeed;
import sample.rssFeed;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PodcastList extends AnchorPane implements Initializable {

  @FXML
  private ListView podcastList;
  @FXML
  private Label podcastListHeader;

  private List<String> podcastNameList = new ArrayList<>();
  private ListProperty<String> listProperty = new SimpleListProperty<>();

  private PodcastListLoader podcastData;
  private List<EpisodeListEntry> episodesList;

  @Override
  public void initialize (URL url, ResourceBundle rb) {
    episodesList = new ArrayList<EpisodeListEntry>();
    podcastListHeader.setText("Podcasts");
    podcastListHeader.setFont(new Font("Droid Sans", 14));
    podcastListHeader.setTooltip(new Tooltip("Hello!"));
    podcastListHeader.getTooltip().setFont(new Font(11));


    // Create ContextMenu
    ContextMenu contextMenu = new ContextMenu();

    MenuItem removePodcastItem = new MenuItem("Remove");
    removePodcastItem.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle (ActionEvent event) {
        removePodcastEntry(podcastList.getSelectionModel().getSelectedIndices());
      }
    });

    contextMenu.getItems().addAll(removePodcastItem);

    podcastList.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
      @Override
      public void handle (ContextMenuEvent event) {
        contextMenu.show(podcastList, event.getScreenX(), event.getScreenY());
      }
    });

    podcastList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    podcastList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
      @Override
      public void changed (ObservableValue observable, Object oldValue, Object newValue) {
        episodesList = getEpisodes(podcastList.getSelectionModel().getSelectedIndex());
      }
    });

    loadPodcastList();

  }

  private void removePodcastEntry (ObservableList selectedIndices) {
    for (Object index : selectedIndices) {
      podcastData.removePodcastEntry((int) index);
      podcastNameList.remove((int) index);
    }
    refreshList();

  }

  public PodcastList () {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
      "podcast-list.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    try {
      fxmlLoader.load();
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  public void addiTunesPodcast (String URL) throws Exception {
    iTunesFeed feed = new iTunesFeed(URL);
    saveFeedToLocalConfig(feed, "iTunes.json");

    for (String name : podcastNameList) {
      if (feed.getTitle().equals(name)) {
        return;
      }
    }

    podcastNameList.add(feed.getTitle());
    refreshList();
  }

  private void saveFeedToLocalConfig (rssFeed feed, String fileName) {
    podcastData.addPodcastEntry(feed);
  }

  public List<String> getPodcastNameList () {
    return podcastNameList;
  }

  public void loadPodcastList () {
    podcastData = new PodcastListLoader("data/PodcastListLoader");

    List<PodcastProperties> iTunesList = podcastData.getITunesData();
    for (PodcastProperties item : iTunesList) {
      podcastNameList.add(item.getTitle());
    }

    refreshList();
  }

  private void refreshList () {
    podcastList.itemsProperty().bind(listProperty);
    listProperty.set(FXCollections.observableArrayList(podcastNameList));
  }

  private List<EpisodeListEntry> getEpisodes (int podcastIndex) {
    return podcastData.getEpisodeList(podcastIndex);
  }

  public List<EpisodeListEntry> getEpisodesForCurrentPodcast () {
    return episodesList;
  }
}
