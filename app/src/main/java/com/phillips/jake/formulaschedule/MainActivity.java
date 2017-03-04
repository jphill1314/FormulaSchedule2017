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
        int startOfDaylightSavings = 1490490000;
        int endOfDaylightSavings = 1509242400;

        schedule = new ArrayList<RaceWeekend>();
        String[] countries = getResources().getStringArray(R.array.country_names);
        int[] fp1Times = getResources().getIntArray(R.array.FP1_Times);
        int[] fp2Times = getResources().getIntArray(R.array.FP2_Times);
        int[] fp3Times = getResources().getIntArray(R.array.FP3_Times);
        int[] qualyTimes = getResources().getIntArray(R.array.Qualy_Times);
        int[] raceTimes = getResources().getIntArray(R.array.Race_Times);

        int fp1, fp2, fp3, qualy, race;
        for(int i = 0; i < countries.length; i++){
            if(fp1Times[i] > startOfDaylightSavings && fp1Times[i] < endOfDaylightSavings){
                fp1 = fp1Times[i] - 3600;
            }
            else{
                fp1 = fp1Times[i];
            }
            if(fp2Times[i] > startOfDaylightSavings && fp2Times[i] < endOfDaylightSavings){
                fp2 = fp2Times[i] - 3600;
            }
            else{
                fp2 = fp2Times[i];
            }
            if(fp3Times[i] > startOfDaylightSavings && fp3Times[i] < endOfDaylightSavings){
                fp3 = fp3Times[i] - 3600;
            }
            else{
                fp3 = fp3Times[i];
            }
            if(qualyTimes[i] > startOfDaylightSavings && qualyTimes[i] < endOfDaylightSavings){
                qualy = qualyTimes[i] - 3600;
            }
            else{
                qualy = qualyTimes[i];
            }
            if(raceTimes[i] > startOfDaylightSavings && raceTimes[i] < endOfDaylightSavings){
                race = raceTimes[i] - 3600;
            }
            else{
                race = raceTimes[i];
            }

            schedule.add(new RaceWeekend(countries[i], countries[i], fp1, fp2,
                    fp3, qualy, race));

        }
    }
}
