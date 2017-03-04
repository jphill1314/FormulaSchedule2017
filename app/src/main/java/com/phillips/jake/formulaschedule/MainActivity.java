package com.phillips.jake.formulaschedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<RaceWeekend> schedule;
    public static final String SCHEDULE_KEY = "schedule_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.top_frame_view) != null){
            if(savedInstanceState != null){
                return;
            }

            createSchedule();

            CountdownFragment cdFrag = new CountdownFragment();
            SeasonScheduleFragment ssFrag = new SeasonScheduleFragment();

            Bundle scheduleBundle = new Bundle();
            scheduleBundle.putParcelableArrayList(SCHEDULE_KEY, schedule);
            ssFrag.setArguments(scheduleBundle);
            cdFrag.setArguments(scheduleBundle);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.top_frame_view, cdFrag)
                    .add(R.id.bottom_frame_view, ssFrag)
                    .commit();


        }
    }

    private void createSchedule(){
        schedule = new ArrayList<RaceWeekend>();
        String[] countries = getResources().getStringArray(R.array.country_names);
        int[] fp1Times = getResources().getIntArray(R.array.FP1_Times);
        int[] fp2Times = getResources().getIntArray(R.array.FP2_Times);
        int[] fp3Times = getResources().getIntArray(R.array.FP3_Times);
        int[] qualyTimes = getResources().getIntArray(R.array.Qualy_Times);
        int[] raceTimes = getResources().getIntArray(R.array.Race_Times);


        for(int i = 0; i < countries.length; i++){
            schedule.add(new RaceWeekend(countries[i], countries[i], fp1Times[i], fp2Times[i],
                    fp3Times[i], qualyTimes[i], raceTimes[i]));

        }
    }
}
