package sample;

import java.util.List;

public class iTunesFeed implements rssFeed {
  private String title;
  private String URL;
  private String author;
  private List<String> episodes;
  private FeedFetcher feed;

  public iTunesFeed (String URL) {
    loadFeed(URL);
    this.URL = URL;
  }

  @Override
  public String getTitle () {
    return title;
  }

  @Override
  public String getURL () {
    return URL;
  }

  @Override
  public String getAuthor () {
    return author;
  }

  @Override
  public List<String> getAllEpisodes () {
    return episodes;
  }

  private void loadFeed (String URL) {
    feed = new FeedFetcher(URL);
    title = feed.getTitle();
    author = feed.getAuthor();
  }
}
