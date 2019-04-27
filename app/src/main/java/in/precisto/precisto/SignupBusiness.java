package in.precisto.precisto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SignupBusiness extends AppCompatActivity {
    EditText businessName, industry;
    RadioGroup type;
    RadioButton newBusiness, existingBusiness;
    String businesstype;
    String firstname, lastname, phone, emailid, dob, gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_business);
        businessName = findViewById(R.id.et_signup_bname);
        industry = findViewById(R.id.et_signup_Iname);
        type = findViewById(R.id.rgroup_type);
        newBusiness = findViewById(R.id.radio_signup_newb);
        existingBusiness = findViewById(R.id.radio_signup_existingb);


        //from SignupPersonal
        Intent i = getIntent();
        firstname = i.getStringExtra("pfirstname");
        lastname = i.getStringExtra("plastname");
        phone = i.getStringExtra("pphone");
        emailid = i.getStringExtra("pemailid");
        dob = i.getStringExtra("pdob");
        gender = i.getStringExtra("pgender");

    }

    public void goToPassword(View view) {
        //Intent intent = new Intent(this, ConfirmPassword.class);
        Intent intent = new Intent(this, SendOtp.class);

        businesstype = "";
        if (newBusiness.isChecked()) {
            businesstype = "New Business";
        } else if (existingBusiness.isChecked()) {
            businesstype = " Existing Business";
        }


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
                    intent.putExtra("bfirstname", firstname);
                    intent.putExtra("blastname", lastname);
                    intent.putExtra("bphone", phone);
                    intent.putExtra("bemailid", emailid);
                    intent.putExtra("bdob", dob);
                    intent.putExtra("bgender", gender);
                    intent.putExtra("bbusinessname", bname);
                    intent.putExtra("bindustryname", iname);
                    intent.putExtra("bbusinesstype", businesstype);
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
