package genuinely.vlc_remote.dummy;

import android.net.Credentials;
import android.util.Log;

import genuinely.vlc_remote.Song;
import genuinely.vlc_remote.Status;
import genuinely.vlc_remote.VLCInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class StatusController {

    //URL to be appended
    static final String DEFAULT_URL = "http://192.168.1.79:8080/requests/";

    private static final String TAG = "StatusController";

    public void start() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(DEFAULT_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create()).build();

        VLCInterface vlcInterface = retrofit.create(VLCInterface.class);

        Call<Status> call = vlcInterface.getStatus(okhttp3.Credentials.basic("","123456"),"pl_play", 4);
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








