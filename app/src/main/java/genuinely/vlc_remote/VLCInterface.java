package genuinely.vlc_remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface VLCInterface {

    @GET("playlist.xml")
    Call<Playlist> loadPlaylist(@Header("Authorization") String credentials);

}

