package sample;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsNot;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.framework.junit.ApplicationTest;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.hamcrest.CoreMatchers.*;


import java.util.ArrayList;
import java.util.List;
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

  Stage testStage;

  @BeforeClass
  public static void setUpClass () throws Exception {
    ApplicationTest.launch(Main.class);
  }

  @Override
  public void start (Stage stage) {
    testStage = stage;
    testStage.show();
  }

  @Test
  public void testOnUserClickCanceliTunesPodcastPopup () throws Exception {
    List<String> podcastNameList = new ArrayList<>();
    podcastNameList.add("Levar Burton Reads");
    podcastNameList.add("OWASP 24/7");

    clickOn("#menuBarFile").clickOn("#menuBarFileNew");
    clickOn(500, 300);
    write("http://historyofrome.libsyn.com/rss/");
    clickOn(800, 375);
    ListView list = (ListView) GuiTest.find("#mainVBox #podcastList");
    assertThat(list.getItems().toString(), equalTo(podcastNameList.toString()));
  }

  @Test
  public void testOnUserClickAddiTunesPodcastPopup () throws Exception {
    List<String> podcastNameList = new ArrayList<>();
    podcastNameList.add("Levar Burton Reads");
    podcastNameList.add("OWASP 24/7");
    podcastNameList.add("The History of Rome");

    clickOn("#menuBarFile").clickOn("#menuBarFileNew");
    clickOn(500, 300);
    write("http://historyofrome.libsyn.com/rss/");
    clickOn(900, 375);
    ListView list = (ListView) GuiTest.find("#mainVBox #podcastList");
    assertThat(list.getItems().toString(), equalTo(podcastNameList.toString()));
  }
}