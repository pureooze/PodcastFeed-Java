package sample;

import javafx.application.Application;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

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