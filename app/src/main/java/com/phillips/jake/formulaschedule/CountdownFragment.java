package com.phillips.jake.formulaschedule;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class CountdownFragment extends Fragment {

    ArrayList<RaceWeekend> schedule;

    public CountdownFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (getArguments() != null){
            schedule = getArguments().getParcelableArrayList(MainActivity.SCHEDULE_KEY);
        }

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_countdown, container, false);

        populateView(view);

        return view;
    }

    private String nextSession, nextRaceCountry;
    private long timeToNextRace;

    private void populateView(View rootView){
        nextSession();

        TextView tvCountry = (TextView) rootView.findViewById(R.id.next_race_country);
        TextView tvSession = (TextView) rootView.findViewById(R.id.next_session_name);

        final TextView tvHours = (TextView) rootView.findViewById(R.id.num_hours_to_next_session);
        final TextView tvMins = (TextView) rootView.findViewById(R.id.num_min_to_next_session);
        final TextView tvSecs = (TextView) rootView.findViewById(R.id.num_seconds_to_next_session);
        final TextView tvDays = (TextView) rootView.findViewById(R.id.num_days_to_next_session);

        tvCountry.setText(nextRaceCountry);
        tvSession.setText(nextSession);

        new CountDownTimer(timeToNextRace, 1000){
            public void onTick(long millSecondsLeft){
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

            public void onFinish(){

            }
        }.start();
    }

    private void nextSession(){
        Calendar current = Calendar.getInstance();
        Calendar race = Calendar.getInstance();

        for(RaceWeekend rw : schedule){
            int[] times = rw.getTimes();
            race.setTimeInMillis(times[4] * 1000L);
            if(current.compareTo(race) < 0){
                nextRaceCountry = rw.getCountry();

                race.setTimeInMillis(times[0] * 1000L);
                if(current.compareTo(race) < 0){
                    timeToNextRace = (race.getTimeInMillis() - current.getTimeInMillis());
                    nextSession = "FP1";
                    //createNotificationAlarm(race.getTimeInMillis());
                    createNotificationAlarm(current.getTimeInMillis() + 15 * 1000);

                    SharedPreferences pref = getActivity().getSharedPreferences(getContext().getString(R.string.Shared_Pref_Key), Context.MODE_PRIVATE);
                    SharedPreferences.Editor prefEdit = pref.edit();
                    prefEdit.putString(getContext().getString(R.string.Shared_Pref_Country), nextRaceCountry);
                    prefEdit.putString(getContext().getString(R.string.Shared_Pref_Session), nextSession);

                    return;
                }

                race.setTimeInMillis(times[1] * 1000L);
                if(current.compareTo(race) < 0){
                    timeToNextRace = (race.getTimeInMillis() - current.getTimeInMillis());
                    nextSession = "FP2";
                    createNotificationAlarm(race.getTimeInMillis());
                    return;
                }

                race.setTimeInMillis(times[2] * 1000L);
                if(current.compareTo(race) < 0){
                    timeToNextRace = (race.getTimeInMillis() - current.getTimeInMillis());
                    nextSession = "FP3";
                    createNotificationAlarm(race.getTimeInMillis());
                    return;
                }

                race.setTimeInMillis(times[3] * 1000L);
                if(current.compareTo(race) < 0){
                    timeToNextRace = (race.getTimeInMillis() - current.getTimeInMillis());
                    nextSession = "Qualifying";
                    createNotificationAlarm(race.getTimeInMillis());
                    return;
                }

                race.setTimeInMillis(times[4] * 1000L);
                timeToNextRace = (race.getTimeInMillis() - current.getTimeInMillis());
                nextSession = "Race";
                createNotificationAlarm(race.getTimeInMillis());
                return;

            }
        }
    }

    private void createNotificationAlarm(long time){
        Intent intent = new Intent(getContext(), SessionNotification.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(getContext(), 0, intent ,0);

        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pIntent);
    }
}
