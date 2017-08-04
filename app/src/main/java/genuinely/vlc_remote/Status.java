package genuinely.vlc_remote;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "root")
public class Status {

    @Element(name = "information")
    private String information;

    @Element(name = "state")
    private String state;

    public String getState() {
        return state;
    }

    public String getInformation() {
        return information;
    }
}
