package codezilla.iservant;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, NearByJobsFragment.OnFragmentInteractionListener, SearchJobFragment.OnFragmentInteractionListener , OfferJobFragment.OnFragmentInteractionListener  , WorkHistoryFragment.OnFragmentInteractionListener , MyAccount.OnFragmentInteractionListener   {

    FirebaseUser user;
    private static MainPage mainContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //my code
        Fragment fragment = null;
        Class fragmentClass = null;
        fragmentClass = NearByJobsFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        //my code over

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //my code
        mainContext=this;


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        //my code
        user = FirebaseAuth.getInstance().getCurrentUser();
        TextView unametxt=(TextView)findViewById(R.id.username);
        unametxt.setText(user.getEmail());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        boolean signedIn=true;
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass = null;

        if (id == R.id.nearbyjobs) {
            fragmentClass = NearByJobsFragment.class;
        } else if (id == R.id.searchjobs) {
            fragmentClass = SearchJobFragment.class;
        } else if (id == R.id.searchjobs) {
            fragmentClass = OfferJobFragment.class;
        } else if (id == R.id.offerjob) {
            fragmentClass = OfferJobFragment.class;
        } else if (id == R.id.workhistory) {
            fragmentClass = WorkHistoryFragment.class;
        } else if (id == R.id.account) {
            fragmentClass = MyAccount.class;
        } else if (id == R.id.signout) {
            Toast.makeText(this,"Successfully Signed Out",Toast.LENGTH_LONG).show();
            Intent in = new Intent(this.getApplicationContext(), Login.class);
            FirebaseAuth.getInstance().signOut();
            signedIn=false;
            this.startActivity(in);
            this.finish();
        }
        if(signedIn){
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }

        return true;
    }

    public static MainPage getMainContext(){
        return mainContext;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
