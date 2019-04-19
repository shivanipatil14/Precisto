package in.precisto.precisto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SignupBusiness extends AppCompatActivity {
    EditText businessName, industry;
    RadioGroup type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_business);
        businessName = findViewById(R.id.et_signup_bname);
        industry = findViewById(R.id.et_signup_Iname);
        type = findViewById(R.id.rgroup_type);
    }

    public void goToPasswordd(View view) {
        Intent intent = new Intent(this, ConfirmPassword.class);

        String bname = businessName.getText().toString();
        String iname = industry.getText().toString();

        if (TextUtils.isEmpty(bname)) {
            Toast.makeText(getApplicationContext(), "Enter Business Name", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (TextUtils.isEmpty(iname)) {
                Toast.makeText(getApplicationContext(), "Enter Industry", Toast.LENGTH_SHORT).show();
                return;
            } else {
                if (type.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Please Select Business Type", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }


            }
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
