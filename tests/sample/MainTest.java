package sample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import javafx.application.Application;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsNot;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.hamcrest.CoreMatchers.*;


import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.loadui.testfx.GuiTest.find;
import static org.loadui.testfx.GuiTest.waitUntil;
import static org.loadui.testfx.controls.impl.VisibleNodesMatcher.visible;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.TextFlowMatchers.hasText;
import static org.testfx.util.WaitForAsyncUtils.waitFor;

import org.loadui.testfx.controls.impl.VisibleNodesMatcher;

public class MainTest extends ApplicationTest {

  private Gson gson;
  private PodcastListEntry podcastEntry;
  Parent mainNode;


  @Override
  public void start (Stage stage) throws Exception {
    mainNode = FXMLLoader.load(Main.class.getResource("sample.fxml"));
    stage.setScene(new Scene(mainNode));
    stage.show();
    stage.toFront();
  }

  @Before
  public void setUp () throws Exception {
    gson = new Gson();
    JsonReader reader = new JsonReader(new FileReader("data/PodcastListLoader/iTunes.json"));
    podcastEntry = gson.fromJson(reader, PodcastListEntry.class);
  }

  @After
  public void tearDown () throws Exception {
    gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(podcastEntry);
    FileWriter writer = new FileWriter("data/PodcastListLoader/iTunes.json");
    writer.write(json);
    writer.close();
    FxToolkit.hideStage();
    release(new KeyCode[]{});
    release(new MouseButton[]{});
  }

  @Test
  public void testOn_UserClick_CanceliTunesPodcastPopup () throws Exception {
    List<String> podcastNameList = new ArrayList<>();
    podcastNameList.add("The History of Rome");
    podcastNameList.add("LeVar Burton Reads");

    clickOn("#menuBarFile").clickOn("#menuBarFileNew");
    clickOn(500, 300);
    write("https://feeds.soundcloud.com/users/soundcloud:users:63303345/sounds.rss");
    clickOn(800, 375);
    ListView list = (ListView) GuiTest.find("#mainVBox #podcastList");
    assertThat(list.getItems().toString(), equalTo(podcastNameList.toString()));
  }

  @Test
  public void testOn_UserClick_AddiTunesPodcastPopup () throws Exception {
    List<String> podcastNameList = new ArrayList<>();
    podcastNameList.add("The History of Rome");
    podcastNameList.add("LeVar Burton Reads");
    podcastNameList.add("OWASP 24/7");

    clickOn("#menuBarFile").clickOn("#menuBarFileNew");
    clickOn(500, 300);
    write("https://feeds.soundcloud.com/users/soundcloud:users:63303345/sounds.rss");
    clickOn(900, 375);
    ListView list = (ListView) GuiTest.find("#mainVBox #podcastList");
    assertThat(list.getItems().toString(), equalTo(podcastNameList.toString()));
  }

  @Test
  public void testOn_UserClick_AddiTunesPodcastPopup_WithDuplicateEntry () throws Exception {
    List<String> podcastNameList = new ArrayList<>();
    podcastNameList.add("The History of Rome");
    podcastNameList.add("LeVar Burton Reads");

    clickOn("#menuBarFile").clickOn("#menuBarFileNew");
    clickOn(500, 300);
    write("http://historyofrome.libsyn.com/rss/");
    clickOn(900, 375);
    ListView list = (ListView) GuiTest.find("#mainVBox #podcastList");
    assertThat(list.getItems().toString(), equalTo(podcastNameList.toString()));
  }

  //EPISODE LIST TESTS
  @Test
  public void testOn_Startup_Is_EpisodeList_Empty () throws Exception {
    List<String> emptyList = new ArrayList<String>();
    TableView table = (TableView) GuiTest.find("#mainVBox #episodeList");
    assertThat(table.getItems().toString(), equalTo(emptyList.toString()));
  }

  @Test
  public void testOn_User_Click_Podcast_Display_Episodes () throws Exception {
    List<String> episodes = new ArrayList<String>();
    List<Integer> selectedIndicies = new ArrayList<Integer>();
    List<Integer> mockIndicies = new ArrayList<Integer>();
    List<String> currentPodcast = new ArrayList<>();
    List<String> mockCurrentPodcast = new ArrayList<>();

    mockIndicies.add(0);
    mockCurrentPodcast.add("The History of Rome");
    episodes.add("The Storm Before The Storm: Chapter 1- The Beasts of Italy");
    episodes.add("001- In the Beginning");

    ListView list = (ListView) GuiTest.find("#mainVBox #podcastList");
    list.getSelectionModel().selectFirst();
    selectedIndicies = list.getSelectionModel().getSelectedIndices();
    currentPodcast = list.getSelectionModel().getSelectedItems();
    assertThat(selectedIndicies.toString(), equalTo(mockIndicies.toString()));
    assertThat(currentPodcast.toString(), equalTo(mockCurrentPodcast.toString()));

    TableView table = (TableView) GuiTest.find("#mainVBox #episodeList");
    int episodeCount = table.getItems().size() - 1;

    episodeListEntry firstEntry = (episodeListEntry) table.getItems().get(0);
    episodeListEntry lastEntry = (episodeListEntry) table.getItems().get(episodeCount);
    assertThat(firstEntry.getName(), equalTo(episodes.get(0).toString()));
    assertThat(lastEntry.getName(), equalTo(episodes.get(1).toString()));
  }
}