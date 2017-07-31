package sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class PodcastListDataTest {
  PodcastListData podcastListData = new PodcastListData("testData/PodcastListDataTest");

  @Before
  public void setUp () throws Exception {
  }

  @Test
  public void loadITunesData () throws Exception {
    List<PodcastListItem> mockData = new ArrayList<>();
    List<PodcastListItem> iTunesData = podcastListData.getITunesData();

    PodcastListItem item1 = new PodcastListItem();
    PodcastListItem item2 = new PodcastListItem();
    PodcastListItem item3 = new PodcastListItem();

    item1.setTitle("History of Rome");
    item2.setTitle("Levar Burton Reads");
    item3.setTitle("OWASP 24/7");

    item1.setURL("http://historyofrome.libsyn.com/rss/");
    item2.setURL("https://rss.art19.com/levar-burton-reads");
    item3.setURL("https://feeds.soundcloud.com/users/soundcloud:users:63303345/sounds.rss");

    mockData.add(item1);
    mockData.add(item2);
    mockData.add(item3);

    assertThat(iTunesData.get(0).getTitle(), equalTo(mockData.get(0).getTitle()));
    assertThat(iTunesData.get(1).getTitle(), equalTo(mockData.get(1).getTitle()));
    assertThat(iTunesData.get(2).getTitle(), equalTo(mockData.get(2).getTitle()));
  }
}