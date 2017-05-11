package au.com.patricklabes.chaliceofawesome;

import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.geniusforapp.fancydialog.FancyAlertDialog;

/**
 * Created by patrick on 17/03/2017.
 */

public class Fragment_Home extends Fragment {

    TextView textView1;
    ImageView chalicImage;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v;

        v = inflater.inflate(R.layout.fragment_home, container, false);
        chalicImage = (ImageView)v.findViewById(R.id.chaliceImage);
        chalicImage.setOnLongClickListener(chaliceLongClick);





        //Data debug info

        InformationBaton ib = new InformationBaton();



        return v;
    }


    private View.OnLongClickListener chaliceLongClick = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {


            InformationBaton ib = new InformationBaton();
            Long size = ib.getDataBaseSizeLong(v.getContext());


            Animation shake = AnimationUtils.loadAnimation(v.getContext(), R.anim.shake );
            v.findViewById(R.id.chaliceImage).startAnimation(shake);
            if(size != 0 ){
                displayAchievement(v);
            }else{

            }

            return false;
        }
    };


    private void displayAchievement(View v){
        InformationBaton ib = new InformationBaton();
        PersonalWins accomplishment = ib.getRandomAccomplishment(v);

        FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(v.getContext())
                //.setTextTitle(accomplishment.getDate())
                .setTextSubTitle(accomplishment.getAccomplishments())
                .setBody(accomplishment.getDate())
                
                .setPositiveButtonText("Awesome")
                .setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
                    @Override
                    public void OnClick(View view, Dialog dialog) {
                        dialog.dismiss();
                    }
                })

                .build();
        alert.show();

        }



    private void displayFailedAchievement(){

    }



}
