package podcastList;

import episodeList.EpisodeListEntry;

import java.util.List;

public class PodcastListEntry {
  private List<PodcastProperties> podcasts;

  public List<PodcastProperties> getProperties () {
    return podcasts;
  }

  public void addPodcast (String title, String URL, List<EpisodeListEntry> episodes) {
    PodcastProperties prop = new PodcastProperties();
    prop.setTitle(title);
    prop.setURL(URL);
    prop.setEpisodeList(episodes);
    podcasts.add(prop);
  }

  public void removePodcast (int index) {
    podcasts.remove(index);
  }

  public List<EpisodeListEntry> getEpisodes (int podcastIndex) {
    return podcasts.get(podcastIndex).getEpisodeList();
  }
}
