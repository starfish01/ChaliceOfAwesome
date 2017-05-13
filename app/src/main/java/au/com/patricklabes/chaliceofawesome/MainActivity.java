package au.com.patricklabes.chaliceofawesome;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity {


    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(whatWasClicked);

        //call to open display
        viewController();

       // fragmentTransaction.replace(R.id.first_layout,new Fragment_Home()).commit();

    }

    private void viewController(){

        SharedPreferences settings = getSharedPreferences("PREFERENCE", 0);

        if (settings.getBoolean("my_first_time", true)) {

            settings.edit().putBoolean("my_first_time", false).apply();
            Intent intent = new Intent(this,IntroActivity.class);
            startActivity(intent);
            finish();

        }else{
            fragmentTransaction.replace(R.id.first_layout,new Fragment_Home(),"hFRAG").commit();
        }

    }





    private View.OnClickListener whatWasClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.fab:
                    fabOnClickProcedure(v);
                    break;
            }

        }
    };


    private void fabOnClickProcedure(View v){
        InformationBaton ib = new InformationBaton(v);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_settings:

                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.first_layout,new SettingFragment(),"sFRAG").addToBackStack("").commit();

                break;

        }



        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }


    }



}
