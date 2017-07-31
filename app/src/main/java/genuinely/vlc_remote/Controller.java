package genuinely.vlc_remote;

import android.util.Log;
import android.widget.TextView;

import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class Controller implements Callback<RSSFeed> {



    static final String BASE_URL = "http://127.0.0.1:8080/requests/";
    private static final String TAG = "Controller";

    public void start() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create()).build();

        VLCInterface vlcInterface = retrofit.create(VLCInterface.class);

        Call<RSSFeed> call = vlcInterface.loadRSSFeed();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<RSSFeed> call, Response<RSSFeed> response) {

        if (response.isSuccessful()) {
            RSSFeed rss = response.body();
            for (Iterator<song> i = rss.getPlaylist().iterator(); i.hasNext();) {
                song song = i.next();
                Log.d(TAG, (("Title: " + song.getName())));
            }
        } else {
            Log.d(TAG, "error");
        }
    }

    @Override
    public void onFailure(Call<RSSFeed> call, Throwable t) {
        t.printStackTrace();
    }
}



