package in.precisto.precisto;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText username, password;
    Button login;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private FirebaseAuth mAuth;
    private ProgressDialog mProLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mProLogin= new ProgressDialog(this);
        username = (EditText) findViewById(R.id.login_et_un);
        password = (EditText) findViewById(R.id.login_et_pass);
        login = (Button) findViewById(R.id.btn_login_login);

        mAuth= FirebaseAuth.getInstance();


    }

    public void login(View view) {
        //Intent intent = new Intent(this, MainActivity.class);


        String user = username.getText().toString();
        String pass = password.getText().toString();

        if (TextUtils.isEmpty(user)) {
            Toast.makeText(getApplicationContext(), "Enter Email", Toast.LENGTH_SHORT).show();
            return;
        } else {

            if (!user.matches(emailPattern)) {
                Toast.makeText(getApplicationContext(), "Please provide your valid Email address", Toast.LENGTH_SHORT).show();
                return;
            } else {

                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(getApplicationContext(), "Enter Valid Password", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (pass.length() < 8) {
                        Toast.makeText(getApplicationContext(), "Password should have at least 8 characters", Toast.LENGTH_SHORT).show();
                        return;
                    } /*else {
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                    }*/

                }
            }
        }
        mProLogin.setTitle("Logging In");
        mProLogin.setMessage("Please wait while we check your credentials");
        mProLogin.setCanceledOnTouchOutside(false);
        mProLogin.show();

        signin(user,pass);
    }

    public void forgotPass(View view) {
        Intent intent = new Intent(this, ForgotPassword.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, SignupLogin.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }
    private void signin(String user,String pass) {

        mAuth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    mProLogin.dismiss();
                    Intent loginIntent= new Intent(Login.this,MainActivity.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(loginIntent);

                }
                else {
                    mProLogin.dismiss();
                    Toast.makeText(getApplication(),"UserName Or Password Incorrect",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
