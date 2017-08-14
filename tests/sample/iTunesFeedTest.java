package sample;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class iTunesFeedTest {
  private iTunesFeed feed;

  @Before
  public void setUp () throws Exception {

  }

  @Test
  public void getTitle () throws Exception {
    feed = new iTunesFeed("http://historyofrome.libsyn.com/rss/");
    assertThat(feed.getTitle(), equalTo("The History of Rome"));

    feed = new iTunesFeed("https://rss.art19.com/levar-burton-reads");
    assertThat(feed.getTitle(), equalTo("LeVar Burton Reads"));

    feed = new iTunesFeed("https://feeds.soundcloud.com/users/soundcloud:users:63303345/sounds.rss");
    assertThat(feed.getTitle(), equalTo("OWASP 24/7"));
  }

  @Test
  public void getURL () throws Exception {
    feed = new iTunesFeed("http://historyofrome.libsyn.com/rss/");
    assertThat(feed.getURL(), equalTo("http://historyofrome.libsyn.com/rss/"));

    feed = new iTunesFeed("https://rss.art19.com/levar-burton-reads");
    assertThat(feed.getURL(), equalTo("https://rss.art19.com/levar-burton-reads"));

    feed = new iTunesFeed("https://feeds.soundcloud.com/users/soundcloud:users:63303345/sounds.rss");
    assertThat(feed.getURL(), equalTo("https://feeds.soundcloud.com/users/soundcloud:users:63303345/sounds.rss"));
  }

  @Test
  public void getAuthor () throws Exception {
    feed = new iTunesFeed("http://historyofrome.libsyn.com/rss/");
    assertThat(feed.getAuthor(), equalTo("Mike Duncan"));

    feed = new iTunesFeed("https://rss.art19.com/levar-burton-reads");
    assertThat(feed.getAuthor(), equalTo("LeVar Burton and Stitcher"));

    feed = new iTunesFeed("https://feeds.soundcloud.com/users/soundcloud:users:63303345/sounds.rss");
    assertThat(feed.getAuthor(), equalTo("OWASP 24/7"));
  }

  @Test
  public void getAllEpisodes () throws Exception {
    List<EpisodeListEntry> episodes;
    feed = new iTunesFeed("http://historyofrome.libsyn.com/rss/");
    episodes = feed.getAllEpisodes();
    assertThat(episodes.get(0).getName(), equalTo("The Storm Before The Storm: Chapter 1- The Beasts of Italy"));
    assertThat(episodes.get(1).getName(), equalTo("Revolutions Launch"));
    assertThat(episodes.get(2).getName(), equalTo("Update- One Year Later"));

    feed = new iTunesFeed("https://rss.art19.com/levar-burton-reads");
    episodes = feed.getAllEpisodes();
    assertThat(episodes.get(episodes.size() - 1).getName(), equalTo("Introducing… LeVar Burton Reads"));
    assertThat(episodes.get(episodes.size() - 2).getName(), equalTo("Episode 1: \"Kin\" by Bruce McAllister"));
    assertThat(episodes.get(episodes.size() - 3).getName(), equalTo("Episode 2: \"The Lighthouse Keeper\" by Daisy Johnson"));
  }

}