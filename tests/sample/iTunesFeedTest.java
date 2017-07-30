package sample;

import com.sun.istack.internal.Nullable;
import org.junit.Before;
import org.junit.Test;

import javax.lang.model.type.NullType;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
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
  }

}