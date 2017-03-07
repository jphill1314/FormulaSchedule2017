package com.phillips.jake.formulaschedule;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;


public class RaceWeekendFragment extends Fragment {

    public RaceWeekendFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private RaceWeekend rw;
    private long timeToNextRace;
    private String nextSession;

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

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        SessionScheduleAdapter adapter = new SessionScheduleAdapter(rw.getTimes());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void populateTopView(View view){
        nextSession();
        ImageView flagIcon = (ImageView) view.findViewById(R.id.flag_icon);
        TextView countryName = (TextView) view.findViewById(R.id.host_country);
        ImageView trackMap = (ImageView) view.findViewById(R.id.track_map);
        TextView nextSess = (TextView) view.findViewById(R.id.cd_session);

        countryName.setText(rw.getCountry());
        nextSess.setText(nextSession);

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

        final TextView tvHours = (TextView) view.findViewById(R.id.num_hours_to_next_session);
        final TextView tvMins = (TextView) view.findViewById(R.id.num_min_to_next_session);
        final TextView tvSecs = (TextView) view.findViewById(R.id.num_seconds_to_next_session);
        final TextView tvDays = (TextView) view.findViewById(R.id.num_days_to_next_session);
        LinearLayout cd = (LinearLayout) view.findViewById(R.id.cd_layout);

        if(timeToNextRace > 0) {
            cd.setVisibility(View.VISIBLE);
            new CountDownTimer(timeToNextRace, 1000) {
                public void onTick(long millSecondsLeft) {
                    long days = (millSecondsLeft / (1000 * 3600 * 24));
                    millSecondsLeft -= days * 24 * 3600 * 1000;
                    long hours = (millSecondsLeft / (1000 * 3600));
                    millSecondsLeft -= hours * 3600 * 1000;
                    long minutes = (millSecondsLeft / (1000 * 60));
                    millSecondsLeft -= minutes * 60 * 1000;
                    long seconds = (millSecondsLeft / (1000));

                    tvDays.setText(days + "");
                    tvHours.setText(hours + "");
                    tvMins.setText(minutes + "");
                    tvSecs.setText(seconds + "");
                }

                public void onFinish() {

                }
            }.start();
        }
        else{
            cd.setVisibility(View.GONE);
        }
    }

    private void nextSession(){
        Calendar current = Calendar.getInstance();
        Calendar race = Calendar.getInstance();

        int[] times = rw.getTimes();

        race.setTimeInMillis(times[0] * 1000L);
        if(current.compareTo(race) < 0){
            timeToNextRace = (race.getTimeInMillis() - current.getTimeInMillis());
            nextSession = "FP1";
            return;
        }

        race.setTimeInMillis(times[1] * 1000L);
        if(current.compareTo(race) < 0){
            timeToNextRace = (race.getTimeInMillis() - current.getTimeInMillis());
            nextSession = "FP2";
            return;
        }

        race.setTimeInMillis(times[2] * 1000L);
        if(current.compareTo(race) < 0){
            timeToNextRace = (race.getTimeInMillis() - current.getTimeInMillis());
            nextSession = "FP3";
            return;
        }

        race.setTimeInMillis(times[3] * 1000L);
        if(current.compareTo(race) < 0){
            timeToNextRace = (race.getTimeInMillis() - current.getTimeInMillis());
            nextSession = "Qualifying";
            return;
        }

        race.setTimeInMillis(times[4] * 1000L);
        if(current.compareTo(race) < 0) {
            timeToNextRace = (race.getTimeInMillis() - current.getTimeInMillis());
            nextSession = "Race";
            return;
        }

        timeToNextRace = -1;
    }
}
