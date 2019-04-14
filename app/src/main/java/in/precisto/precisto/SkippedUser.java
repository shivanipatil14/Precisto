package in.precisto.precisto;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.drawer_services_skipped:
                Toast.makeText(this, "Services", Toast.LENGTH_SHORT).show();
                break;
            case R.id.drawer_faq_skipped:
                Toast.makeText(this, "FAQs", Toast.LENGTH_SHORT).show();
                break;
            case R.id.drawer_contact_skipped:
                Toast.makeText(this, "Contact us", Toast.LENGTH_SHORT).show();
                break;
            case R.id.drawer_about_skipped:
                Toast.makeText(this, "About us", Toast.LENGTH_SHORT).show();
                break;
            case R.id.drawer_tnc_skipped:
                Toast.makeText(this, "Terms and Conditions", Toast.LENGTH_SHORT).show();
                break;
            case R.id.drawer_privacy_policy_skipped:
                Toast.makeText(this, "Privacy Policies", Toast.LENGTH_SHORT).show();
                break;

        }

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
}
