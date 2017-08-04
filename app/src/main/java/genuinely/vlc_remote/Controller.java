package genuinely.vlc_remote;

import android.nfc.Tag;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.simpleframework.xml.ElementList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class Controller implements Callback<Playlist> {

    //ArrayList where song values are stored
    ArrayList<String> arrayObject = new ArrayList<String>();

    public ArrayList<String> getArrayObject(){
        return arrayObject;
    }

    static final String BASE_URL = "http://192.168.1.79:8080/requests/";

    private static final String TAG = "Controller";

    public void start() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create()).build();

        VLCInterface vlcInterface = retrofit.create(VLCInterface.class);

        //Fetch the list of songs
        Call<Playlist> call = vlcInterface.loadPlaylist(Credentials.basic("","123456"));
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
                String songName = song.getName();
                int id = song.getId();
                Log.d(TAG, "this song's id is:" + (id));
                arrayObject.add(songName);
            }
        } else {
            Log.d(TAG, "error");
        }

        for (int i = 0; i < arrayObject.size(); i++)
        {
            Log.d(TAG, "song" + i);
        }
    }

    @Override
    public void onFailure(Call<Playlist> call, Throwable t) {
        t.printStackTrace();
    }

}





