package genuinely.vlc_remote;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root(name="rss", strict=false)
public class RSSFeed {

//    <?xml version="1.0" encoding="utf-8" standalone="yes"?>
//    <node ro="rw" name="Undefined" id="1">
//    <node ro="ro" name="Playlist" id="2">
//    <leaf ro="rw" name="I'm The One ft. Justin Bieber, Quavo, Chance The Rapper, Lil Wayne" id="4" duration="292" uri="file:///Users/neilyeung/Downloads/%28lyrics%29_DJ_Khaled_-_I%27m_The_One_ft._Justin_Bieber__Quavo__Chance_The_Rapper__Lil_Wayne.mp3"/>
//    <leaf ro="rw" name="[60fps Full風] 千本桜 Senbonzakura \&quot;One Thousand Cherry Trees\&quot;- Hatsune Miku 初音ミク DIVA English Romaji" id="5" duration="245" uri="file:///Users/neilyeung/Downloads/%5B60fps_Full%E9%A2%A8%5D_%E5%8D%83%E6%9C%AC%E6%A1%9C_Senbonzakura_-One_Thousand_Cherry_Trees--_Hatsune_Miku_%E5%88%9D%E9%9F%B3%E3%83%9F%E3%82%AF_DIVA_English_Romaji.mp3" current="current"/>
//    </node>
//    <node ro="ro" name="Media Library" id="3"></node>
//    </node>

    @Element(name = "node")
    @Path("node")
    private List<song> playList;

    public List<song> getPlaylist() {
        return playList;
    }

    public void setPlayList(List<song> playList) {this.playList = playList;}
}
