package genuinely.vlc_remote;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root(name = "node", strict = false)

public class Playlist {

    @ElementList(entry = "leaf", inline = true)
    @Path("node")
    private List<Song> list;

    private ArrayList<Song> songArrayList;

    @Attribute(name = "ro")
    private String ro;

    @Attribute(name = "name")
    private String name;

    public List<Song> getPlaylist() {
        return list;
    }

    public void setPlayList(List<Song> pList) {this.list = pList;}
}
