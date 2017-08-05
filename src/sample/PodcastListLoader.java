package sample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PodcastListLoader {
  private List<PodcastProperties> iTunesData;
  private PodcastListEntry podcastEntry;
  private String configPath;

  public PodcastListLoader (String configPath) {
    this.configPath = configPath;
    iTunesData = new ArrayList<>();
    podcastEntry = new PodcastListEntry();
    loadSavedPodcasts();
  }

  private void loadSavedPodcasts () {
    loadLocalPodcastJSON(iTunesData, "iTunes.json");
  }

  //TODO: ADD EPISODES TO JSON
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

  public void updateLocalPodcastData (rssFeed feed, String fileName) {
    podcastEntry.addPodcast(feed.getTitle(), feed.getURL(), feed.getAllEpisodes());

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
}
