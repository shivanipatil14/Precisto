package in.precisto.precisto;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EasySplashScreen easySplashScreen = new EasySplashScreen(SplashScreen.this)
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(4000)
                .withBackgroundColor(Color.parseColor("#3B5998"))
                .withLogo(R.drawable.ic_precisto_logo_all_white);

        View view = easySplashScreen.create();

        setContentView(view);


    }
}
