package org.github.brnmb.android.active.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.activeandroid.query.Select;
import org.github.brnmb.android.active.R;
import org.github.brnmb.android.active.model.Hero;
import org.github.brnmb.android.active.model.HeroAttribute;
import org.github.brnmb.android.active.model.HeroRole;

import java.util.List;

/** MainActivity of the app
 *
 * @author Bruno Miranda Brandão
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * FragmentList
     */
    final String[] fragments ={
            "org.github.brnmb.android.active.view.FragmentHerosAgility",
            "org.github.brnmb.android.active.view.FragmentHerosStrength",
            "org.github.brnmb.android.active.view.FragmentHerosIntelligence",
            "org.github.brnmb.android.active.view.FragmentHome"};

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        prepareModel();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            });
        }

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.main_fragment, Fragment.instantiate(MainActivity.this, fragments[3]));
        tx.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.setDrawerListener(toggle);
        }
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
            navigationView.bringToFront();
            navigationView.requestLayout();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.main_fragment, Fragment.instantiate(MainActivity.this, fragments[1]));
            tx.commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_agility) {
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.main_fragment,Fragment.instantiate(MainActivity.this, fragments[0]));
            tx.commit();
        } else if (id == R.id.nav_strength) {
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.main_fragment, Fragment.instantiate(MainActivity.this, fragments[1]));
            tx.commit();
        } else if (id == R.id.nav_intelligence) {
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.main_fragment, Fragment.instantiate(MainActivity.this, fragments[2]));
            tx.commit();
        } else if (id == R.id.nav_home) {
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.main_fragment,Fragment.instantiate(MainActivity.this, fragments[3]));
            tx.commit();
        } else if (id == R.id.nav_share) {
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.main_fragment,Fragment.instantiate(MainActivity.this, fragments[1]));
            tx.commit();
        } else if (id == R.id.nav_send) {
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.main_fragment,Fragment.instantiate(MainActivity.this, fragments[2]));
            tx.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    /**
     * Insert some initial data o the Database
     */
    public void prepareModel() {
        HeroAttribute strength = new HeroAttribute("Strength");
        HeroAttribute agility = new HeroAttribute("Agility");
        HeroAttribute intelligence = new HeroAttribute("Intelligence");

        HeroRole midLane = new HeroRole("Mid Lane");
        HeroRole offLane = new HeroRole("Off Lane");
        HeroRole safeLane = new HeroRole("Safe Lane");

        Hero axe = new Hero("Axe", strength, offLane);
        Hero puck = new Hero("Puck", intelligence, midLane);
        Hero nevermore = new Hero("Nevermore", agility, midLane);
        Hero magina = new Hero("Magina", agility, midLane);

        List<HeroAttribute> storedAttributes = HeroAttribute.getAllAttributes();
        List<HeroRole> storedHeroRoles = HeroRole.getAllHeroRoles();
    }
}
