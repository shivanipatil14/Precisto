package in.precisto.precisto;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class SkippedUser extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skipped_user);

        Toolbar toolbar = findViewById(R.id.skipped_toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.skipped_drawer_layout);

        NavigationView navigationView = findViewById(R.id.skipped_drawer_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle =  new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new Services()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment selectedFragment=null;

        switch (menuItem.getItemId()) {
            case R.id.drawer_services_skipped:
                selectedFragment=new Services();
                break;
            case R.id.drawer_faq_skipped:
                selectedFragment=new FAQs();
                break;
            case R.id.drawer_contact_skipped:
               selectedFragment=new ContactUs();
                break;
            case R.id.drawer_about_skipped:
                selectedFragment=new AboutUs();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.skipped_content,selectedFragment).commit();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new Services()).commit();
        }

    }

    public void skipped_tnc(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new TermsConditions()).commit();
        drawer.closeDrawer(GravityCompat.START);
    }

    public void skipped_policy(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new PrivacyPolicies()).commit();
        drawer.closeDrawer(GravityCompat.START);
    }
}
