package genuinely.vlc_remote;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VLCInterface {
    @GET("song")
    Call<RSSFeed> loadRSSFeed();
}

