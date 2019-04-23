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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;

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
                            Intent intent = new Intent(getApplicationContext(), Login.class);
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
