package in.precisto.precisto;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.main_drawer_layout);

        NavigationView navigationView = findViewById(R.id.main_drawer_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle =  new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment selectedFragment=null;

        switch (menuItem.getItemId()) {
            case R.id.drawer_home:
                selectedFragment = new Home();
                break;
            case R.id.drawer_profile:
                selectedFragment = new Profile();
                break;
            case R.id.drawer_services:
                selectedFragment=new Services();
                break;
            case R.id.drawer_quotation:
                selectedFragment=new Quotations();
                break;
            case R.id.drawer_faq:
                selectedFragment=new FAQs();
                break;
            case R.id.drawer_contact:
                selectedFragment=new ContactUs();
                break;
            case R.id.drawer_about:
                selectedFragment=new AboutUs() ;
                break;
            case R.id.drawer_tnc:
                selectedFragment=new TermsConditions();
                break;
            case R.id.drawer_privacy_policy:
                selectedFragment=new PrivacyPolicies();
                break;

        }

        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,selectedFragment).commit();

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_action_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.action_logout) {
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.action_exit) {
            Toast.makeText(this, "Exit", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}
