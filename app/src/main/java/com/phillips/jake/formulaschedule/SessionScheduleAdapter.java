package com.phillips.jake.formulaschedule;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Jake on 3/4/2017.
 */

public class SessionScheduleAdapter extends RecyclerView.Adapter<SessionScheduleAdapter.ViewHolder> {

    private int[] sessionTimes;

    public SessionScheduleAdapter(int[] times){
        sessionTimes = times;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_session, parent, false);
        return new SessionScheduleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SessionScheduleAdapter.ViewHolder holder, int position) {
        switch (position){
            case 0:
                holder.name.setText(R.string.fp1);
                break;
            case 1:
                holder.name.setText(R.string.fp2);
                break;
            case 2:
                holder.name.setText(R.string.fp3);
                break;
            case 3:
                holder.name.setText(R.string.qualy);
                break;
            case 4:
                holder.name.setText(R.string.race);
                break;
        }

        holder.date.setText(getEventDate(sessionTimes[position]));
        holder.time.setText(getEventTime(sessionTimes[position]));
    }

    @Override
    public int getItemCount() {
        return sessionTimes.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView name, date, time;

        public ViewHolder(View view){
            super(view);
            name = (TextView) view.findViewById(R.id.session_name);
            date = (TextView) view.findViewById(R.id.session_date);
            time = (TextView) view.findViewById(R.id.session_time);
        }
    }

    public String getEventDate(int time){
        int start = time;

        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String month, day;
        Calendar local = new GregorianCalendar(TimeZone.getDefault());
        local.setTimeInMillis(start * 1000L);

        month = monthNames[local.get(Calendar.MONTH)];
        day = "" + local.get(Calendar.DAY_OF_MONTH);

        return month + " " + day;
    }

    public String getEventTime(int time){
        int start = time;

        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String month, day;
        Calendar local = new GregorianCalendar(TimeZone.getDefault());
        local.setTimeInMillis(start * 1000L);

        int hour = local.get(Calendar.HOUR);
        int min = local.get(Calendar.MINUTE);
        int am = local.get(Calendar.AM_PM);

        if(am == 0){
            if (min < 10){
                return hour + ":0" + min + " AM";
            }
            else{
                return hour + ":" + min + " AM";
            }
        }
        else{
            if (min < 10){
                return hour + ":0" + min + " PM";
            }
            else{
                return hour + ":" + min + " PM";
            }
        }
    }
}
