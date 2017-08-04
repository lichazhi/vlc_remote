package genuinely.vlc_remote;

import org.simpleframework.xml.Path;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface VLCInterface {

    //Query Playlist
    @GET("playlist.xml")
    Call<Playlist> loadPlaylist(@Header("Authorization") String credentials);

    //Play and Stop
    @GET("status.xml")
    Call<Status> getStatus(@Header("Authorization") String credentials,
            @Query("command") String command,
            @Query("id") int id
    );
}

