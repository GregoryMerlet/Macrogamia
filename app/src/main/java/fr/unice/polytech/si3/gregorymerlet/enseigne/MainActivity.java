package fr.unice.polytech.si3.gregorymerlet.enseigne;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import fr.unice.polytech.si3.gregorymerlet.enseigne.dialogs.ConnectionDialog;
import fr.unice.polytech.si3.gregorymerlet.enseigne.fragments.AccountFragment;
import fr.unice.polytech.si3.gregorymerlet.enseigne.fragments.MainFragment;
import fr.unice.polytech.si3.gregorymerlet.enseigne.fragments.MapFragment;
import fr.unice.polytech.si3.gregorymerlet.enseigne.fragments.ProductsFragment;
import fr.unice.polytech.si3.gregorymerlet.enseigne.model.Firm;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Firm firm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.firm = new Firm();
        this.firm.init();

        setTitle(getResources().getString(R.string.menu_home));

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragmentManager.getFragments() == null)
            fragmentManager.beginTransaction().replace(R.id.flContent, MainFragment.newInstance(firm)).commit();

        final RelativeLayout connectedUserLayout = (RelativeLayout) navigationView.getHeaderView(0).findViewById(R.id.connectedUserLayout);
        final RelativeLayout noUserLayout = (RelativeLayout) navigationView.getHeaderView(0).findViewById(R.id.noUserLayout);

        Button connectionButton = (Button) navigationView.getHeaderView(0).findViewById(R.id.connectionButton);
        connectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionDialog connectionDialog = new ConnectionDialog(MainActivity.this, firm);
                connectionDialog.show();
                connectionDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if(firm.isSomeoneConnected()) {
                            TextView name = (TextView) navigationView.getHeaderView(0).findViewById(R.id.connectedUserLayoutName);
                            TextView mail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.connectedUserLayoutMail);
                            TextView fidelityPoints = (TextView) navigationView.getHeaderView(0).findViewById(R.id.connectedUserLayoutFidelityPoints);

                            name.setText(firm.getActualUser().getFirstName() + " " + firm.getActualUser().getLastName());
                            mail.setText(firm.getActualUser().getMail());
                            fidelityPoints.setText(String.valueOf(firm.getActualUser().getFideltyPoints()));

                            noUserLayout.setVisibility(View.GONE);
                            connectedUserLayout.setVisibility(View.VISIBLE);
                            navigationView.getMenu().findItem(R.id.nav_account).setVisible(true);
                        }
                    }
                });
            }
        });

        Button deconnectionButton = (Button) navigationView.getHeaderView(0).findViewById(R.id.deconnectionButton);
        deconnectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firm.disconnect();
                navigationView.getMenu().findItem(R.id.nav_account).setVisible(false);
                connectedUserLayout.setVisibility(View.GONE);
                noUserLayout.setVisibility(View.VISIBLE);
            }
        });
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        switch(id){
            case R.id.nav_products:
                fragment = ProductsFragment.newInstance(firm);
                break;
            case R.id.nav_map:
                fragment = MapFragment.newInstance(firm);
                break;
            case R.id.nav_account:
                fragment = AccountFragment.newInstance(firm);
                break;
            default:
                fragment = MainFragment.newInstance(firm);
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        item.setChecked(true);
        // Set action bar title
        setTitle(item.getTitle());
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
