package au.com.patricklabes.chaliceofawesome;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class SettingFragment extends Fragment implements View.OnClickListener {

    Button clearAllButton, viewSplashScreenButton;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fragment_setting, container, false);

        clearAllButton = (Button)v.findViewById(R.id.clearAllButton);
        clearAllButton.setOnClickListener(this);

        viewSplashScreenButton = (Button)v.findViewById(R.id.viewSplashScreenButton);
        viewSplashScreenButton.setOnClickListener(this);


        return v;
    }

    public void onClick(final View v){


        switch (v.getId()){
            case R.id.clearAllButton:
                clearAll(v);
                break;
            case R.id.viewSplashScreenButton:
                viewSplashScreen(v);
                break;

        }



    }


    public void clearAll(final View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Delete All Records")
                .setMessage("Are you sure you want to delete all the greatness")
                .setPositiveButton("Delete All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        InformationBaton ib = new InformationBaton();
                        ib.dropDataBase(v.getContext());
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }

    public void viewSplashScreen(View v){
        Intent intent = new Intent(v.getContext(),IntroActivity.class);
        startActivity(intent);
    }


}