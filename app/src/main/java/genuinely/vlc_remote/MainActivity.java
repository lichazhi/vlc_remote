package genuinely.vlc_remote;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import genuinely.vlc_remote.dummy.StatusController;
import retrofit2.Retrofit;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private GestureDetectorCompat mDetector;

    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Controller ctrl = new Controller();
        final StatusController statusController = new StatusController();
        ctrl.start();
        ArrayList<String> propagator = ctrl.getArrayObject();

        ArrayAdapter<String> playlistAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, propagator);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "myPos "+ (i + 1), Toast.LENGTH_SHORT).show();
                statusController.start();
            }
        });
        listView.setAdapter(playlistAdapter);
    }
}
