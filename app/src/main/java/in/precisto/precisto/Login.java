package in.precisto.precisto;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

public class Login extends AppCompatActivity {
    EditText username, password;
    Button login;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+", adminUsername, adminPassword;
    private FirebaseAuth mAuth;
    private ProgressDialog mProLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mProLogin = new ProgressDialog(this);
        username = (EditText) findViewById(R.id.login_et_un);
        password = (EditText) findViewById(R.id.login_et_pass);
        login = (Button) findViewById(R.id.btn_login_login);

        mAuth = FirebaseAuth.getInstance();

    }

    public void login(View view) {
        //Intent intent = new Intent(this, MainActivity.class);


        String user = username.getText().toString();
        String pass = password.getText().toString();

        if (checkInternetConnection()) {
            if (TextUtils.isEmpty(user)) {
                Toast.makeText(getApplicationContext(), "Enter Email", Toast.LENGTH_SHORT).show();
                return;
            } else {

                if (!user.matches(emailPattern)) {
                    Toast.makeText(getApplicationContext(), "Please provide your valid Email address", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    if (TextUtils.isEmpty(pass)) {
                        Toast.makeText(getApplicationContext(), "Enter Valid Password", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
            mProLogin.setTitle("Logging In");
            mProLogin.setMessage("Please wait while we check your credentials");
            mProLogin.setCanceledOnTouchOutside(false);
            mProLogin.show();

            signin(user, pass);
        }

    }

    public void forgotPass(View view) {
        Intent intent = new Intent(this, ForgotPassword.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, SignupLogin.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }

    private void signin(final String user, String pass) {

        final String tempuser = user, temppassword = pass;
        mAuth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    mProLogin.dismiss();
                    if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals("MKG5JZqoxIS1Qu2zFDowUfZXdVM2")) {

                        Intent loginIntent = new Intent(Login.this, AdminPanel.class);
                        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(loginIntent);
                    } else {

                        Intent loginIntent = new Intent(Login.this, MainActivity.class);
                        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(loginIntent);
                    }

                } else {
                    mProLogin.dismiss();
                    Toast.makeText(getApplication(), "Username Or Password Incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public boolean checkInternetConnection() {
        // get Connectivity Manager object to check connection
        ConnectivityManager connec = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
            return true;
        } else if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
            return false;
        }
        return false;
    }

}

