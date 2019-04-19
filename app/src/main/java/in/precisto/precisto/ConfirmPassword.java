package in.precisto.precisto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ConfirmPassword extends AppCompatActivity {

    EditText pass, conPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_password);

        pass = findViewById(R.id.et_cpass_pass);
        conPass = findViewById(R.id.et_cpass_repass);
    }

    public void confirmPassword(View view) {

        String password = pass.getText().toString();
        String conPassword = conPass.getText().toString();

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter Valid Password", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (password.length() < 8) {
                Toast.makeText(getApplicationContext(), "Password should have at least 8 characters", Toast.LENGTH_SHORT).show();
                return;
            } else {
                if (TextUtils.isEmpty(conPassword)) {
                    Toast.makeText(getApplicationContext(), "Confirm the Password", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals(conPassword)) {
                        Intent intent = new Intent(this, Login.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    } else {
                        Toast.makeText(getApplicationContext(), "Password doesn't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
}
