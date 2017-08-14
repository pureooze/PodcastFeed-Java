package sample;

import com.rometools.rome.feed.module.DCModuleImpl;
import com.rometools.rome.feed.synd.SyndEnclosureImpl;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import org.jdom2.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class iTunesFeed implements rssFeed {
  private String title;
  private String URL;
  private String author;
  private List<SyndEntry> entries;
  private List<EpisodeListEntry> episodes;
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
  public List<EpisodeListEntry> getAllEpisodes () {
    return episodes;
  }

  private void loadFeed (String URL) {
    feed = new FeedFetcher(URL);
    title = feed.getTitle();
    author = feed.getForeignElementValue("author");
    episodes = getEntriesTitles();
  }

  private List<EpisodeListEntry> getEntriesTitles () {
    List<EpisodeListEntry> episodeListEntryList = new ArrayList<>();
    entries = feed.getEntries();
    //https://rss.art19.com/levar-burton-reads

    for (final Iterator iter = entries.iterator(); iter.hasNext(); ) {
      final SyndEntry entry = (SyndEntry) iter.next();
      EpisodeListEntry episodeListEntry = new EpisodeListEntry();
      episodeListEntry.setName(entry.getTitle());
      episodeListEntry.setDescription(entry.getDescription().getValue());

      final Element foreign = (Element) entry.getForeignMarkup().get(3);
      System.out.println(foreign.getText());

//      for (final Iterator foreignIterator = entry.getForeignMarkup().iterator(); foreignIterator.hasNext(); ) {
//        final Element foreign = (Element) foreignIterator.next();
//        System.out.println(foreign.get);
//      }

      for (final Iterator enclosureIterator = entry.getEnclosures().iterator(); enclosureIterator.hasNext(); ) {
        final SyndEnclosureImpl enclosure = (SyndEnclosureImpl) enclosureIterator.next();
        episodeListEntry.setType(enclosure.getType());
        episodeListEntry.setURL(enclosure.getUrl());
      }

      for (final Iterator moduleIter = entry.getModules().iterator(); moduleIter.hasNext(); ) {
        final DCModuleImpl module = (DCModuleImpl) moduleIter.next();
        episodeListEntry.setDate(module.getDate().toString());
      }

      episodeListEntryList.add(episodeListEntry);
    }

    return episodeListEntryList;

  }
}
