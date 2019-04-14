package in.precisto.precisto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class FogotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fogot_password);
    }

    public void sendEmail(View view)
    {
        Toast.makeText(this, "E-mail has been sent!", Toast.LENGTH_SHORT).show();
    }
}
