package genuinely.vlc_remote;

import android.util.Log;

import java.util.Iterator;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class Controller implements Callback<Playlist> {

    static final String BASE_URL = "http://192.168.1.79:8080/requests/";

    private static final String TAG = "Controller";

    //private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public void start() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create()).build();

        VLCInterface vlcInterface = retrofit.create(VLCInterface.class);

        //Fetch the list of songs
        Call<Playlist> call = vlcInterface.loadPlaylist(Credentials.basic("","123456"));
        //executing call asynchronously
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Playlist> call, Response<Playlist> response) {

        Log.d(TAG, "entering");

        if (response.isSuccessful()) {

            Playlist playlist = response.body();
            int s = playlist.getPlaylist().size();
            Log.d(TAG, "wow so big " + s);

            for (Iterator<Song> i = playlist.getPlaylist().iterator(); i.hasNext();) {
                Song song = i.next();
                Log.d(TAG, (("Title: " + song.getName())));
            }
        } else {
            Log.d(TAG, "error");
        }

        Log.d(TAG, "leaving");
    }

    @Override
    public void onFailure(Call<Playlist> call, Throwable t) {
        t.printStackTrace();
    }
}



