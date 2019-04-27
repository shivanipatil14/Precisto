package in.precisto.precisto;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class SendOtp extends AppCompatActivity {

    EditText otpEt;
    Button verifyOtp;
    String firstname, lastname, phone, emailid, dob, gender, businessname, industryname, businesstype, otp, verificationCode;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);

        auth = FirebaseAuth.getInstance();

        otpEt = findViewById(R.id.et_sendotp_otp);
        verifyOtp = findViewById(R.id.btn_sendotp_verify_otp);

        otp = otpEt.getText().toString();

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

        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(SendOtp.this, "Verification completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(SendOtp.this, "Verification failed"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;
                Toast.makeText(SendOtp.this, "Code sent", Toast.LENGTH_SHORT).show();
            }
        };

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+phone,                     // Phone number to verify
                60,                           // Timeout duration
                TimeUnit.SECONDS,                // Unit of timeout
                SendOtp.this,        // Activity (for callback binding)
                mCallback);


    }

    public void verifyOtp(View view) {

        final Intent intent = new Intent(this, ConfirmPassword.class);

        otp = otpEt.getText().toString();

        if (TextUtils.isEmpty(otp)) {
            Toast.makeText(getApplicationContext(), "Enter OTP", Toast.LENGTH_SHORT).show();
            return;
        } else {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, otp);

            auth.signInWithCredential(credential)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                intent.putExtra("bfirstname", firstname);
                                intent.putExtra("blastname", lastname);
                                intent.putExtra("bphone", phone);
                                intent.putExtra("bemailid", emailid);
                                intent.putExtra("bdob", dob);
                                intent.putExtra("bgender", gender);
                                intent.putExtra("bbusinessname", businessname);
                                intent.putExtra("bindustryname", industryname);
                                intent.putExtra("bbusinesstype", businesstype);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                            } else {
                                Toast.makeText(SendOtp.this, "Incorrect OTP", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }
}
