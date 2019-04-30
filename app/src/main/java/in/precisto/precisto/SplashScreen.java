package in.precisto.precisto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashScreen extends AppCompatActivity {

    private SharedPreferences preferences;
    private FirebaseAuth mAuth;
    private FirebaseUser use;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mAuth = FirebaseAuth.getInstance();
        use = mAuth.getCurrentUser();

        preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        boolean firstStart = preferences.getBoolean("firstStart", true);


        if (firstStart) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent homeIntent = new Intent(SplashScreen.this, Slider.class);
                    startActivity(homeIntent);
                    overridePendingTransition(R.anim.from_bottom, R.anim.to_top);
                    finish();

                }
            }, 2000);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("firstStart", false);
            editor.apply();

        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (use == null) {
                        Intent homeIntent = new Intent(SplashScreen.this, SignupLogin.class);
                        startActivity(homeIntent);
                        overridePendingTransition(R.anim.from_bottom, R.anim.to_top);
                        finish();
                    } else {

                        if (use.getUid().equals("MKG5JZqoxIS1Qu2zFDowUfZXdVM2")) {
                            Intent homeIntent = new Intent(SplashScreen.this, AdminPanel.class);
                            startActivity(homeIntent);
                            overridePendingTransition(R.anim.from_bottom, R.anim.to_top);
                            finish();
                        } else {
                            Intent homeIntent = new Intent(SplashScreen.this, MainActivity.class);
                            startActivity(homeIntent);
                            overridePendingTransition(R.anim.from_bottom, R.anim.to_top);
                            finish();
                        }
                    }
                }
            }, 2000);
        }
    }
}
