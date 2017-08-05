package sample;


import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

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

  @Override
  public void initialize (URL url, ResourceBundle rb) {
    podcastData = new PodcastListLoader("data/PodcastListLoader");

    List<PodcastProperties> iTunesList = podcastData.getITunesData();
    for (PodcastProperties item : iTunesList) {
      podcastNameList.add(item.getTitle());
    }

    podcastListHeader.setText("Podcasts");
    podcastListHeader.setFont(new Font("Droid Sans", 14));
    podcastListHeader.setTooltip(new Tooltip("Hello!"));
    podcastListHeader.getTooltip().setFont(new Font(11));

    podcastList.itemsProperty().bind(listProperty);
    listProperty.set(FXCollections.observableArrayList(podcastNameList));

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

  @FXML
  private void doSomething () {
    System.out.println("The button was clicked!");
  }

  public void addiTunesPodcast (String URL) {
    iTunesFeed feed = new iTunesFeed(URL);
    saveFeedToLocalConfig(feed, "iTunes.json");
    podcastNameList.add(feed.getTitle());
    podcastList.itemsProperty().bind(listProperty);
    listProperty.set(FXCollections.observableArrayList(podcastNameList));
  }

  private void saveFeedToLocalConfig (rssFeed feed, String fileName) {
    podcastData.updateLocalPodcastData(feed, fileName);
  }

  public List<String> getPodcastNameList () {
    return podcastNameList;
  }
}
