package sample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PodcastListLoader {
  private List<PodcastProperties> iTunesData;
  private PodcastListEntry podcastEntry;
  private String configPath;
  private String iTunesFile;

  public PodcastListLoader (String configPath) {
    this.configPath = configPath;
    iTunesData = new ArrayList<>();
    podcastEntry = new PodcastListEntry();
    iTunesFile = "iTunes.json";
    loadSavedPodcasts();
  }

  private void loadSavedPodcasts () {
    loadLocalPodcastJSON(iTunesData, iTunesFile);
  }

  private void loadLocalPodcastJSON (List<PodcastProperties> dataList, String fileName) {
    try {
      Gson gson = new Gson();
      JsonReader reader = new JsonReader(new FileReader(configPath + "/" + fileName));
      podcastEntry = gson.fromJson(reader, PodcastListEntry.class);

      for (PodcastProperties item : podcastEntry.getProperties()) {
        iTunesData.add(item);
      }

    } catch (IOException ex) {
      System.out.println("Error reading file '" + configPath + "/" + fileName);
    }
  }

  public List<PodcastProperties> getITunesData () {
    return iTunesData;
  }

  public void addPodcastEntry (rssFeed feed) {
    podcastEntry.addPodcast(feed.getTitle(), feed.getURL(), feed.getAllEpisodes());
    updateLocalPodcastData(iTunesFile);
  }

  public void updateLocalPodcastData (String fileName) {
    try {
      Gson gson = new Gson();
      gson = new GsonBuilder().setPrettyPrinting().create();
      String json = gson.toJson(podcastEntry);
      FileWriter writer = new FileWriter(configPath + "/" + fileName);
      writer.write(json);
      writer.close();
    } catch (IOException e) {
      System.out.println("Error writing to file '" + configPath + "/" + fileName);
    }
  }

  public void removePodcastEntry (int index) {
    podcastEntry.removePodcast(index);
    updateLocalPodcastData(iTunesFile);
  }

  public List<EpisodeListEntry> getEpisodeList (int podcastIndex) {
    return podcastEntry.getEpisodes(podcastIndex);
  }
}
