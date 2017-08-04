package genuinely.vlc_remote;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "leaf", strict = false)
public class Song {

    @Attribute(name="ro")
    private String ro;

    @Attribute(name = "name")
    private String name;

    @Attribute(name = "id")
    private int id;

    public Song() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
