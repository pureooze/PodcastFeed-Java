package sample;

import episodeList.EpisodeListEntry;

import java.util.List;

public interface rssFeed {
  public String getTitle ();

  public String getURL ();

  public String getAuthor ();

  public List<EpisodeListEntry> getAllEpisodes ();
}
