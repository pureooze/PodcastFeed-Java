package sample;

import java.util.List;

public interface rssFeed {
  public String getTitle ();

  public String getURL ();

  public String getAuthor ();

  public List<String> getAllEpisodes ();
}
