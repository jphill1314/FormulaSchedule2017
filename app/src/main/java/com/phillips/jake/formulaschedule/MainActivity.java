package com.phillips.jake.formulaschedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    RaceWeekend[] schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.top_frame_view) != null){
            if(savedInstanceState != null){
                return;
            }

            CountdownFragment cdFrag = new CountdownFragment();
            SeasonScheduleFragment ssFrag = new SeasonScheduleFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.top_frame_view, cdFrag)
                    .add(R.id.bottom_frame_view, ssFrag)
                    .commit();

            createSchedule();
        }
    }

    private void createSchedule(){
        String[] countries = getResources().getStringArray(R.array.country_names);
        int[] fp1Times = getResources().getIntArray(R.array.FP1_Times);
        int[] fp2Times = getResources().getIntArray(R.array.FP2_Times);
        int[] fp3Times = getResources().getIntArray(R.array.FP3_Times);
        int[] qualyTimes = getResources().getIntArray(R.array.Qualy_Times);
        int[] raceTimes = getResources().getIntArray(R.array.Race_Times);

        schedule = new RaceWeekend[countries.length];

        for(int i = 0; i < schedule.length; i++){
            schedule[i] = new RaceWeekend(countries[i], countries[i], fp1Times[i], fp2Times[i],
                    fp3Times[i], qualyTimes[i], raceTimes[i]);
        }
    }
}
