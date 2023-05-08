package net.kcci.homeIot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {
    MainActivity mainActivity;
    TextView textViewIllu;
    TextView textViewTem;
    TextView textViewHum;
    Button buttonCondition;
    ImageView imageViewLed;
    ImageView imageViewBlinds;
    ImageView imageViewAir;

    Switch switchLed;
    Switch switchBlinds;
    Switch switchAir;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        imageViewLed = view.findViewById(R.id.imageViewLed);
        imageViewBlinds = view.findViewById(R.id.imageViewBlinds);
        imageViewAir = view.findViewById(R.id.imageViewAir);
        switchLed = (Switch)view.findViewById(R.id.switchLed);
        switchBlinds = (Switch)view.findViewById(R.id.switchBlinds);
        switchAir = (Switch)view.findViewById(R.id.switchAir);
        mainActivity = (MainActivity)getActivity();
        buttonCondition = view.findViewById(R.id.buttonCondition);
        textViewIllu = view.findViewById(R.id.textViewIllu);
        textViewTem = view.findViewById(R.id.textViewTem);
        textViewHum = view.findViewById(R.id.textViewHum);

        switchLed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ClientThread.socket != null){
                    if(switchLed.isChecked()){
                        mainActivity.clientThread.sendData("[KSH_ARD]LEDON\n");
                        switchLed.setChecked(false);
                    }
                    else{
                        mainActivity.clientThread.sendData("[KSH_ARD]LEDOFF\n");
                        switchLed.setChecked(true);
                    }

                }
            }
        });

        switchBlinds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ClientThread.socket != null){
                    if(switchBlinds.isChecked()){
                        mainActivity.clientThread.sendData("[KSH_ARD]BLINDSON\n");
                        switchBlinds.setChecked(false);
                    }

                    else{
                        mainActivity.clientThread.sendData("[KSH_ARD]BLINDSOFF\n");
                        switchBlinds.setChecked(true);
                    }

                }
            }
        });

        switchAir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ClientThread.socket != null){
                    if(switchAir.isChecked()){
                        mainActivity.clientThread.sendData("[KSH_ARD]AIRON");
                        switchAir.setChecked(false);
                    }

                    else{
                        mainActivity.clientThread.sendData("[KSH_ARD]AIROFF");
                        switchAir.setChecked(true);
                    }


                }
            }
        });

        buttonCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ClientThread.socket != null){
                    mainActivity.clientThread.sendData("[KSH_ARD]GETSENSOR");
                }
            }
        });
        return view;
    }
    void recvDataProcess(String strCondition){
        String[] splitLists = strCondition.toString().split("\\[|]|@|\\r");

        if(splitLists[2].equals("LEDON")){
            imageViewLed.setImageResource(R.drawable.led_on);
            switchLed.setChecked(true);
        }
        else if(splitLists[2].equals("LEDOFF")){
            imageViewLed.setImageResource(R.drawable.led_off);
            switchLed.setChecked(false);
        }
        else if(splitLists[2].equals("BLINDSON")){
            imageViewBlinds.setImageResource(R.drawable.blinds_on);
            switchBlinds.setChecked(true);
        }
        else if(splitLists[2].equals("BLINDSOFF")){
            imageViewBlinds.setImageResource(R.drawable.blinds_off);
            switchBlinds.setChecked(false);
        }
        else if(splitLists[2].equals("AIRON")){
            imageViewAir.setImageResource(R.drawable.air_on);
            switchAir.setChecked(true);
        }
        else if(splitLists[2].equals("AIROFF")){
            imageViewAir.setImageResource(R.drawable.air_off);
            switchAir.setChecked(false);
        }
        else if(splitLists[2].equals("SENSOR")){
            textViewIllu.append(splitLists[3]+"%");
            textViewTem.append(splitLists[4]+"C");
            textViewHum.append(splitLists[5]+"%");
        }
    }
}
