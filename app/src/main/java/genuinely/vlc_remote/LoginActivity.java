package genuinely.vlc_remote;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button mShowDialog = (Button) findViewById(R.id.btnShowDialog);
        mShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(LoginActivity.this);
                View myView = getLayoutInflater().inflate(R.layout.dialog_login, null);
                final EditText mIP = (EditText) myView.findViewById(R.id.etIP);
                final EditText mPassword = (EditText) myView.findViewById(R.id.etPassword);
                Button mLogin = (Button) myView.findViewById(R.id.btnLogin);

                mLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!mIP.getText().toString().isEmpty() && !mPassword.getText().toString().isEmpty()) {
                            Toast.makeText(LoginActivity.this,
                                    "Login successful",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("ip", mIP.getText());
                            intent.putExtra("password", mPassword.getText());
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Please fill any empty fields", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                mBuilder.setView(myView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
    }
}
