package sample;

import org.junit.Before;
import podcastList.PodcastList;

import java.net.URL;
import java.util.ResourceBundle;

public class PodcastListTest {
  private PodcastListTestClass podcastList;

  private class PodcastListTestClass extends PodcastList {
    @Override
    public void initialize (URL url, ResourceBundle rb) {
    }
  }

  @Before
  public void setUp () throws Exception {
  }

}