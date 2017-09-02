package sample;

import org.junit.Before;
import org.junit.Test;
import podcastList.PodcastListLoader;
import podcastList.PodcastProperties;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class PodcastListLoaderTest {
  PodcastListLoader podcastListLoader = new PodcastListLoader("testData/PodcastListLoaderTest");

  @Before
  public void setUp () throws Exception {
  }

  @Test
  public void loadITunesData () throws Exception {
    List<PodcastProperties> mockData = new ArrayList<>();
    List<PodcastProperties> iTunesData = podcastListLoader.getITunesData();

    PodcastProperties item1 = new PodcastProperties();
    PodcastProperties item2 = new PodcastProperties();
    PodcastProperties item3 = new PodcastProperties();

    item1.setTitle("The History of Rome");
    item2.setTitle("LeVar Burton Reads");

    item1.setURL("http://historyofrome.libsyn.com/rss/");
    item2.setURL("https://rss.art19.com/levar-burton-reads");

    mockData.add(item1);
    mockData.add(item2);

    assertThat(iTunesData.get(0).getTitle(), equalTo(mockData.get(0).getTitle()));
    assertThat(iTunesData.get(1).getTitle(), equalTo(mockData.get(1).getTitle()));
  }
}