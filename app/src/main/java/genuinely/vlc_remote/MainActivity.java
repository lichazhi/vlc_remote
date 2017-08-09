package genuinely.vlc_remote;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private static Controller mCtrl;
    private String mIP;
    private String password;
    private static final String TAG = "MainActivity";
    private static final int GET_PLAYLIST_MSG = 1;
    private static ArrayAdapter<String> mPlaylistAdapter;
    private static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == GET_PLAYLIST_MSG) {
                mPlaylistAdapter.notifyDataSetChanged();
                Log.d(TAG, "Data Set Changed");
            }
        }
    };

    public void setmIP(String ip1) {
        this.mIP = ip1;
    }

    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = getIntent();
        mIP = i.getStringExtra("ip");
        Log.d(TAG, "This is IP:" + mIP);
        password = i.getStringExtra("password");
        Log.d(TAG, "This is Password:" + password);

        //Controller, Status Controller, and ListView
        mCtrl = new Controller();
        mListView = (ListView) findViewById(R.id.listView);
        final StatusController statusController = new StatusController();

        //Controller starts
        mCtrl.start();


        Log.d(TAG, "Before");
        mPlaylistAdapter = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1);
        mListView.setAdapter(mPlaylistAdapter);
        Log.d(TAG, "running");



//        for (Iterator<String> i = propagator.iterator(); i.hasNext();) {
//            Log.d(TAG, i.toString());
//        }
        Log.d(TAG, "After");

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "myPos "+ (i + 1), Toast.LENGTH_SHORT).show();
                statusController.setIdList(i + 4);
                statusController.setCommand("pl_pause");
                statusController.start();
            }
        });

    }

    class Controller implements Callback<Playlist> {

        private static final String TAG = "Controller";

        public void start() {
            String BASE_URL = "http://"+ mIP + ":8080/requests/";
            Log.d(TAG, BASE_URL);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();

            VLCInterface vlcInterface = retrofit.create(VLCInterface.class);

            //Fetch the list of songs
            Call<Playlist> call = vlcInterface.loadPlaylist(Credentials.basic("", password));
            call.enqueue(this);
        }

        @Override
        public void onResponse(Call<Playlist> call, Response<Playlist> response) {

            Log.d(TAG, "entering");

            if (response.isSuccessful()) {

                Playlist playlist = response.body();
                int s = playlist.getPlaylist().size();
                Log.d(TAG, "wow so big " + s);


                mPlaylistAdapter.clear();
                for (Iterator<Song> i = playlist.getPlaylist().iterator(); i.hasNext();) {
                    Song song = i.next();
                    Log.d(TAG, (("Title: " + song.getName())));
                    String songName = song.getName();
                    int id = song.getId();
                    Log.d(TAG, "this song's id is:" + (id));
                    mPlaylistAdapter.add(songName);
                }
                mHandler.sendEmptyMessage(GET_PLAYLIST_MSG);
            } else {
                Log.d(TAG, "error");
            }
        }

        @Override
        public void onFailure(Call<Playlist> call, Throwable t) {
            t.printStackTrace();
        }
    }

    class StatusController {

        public int idList;
        private String command;
        private String ipAddress;

        public void setIdList(int touchID) {
            this.idList = touchID;
        }
        public void setCommand(String command1) {
            this.command = command1;
        }

        public void setIpAddress(String ipAddress) {
            this.ipAddress = ipAddress;
        }

        //URL to be appended
        String DEFAULT_URL = "http://" + mIP + ":8080/requests/";

        private static final String TAG = "StatusController";

        public void start() {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(DEFAULT_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create()).build();

            VLCInterface vlcInterface = retrofit.create(VLCInterface.class);

            Call<Status> call = vlcInterface.getStatus(okhttp3.Credentials.basic("", password), command, idList);
            call.enqueue(new Callback<Status>() {
                @Override
                public void onResponse(Call<Status> call, Response<Status> response) {
                    Log.d(TAG, "Response");

                    if (response.isSuccessful()) {
                        Log.d(TAG, "Response is success");
                    }

                    else {
                        Log.d(TAG, "error");
                    }
                }

                @Override
                public void onFailure(Call<Status> call, Throwable t) {

                }
            });
        }
    }
}
