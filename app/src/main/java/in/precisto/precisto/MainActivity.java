package in.precisto.precisto;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static in.precisto.precisto.Profile.ptemp;
import static in.precisto.precisto.Profile.userInfo;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private TextView profilename, username;
    private DatabaseReference mProfileReference;

    static boolean flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.main_drawer_layout);

        navigationView = findViewById(R.id.main_drawer_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.getMenu().findItem(R.id.drawer_home).setChecked(true);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new Home()).commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment selectedFragment = null;

        switch (menuItem.getItemId()) {
            case R.id.drawer_home:
                selectedFragment = new Home();
                break;
            case R.id.drawer_profile:
                selectedFragment = new Profile();
                break;
            case R.id.drawer_services:
                selectedFragment = new Services();
                break;
            case R.id.drawer_quotation:
                selectedFragment = new Quotations();
                break;
            case R.id.drawer_faq:
                selectedFragment = new FAQs();
                break;
            case R.id.drawer_contact:
                selectedFragment = new ContactUs();
                break;
            case R.id.drawer_about:
                selectedFragment = new AboutUs();
                break;

        }

        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, selectedFragment).commit();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (navigationView.getMenu().findItem(R.id.drawer_home).isChecked()) {
                super.onBackPressed();
            } else if (navigationView.getMenu().findItem(R.id.drawer_profile).isChecked()) {

                if (flag) {
                    ptemp.fname.setEnabled(false);
                    ptemp.lname.setEnabled(false);
                    ptemp.contact.setEnabled(false);
                    ptemp.dob.setEnabled(false);
                    ptemp.email.setEnabled(false);
                    ptemp.gen.setEnabled(false);
                    ptemp.bname.setEnabled(false);
                    ptemp.industry.setEnabled(false);
                    ptemp.btype.setEnabled(false);

                    ptemp.save.setVisibility(View.INVISIBLE);
                    ptemp.edit.setVisibility(View.VISIBLE);
                    flag = false;

                    ptemp.fname.setText(userInfo.getFirstName());
                    ptemp.lname.setText(userInfo.getLastName());
                    ptemp.contact.setText(userInfo.getContact());
                    ptemp.dob.setText(userInfo.getDob());
                    ptemp.email.setText(userInfo.getEmail());
                    ptemp.gen.setText(userInfo.getGender());
                    ptemp.bname.setText(userInfo.getBusinessName());
                    ptemp.industry.setText(userInfo.getIndustry());
                    ptemp.btype.setText(userInfo.getBusinessType());


                } else {
                    navigationView.getMenu().findItem(R.id.drawer_home).setChecked(true);
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new Home()).commit();
                }

            } else {
                navigationView.getMenu().findItem(R.id.drawer_home).setChecked(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new Home()).commit();
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_action_menu, menu);

        profilename = (TextView) findViewById(R.id.drawer_profile_name);
        username = (TextView) findViewById(R.id.drawer_profile_user_name);

        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        mProfileReference = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("ProfileInfo");

        ValueEventListener profileListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                UserInfo userInfo = dataSnapshot.getValue(UserInfo.class);

                profilename.setText(userInfo.getFirstName() + " " + userInfo.getLastName());
                username.setText(userInfo.getEmail());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(), "Failed to load details", Toast.LENGTH_SHORT).show();

            }
        };
        mProfileReference.addValueEventListener(profileListener);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.action_logout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("Do you want to log out?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FirebaseAuth.getInstance().signOut();
                            Intent intent = new Intent(getApplicationContext(), SignupLogin.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(MainActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        return true;
    }

    public void tnc(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new TermsConditions()).commit();
        drawer.closeDrawer(GravityCompat.START);
    }

    public void policy(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new PrivacyPolicies()).commit();
        drawer.closeDrawer(GravityCompat.START);
    }
}
