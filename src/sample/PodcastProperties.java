package sample;

import java.util.List;

public class PodcastProperties {
  private String title;
  private String URL;
  private List<String> episodeList;

  public PodcastProperties () {

  }

  public void setTitle (String title) {
    this.title = title;
  }

  public void setURL (String URL) {
    this.URL = URL;
  }

  public String getTitle () {
    return title;
  }

  public String getURL () {
    return URL;
  }

  public void setEpisodeList (List<String> episodes) {
    episodeList = episodes;
  }
}
