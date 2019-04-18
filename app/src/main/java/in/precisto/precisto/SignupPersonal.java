package in.precisto.precisto;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SignupPersonal extends AppCompatActivity {
    EditText fname,lname,contact,dob,email;
    RadioGroup gender;
    RadioButton select;
    FloatingActionButton next;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String MobilePattern = "[0-9]{10}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_personal);

        fname = findViewById(R.id.et_signup_fname);
        lname = findViewById(R.id.et_signup_lname);
        contact = findViewById(R.id.et_signup_contact);
        email = findViewById(R.id.et_signup_email);
        dob = findViewById(R.id.et_signup_dob);
        gender = findViewById(R.id.rgroup_signup);
        next = findViewById(R.id.btn_signup_pnext);


    }

    public void goToBusiness(View view) {
        Intent intent = new Intent(this, SignupBusiness.class);

        String firstname = fname.getText().toString();
        String lasttname = lname.getText().toString();
        String phone = contact.getText().toString();
        String emailid = email.getText().toString();
        String dob1 = dob.getText().toString();
        //select = findViewById(gender.getCheckedRadioButtonId());
        //String gen = select.getText().toString();

        if(TextUtils.isEmpty(firstname))
        {
            Toast.makeText(getApplicationContext(),"Enter First Name",Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            if(TextUtils.isEmpty(lasttname))
            {
                Toast.makeText(getApplicationContext(),"Enter Last Name",Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                if(TextUtils.isEmpty(phone))
                {
                    Toast.makeText(getApplicationContext(),"Enter Valid Contact Number",Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    if(!phone.matches(MobilePattern))
                    {
                        Toast.makeText(getApplicationContext(),"Please provide a valid 10 digit contact number",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else {
                    if(TextUtils.isEmpty(emailid))
                    {
                        Toast.makeText(getApplicationContext(),"Enter Valid Email",Toast.LENGTH_SHORT).show();
                        return;
                    }
                        else {
                            if (!emailid.matches(emailPattern)) {
                                Toast.makeText(getApplicationContext(), "Please provide your valid Email address", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                if (TextUtils.isEmpty(emailid)) {
                                    Toast.makeText(getApplicationContext(), "Enter Valid Email", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                else {
                                    if (TextUtils.isEmpty(dob1)) {
                                        Toast.makeText(getApplicationContext(), "Enter Date of Birth", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    else{
                                        if(gender.getCheckedRadioButtonId() == -1)
                                        {
                                            Toast.makeText(getApplicationContext(), "Please Select Gender", Toast.LENGTH_SHORT).show();
                                            return;
                                        }

                                        else{
                                                startActivity(intent);
                                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                            }
                                    }
                                }
                            }
                        }

                    }
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
