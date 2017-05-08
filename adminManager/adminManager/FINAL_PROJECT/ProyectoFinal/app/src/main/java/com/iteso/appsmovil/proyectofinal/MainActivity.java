package com.iteso.appsmovil.proyectofinal;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.iteso.appsmovil.proyectofinal.fragments.MonthlyReportFragment;
import com.iteso.appsmovil.proyectofinal.fragments.MyNotificationsFragment;
import com.iteso.appsmovil.proyectofinal.fragments.NewNotificationFragment;
import com.iteso.appsmovil.proyectofinal.utility.Utility;


public final class MainActivity extends ActionBarActivity {

    private static final String TAG = "MainActivity";

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private String[] drawerElements;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.drawerElements = this.getResources().getStringArray(R.array.drawer_items);
        this.drawerLayout = (DrawerLayout) this.findViewById(R.id.drawer_layout);
        this.drawerList = (ListView) this.findViewById(R.id.left_drawer);
        this.drawerList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        this.drawerList.setAdapter( new ArrayAdapter<String>(this,R.layout.drawer_list_text,this.drawerElements));
        this.drawerList.setOnItemClickListener(new MainActivity.DrawerItemClickListener());

        this.drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                this.drawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        this.drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        this.drawerLayout.setDrawerListener(this.drawerToggle);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeButtonEnabled(true);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        this.drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (this.drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();

        switch( id ){

            case R.id.action_settings :

                this.navigateToAlarmSettings();

                return true;

            case R.id.menu_logout :

                Utility.doLogout(this.getApplicationContext());
                this.navigateToLoginActivity();
                this.finish();

                return true;

            default :

                return super.onOptionsItemSelected(item);

        }

    }

    private final void navigateToLoginActivity(){

        Intent intent = new Intent(this, LoginActivity.class);
        this.startActivity(intent);

    }

    private final void navigateToAlarmSettings(){

        Intent intent = new Intent(this, AlarmSettings.class);
        this.startActivity(intent);

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            MainActivity.this.selectItem(position);

        }
    }

    private void selectItem(int position){

        this.drawerList.setItemChecked(position,true);
        this.setTitle(this.drawerElements[position]);
        this.setFragment(position);
        this.drawerLayout.closeDrawer(this.drawerList);

    }

    private void setFragment(int position){

        Fragment fragment = null;
        FragmentManager fragmentManager = this.getFragmentManager();

        switch ( position ){

            case Utility.MY_NOTIFICATIONS_FRAGMENT :
                fragment = new MyNotificationsFragment();
                break;

            case Utility.NEW_NOTIFICATION_FRAGMENT :
                fragment = new NewNotificationFragment();
                break;

            case Utility.MONTHLY_INCOME_REPORT :
                fragment = new MonthlyReportFragment();
                break;
        }

        if ( fragment != null){
            fragmentManager.beginTransaction().replace(R.id.content_frame,fragment).commit();
        }
        else{
            Toast.makeText(this.getApplicationContext(),"ERROR : Invalid option in drawer.", Toast.LENGTH_LONG).show();
            Log.e(this.TAG,"ERROR : Fragment selection from drawer is NULL : position " + position);
        }


    }


}
