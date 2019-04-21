package in.precisto.precisto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ConfirmPassword extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private ProgressDialog mProSignup;
    String firstname, lastname, phone, emailid, dob, gender, businessname, industryname, businesstype;
    EditText pass, conPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_password);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("P_user");
        mAuth=FirebaseAuth.getInstance();
        mProSignup= new ProgressDialog(this);

        pass = findViewById(R.id.et_cpass_pass);
        conPass = findViewById(R.id.et_cpass_repass);

        Intent i = getIntent();
        firstname = i.getStringExtra("bfirstname");
        lastname = i.getStringExtra("blastname");
        emailid = i.getStringExtra("bemailid");
        phone = i.getStringExtra("bphone");
        dob = i.getStringExtra("bdob");
        gender = i.getStringExtra("bgender");
        businessname = i.getStringExtra("bbusinessname");
        industryname = i.getStringExtra("bindustryname");
        businesstype = i.getStringExtra("bbusinesstype");

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

                        final String id = databaseReference.push().getKey();

                        final Map<String, String> datamap = new HashMap<String, String>();
                        datamap.put("firstname", firstname);
                        datamap.put("lastname", lastname);
                        datamap.put("phone", phone);
                        datamap.put("Email", emailid);
                        datamap.put("dob", dob);
                        datamap.put("Gender", gender);
                        datamap.put("businessname", businessname);
                        datamap.put("industryname", industryname);
                        datamap.put("businesstype", businesstype);

                        databaseReference.child(id).setValue(datamap);

                        mProSignup.setTitle("Signing up");
                        mProSignup.setMessage("Please wait while we sign you up");
                        mProSignup.setCanceledOnTouchOutside(false);
                        mProSignup.show();

                        mAuth.createUserWithEmailAndPassword(emailid,password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    mProSignup.dismiss();
                                    Intent loginIntent= new Intent(ConfirmPassword.this,Login.class);
                                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(loginIntent);
                                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                    finish();

                                }
                                else {
                                    mProSignup.dismiss();
                                    Toast.makeText(getApplication(),"User already exists",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                    } else {
                        Toast.makeText(getApplicationContext(), "Password doesn't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
}
