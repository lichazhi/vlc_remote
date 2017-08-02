package genuinely.vlc_remote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Controller ctrl = new Controller();
        ctrl.start();
        ArrayList<String> propagator = ctrl.getArrayObject();

        ArrayAdapter<String> playlistAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, propagator);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(playlistAdapter);
    }
}
