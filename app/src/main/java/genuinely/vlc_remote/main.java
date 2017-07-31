package genuinely.vlc_remote;

import android.app.Fragment;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit2.Call;
import retrofit2.http.GET;

public class main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Controller ctrl = new Controller();
        ctrl.start();
//
//        try {
//            Process process = Runtime.getRuntime().exec("logcat -d");
//            BufferedReader bufferedReader = new BufferedReader(
//                    new InputStreamReader(process.getInputStream()));
//
//            StringBuilder log=new StringBuilder();
//            String line = "";
//            while ((line = bufferedReader.readLine()) != null) {
//                log.append(line);
//            }
//            TextView tv = (TextView)findViewById(R.id.textView1);
//            tv.setText(log.toString());
//        } catch (IOException e) {
//            // Handle Exception
//        }
    }
}
