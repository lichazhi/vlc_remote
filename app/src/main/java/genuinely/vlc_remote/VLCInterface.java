package genuinely.vlc_remote;

import org.simpleframework.xml.Path;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface VLCInterface {

    //Query Playlist
    @GET("playlist.xml")
    Call<Playlist> loadPlaylist(@Header("Authorization") String credentials);

    //Play and Stop
    @GET("{ipAddress}/requests/status.xml")
    Call<Status> getStatus(@Header("Authorization") String credentials,
                           @Path("ipAddress") String ipAddress,
                           @Query("command") String command,
                           @Query("id") int id
    );
}

