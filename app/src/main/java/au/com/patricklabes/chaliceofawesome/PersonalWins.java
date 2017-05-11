package au.com.patricklabes.chaliceofawesome;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by patrick on 4/03/2017.
 */

public class PersonalWins {

    private String date, accomplishments;
    private int visits;

    public void PersonalWinsCreate(String accomplishments){

        this.accomplishments = accomplishments;
        this.visits = setVisits();
        this.date = setDate();
    }

    public void PersonlWinsContinue(String accomplishments, String date, int vists){

        this.accomplishments = accomplishments;
        this.date = date;
        this.visits = vists;
    }


    public String getDate() {
        return date;
    }

    private String setDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
       String newDate = sdf.format(new Date());
        this.date = newDate;
        return newDate;
    }

    public int getVisits() {
         return visits;
    }

    private int setVisits() {
        return 0;
    }

    public void setVisits(int i){
        if(i >= 10){
            this.visits = 10;
        }else
        this.visits = i;
    }



    public String getAccomplishments() {
        return accomplishments;
    }

    private void setAccomplishments(String accomplishments) {
        this.accomplishments = accomplishments;
    }





}
