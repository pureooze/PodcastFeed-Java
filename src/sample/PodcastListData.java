package sample;

import com.rometools.rome.feed.atom.Feed;
import com.rometools.rome.feed.synd.SyndEntry;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PodcastListData {
  private List<PodcastListItem> iTunesData;
  private String configPath;

  public PodcastListData (String configPath) {
    this.configPath = configPath;
    iTunesData = new ArrayList<>();
    loadSavedPodcasts();
  }

  private void loadSavedPodcasts () {
    loadLocalPodcastXML(iTunesData, "iTunes.xml");
  }

  private void loadLocalPodcastXML (List<PodcastListItem> dataList, String fileName) {
    try {
      File inputFile = new File(configPath + "/" + fileName);
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();

      StringBuilder xmlStringBuilder = new StringBuilder();
      xmlStringBuilder.append("<?xml version=\" 1.0 \"?> <class> </class>");
      ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
      Document document = builder.parse(inputFile);
      Element root = document.getDocumentElement();
      NodeList nList = document.getElementsByTagName("podcast");

      for (int index = 0; index < nList.getLength(); index++) {
        PodcastListItem dataItem = new PodcastListItem();
        Node node = nList.item(index);

        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element element = (Element) node;
          dataItem.setTitle(element.getElementsByTagName("title").item(0).getTextContent());
          dataItem.setURL(element.getElementsByTagName("url").item(0).getTextContent());
          dataList.add(dataItem);
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public List<PodcastListItem> getITunesData () {
    return iTunesData;
  }
}
