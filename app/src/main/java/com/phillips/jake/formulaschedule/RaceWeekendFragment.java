package com.phillips.jake.formulaschedule;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class RaceWeekendFragment extends Fragment {

    public RaceWeekendFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private RaceWeekend rw;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if(bundle != null){
            rw = new RaceWeekend(bundle.getString(SeasonScheduleAdapter.EXTRA_COUNTRY),
                    bundle.getString(SeasonScheduleAdapter.EXTRA_COUNTRY),
                    bundle.getIntArray(SeasonScheduleAdapter.EXTRA_TIME));
        }

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_race_weekend, container, false);
        populateTopView(view);

        return view;
    }

    private void populateTopView(View view){
        ImageView flagIcon = (ImageView) view.findViewById(R.id.flag_icon);
        TextView countryName = (TextView) view.findViewById(R.id.host_country);
        ImageView trackMap = (ImageView) view.findViewById(R.id.track_map);

        countryName.setText(rw.getCountry());

        switch(rw.getCountry()){
            case "Australia": flagIcon.setImageResource(R.drawable.australia);
                trackMap.setImageResource(R.drawable.circuit_australia);
                break;
            case "Austria": flagIcon.setImageResource(R.drawable.austria);
                trackMap.setImageResource(R.drawable.circuit_austria);
                break;
            case "Azerbaijan": flagIcon.setImageResource(R.drawable.azerbaijan);
                trackMap.setImageResource(R.drawable.circuit_azerbaijan);
                break;
            case "Bahrain": flagIcon.setImageResource(R.drawable.bahrain);
                trackMap.setImageResource(R.drawable.circuit_bahrain);
                break;
            case "Belgium": flagIcon.setImageResource(R.drawable.belgium);
                trackMap.setImageResource(R.drawable.circuit_belgium);
                break;
            case "Brazil": flagIcon.setImageResource(R.drawable.brazil);
                trackMap.setImageResource(R.drawable.circuit_brazil);
                break;
            case "Canada": flagIcon.setImageResource(R.drawable.canada);
                trackMap.setImageResource(R.drawable.circuit_canada);
                break;
            case "China": flagIcon.setImageResource(R.drawable.china);
                trackMap.setImageResource(R.drawable.circuit_china);
                break;
            case "United Kingdom": flagIcon.setImageResource(R.drawable.uk);
                trackMap.setImageResource(R.drawable.circuit_britain);
                break;
            case "Hungary": flagIcon.setImageResource(R.drawable.hungary);
                trackMap.setImageResource(R.drawable.circuit_hungary);
                break;
            case "Italy": flagIcon.setImageResource(R.drawable.italy);
                trackMap.setImageResource(R.drawable.circuit_italy);
                break;
            case "Japan": flagIcon.setImageResource(R.drawable.japan);
                trackMap.setImageResource(R.drawable.circuit_japan);
                break;
            case "Malaysia": flagIcon.setImageResource(R.drawable.malaysia);
                trackMap.setImageResource(R.drawable.circuit_malaysia);
                break;
            case "Mexico": flagIcon.setImageResource(R.drawable.mexico);
                trackMap.setImageResource(R.drawable.circuit_mexico);
                break;
            case "Monaco": flagIcon.setImageResource(R.drawable.monaco);
                trackMap.setImageResource(R.drawable.circuit_monaco);
                break;
            case "Russia": flagIcon.setImageResource(R.drawable.russia);
                trackMap.setImageResource(R.drawable.circuit_russia);
                break;
            case "Singapore": flagIcon.setImageResource(R.drawable.singapore);
                trackMap.setImageResource(R.drawable.circuit_singapore);
                break;
            case "Spain": flagIcon.setImageResource(R.drawable.spain);
                trackMap.setImageResource(R.drawable.circuit_spain);
                break;
            case "Abu Dhabi": flagIcon.setImageResource(R.drawable.uae);
                trackMap.setImageResource(R.drawable.circuit_uae);
                break;
            case "United States": flagIcon.setImageResource(R.drawable.usa);
                trackMap.setImageResource(R.drawable.circuit_usa);
        }
    }

}
