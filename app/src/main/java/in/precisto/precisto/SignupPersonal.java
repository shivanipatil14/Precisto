package in.precisto.precisto;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class SignupPersonal extends AppCompatActivity {

    final Calendar myCalendar = Calendar.getInstance(TimeZone.getDefault());

   public EditText fname, lname, contact, dob, email;
   public RadioGroup gender;
   public RadioButton select;
   public FloatingActionButton next;
   public String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
   public String MobilePattern = "[0-9]{10}";

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private ProgressDialog mProSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_personal);

        mProSignUp=new ProgressDialog(this);

        fname = findViewById(R.id.et_signup_fname);
        lname = findViewById(R.id.et_signup_lname);
        contact = findViewById(R.id.et_signup_contact);
        email = findViewById(R.id.et_signup_email);
        dob = findViewById(R.id.et_signup_dob);


        final DatePickerDialog.OnDateSetListener date = new
                DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateLabel();
                    }

                };
        dob.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    new DatePickerDialog(SignupPersonal.this, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
                return true;
            }
        });


        gender = findViewById(R.id.rgroup_signup);
        next = findViewById(R.id.btn_signup_pnext);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("p_user");

    }

    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dob.setText(sdf.format(myCalendar.getTime()));
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

        if (TextUtils.isEmpty(firstname)) {
            Toast.makeText(getApplicationContext(), "Enter First Name", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (TextUtils.isEmpty(lasttname)) {
                Toast.makeText(getApplicationContext(), "Enter Last Name", Toast.LENGTH_SHORT).show();
                return;
            } else {
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(getApplicationContext(), "Enter Valid Contact Number", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (!phone.matches(MobilePattern)) {
                        Toast.makeText(getApplicationContext(), "Please provide a valid 10 digit contact number", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        if (TextUtils.isEmpty(emailid)) {
                            Toast.makeText(getApplicationContext(), "Enter Valid Email", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            if (!emailid.matches(emailPattern)) {
                                Toast.makeText(getApplicationContext(), "Please provide your valid Email address", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                if (TextUtils.isEmpty(emailid)) {
                                    Toast.makeText(getApplicationContext(), "Enter Valid Email", Toast.LENGTH_SHORT).show();
                                    return;
                                } else {
                                    if (TextUtils.isEmpty(dob1)) {
                                        Toast.makeText(getApplicationContext(), "Enter Date of Birth", Toast.LENGTH_SHORT).show();
                                        return;
                                    } else {
                                        if (gender.getCheckedRadioButtonId() == -1) {
                                            Toast.makeText(getApplicationContext(), "Please Select Gender", Toast.LENGTH_SHORT).show();
                                            return;
                                        } /*else {
                                            startActivity(intent);
                                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                        }*/
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }

        mProSignUp.setTitle("Registering User");
        mProSignUp.setMessage("Please wait while we create your account");
        mProSignUp.setCanceledOnTouchOutside(false);
        mProSignUp.show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, SignupLogin.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }

}
