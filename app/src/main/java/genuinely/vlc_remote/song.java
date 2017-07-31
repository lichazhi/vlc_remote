package genuinely.vlc_remote;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;

@Element(name = "leaf")
@Path("node/node")
public class song {

    @Attribute(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
