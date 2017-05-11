package au.com.patricklabes.chaliceofawesome;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by patrick on 10/03/2017.
 */

public class InformationBaton {

    View view;

    public InformationBaton(View v) {
        view = v;
        alertBuilder(v);

    }

    public InformationBaton() {

    }


    private void alertBuilder(final View v){
        LayoutInflater factory = LayoutInflater.from(v.getContext());

        final View textEntryView = factory.inflate(R.layout.input_dialog_deed,null);
        final EditText inputAccom = (EditText) textEntryView.findViewById(R.id.editTextAccom);
        inputAccom.setText("", TextView.BufferType.EDITABLE);
        final AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
        alert.setView(textEntryView);
        alert.setPositiveButton("Save",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                               int whichButton) {
                       positiveAlertChecker(inputAccom.getText().toString(), v);
                    }
                }).setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
                        Snackbar.make(v, "Nothing was saved", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                });
        alert.show();



    }

    public PersonalWins getRandomAccomplishment(View v){
        int size = getDataBaseSizeLong(v.getContext()).intValue();
        Random random = new Random();
        int randomInt = random.nextInt((size - 1)+1)+0;
        DatabaseHandler db = new DatabaseHandler(v.getContext());
        PersonalWins pw = db.getSpecificRowById(randomInt);
        return pw;
    }


    private void positiveAlertChecker( String accom, View v) {
        if( accom != null && !accom.isEmpty()) {
            Snackbar.make(v, "Saving Accomplishment...", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            compileAndSave( accom, v);


        }else
            Snackbar.make(v, "Could not save accomplishment", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

    }

    private void compileAndSave( String accom,View v) {
        DatabaseHandler db = new DatabaseHandler(v.getContext());

        PersonalWins pw = new PersonalWins();
        pw.PersonalWinsCreate(accom);

        db.addEntry(pw);

    }

    public void dropDataBase(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        db.dropTable();
    }

    public String getDataBaseSize(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        return Long.toString(db.getDatabaseProfilesCount());
    }
    public Long getDataBaseSizeLong(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        Long size = db.getDatabaseProfilesCount();
        return size;
    }

}
