package com.phillips.jake.formulaschedule;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jake on 3/4/2017.
 */

public class SeasonScheduleAdapter extends RecyclerView.Adapter<SeasonScheduleAdapter.ViewHolder> {

    private static ArrayList<RaceWeekend> schedule;
    public static final String EXTRA_COUNTRY = "country";
    public static final String EXTRA_TRACK = "track";
    public static final String EXTRA_TIME = "time";

    public SeasonScheduleAdapter(ArrayList<RaceWeekend> schedule){
        this.schedule = schedule;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_season, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RaceWeekend race = schedule.get(position);

        holder.tvCountry.setText(race.getCountry());
        holder.tvDates.setText(race.getEventDates());

        switch(race.getCountry()){
            case "Australia": holder.flagIcon.setImageResource(R.drawable.australia);
                break;
            case "Austria": holder.flagIcon.setImageResource(R.drawable.austria);
                break;
            case "Azerbaijan": holder.flagIcon.setImageResource(R.drawable.azerbaijan);
                break;
            case "Bahrain": holder.flagIcon.setImageResource(R.drawable.bahrain);
                break;
            case "Belgium": holder.flagIcon.setImageResource(R.drawable.belgium);
                break;
            case "Brazil": holder.flagIcon.setImageResource(R.drawable.brazil);
                break;
            case "Canada": holder.flagIcon.setImageResource(R.drawable.canada);
                break;
            case "China": holder.flagIcon.setImageResource(R.drawable.china);
                break;
            case "United Kingdom": holder.flagIcon.setImageResource(R.drawable.uk);
                break;
            case "Germany": holder.flagIcon.setImageResource(R.drawable.germany);
                break;
            case "Hungary": holder.flagIcon.setImageResource(R.drawable.hungary);
                break;
            case "Italy": holder.flagIcon.setImageResource(R.drawable.italy);
                break;
            case "Japan": holder.flagIcon.setImageResource(R.drawable.japan);
                break;
            case "Malaysia": holder.flagIcon.setImageResource(R.drawable.malaysia);
                break;
            case "Mexico": holder.flagIcon.setImageResource(R.drawable.mexico);
                break;
            case "Monaco": holder.flagIcon.setImageResource(R.drawable.monaco);
                break;
            case "Russia": holder.flagIcon.setImageResource(R.drawable.russia);
                break;
            case "Singapore": holder.flagIcon.setImageResource(R.drawable.singapore);
                break;
            case "Spain": holder.flagIcon.setImageResource(R.drawable.spain);
                break;
            case "Abu Dhabi": holder.flagIcon.setImageResource(R.drawable.uae);
                break;
            case "United States": holder.flagIcon.setImageResource(R.drawable.usa);
        }
    }


    @Override
    public int getItemCount() {
        return schedule.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final ImageView flagIcon;
        public final TextView tvCountry;
        public final TextView tvDates;

        public ViewHolder(View view){
            super(view);
            flagIcon = (ImageView) view.findViewById(R.id.country_flag);
            tvCountry = (TextView) view.findViewById(R.id.country_name);
            tvDates = (TextView) view.findViewById(R.id.dates);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    RaceWeekend rw = getWeekend(tvCountry.getText().toString());
                    String country = rw.getCountry();
                    String track = rw.getTrack();
                    int[] times = rw.getTimes();

                    Context context = v.getContext();
                    Intent i = new Intent(context, RaceWeekendActivity.class)
                            .putExtra(EXTRA_COUNTRY, country)
                            .putExtra(EXTRA_TRACK, track)
                            .putExtra(EXTRA_TIME, times);
                    context.startActivity(i);
                }
            });
        }

        public RaceWeekend getWeekend(String name){
            for(RaceWeekend rw : schedule){
                if(rw.getCountry().equals(name)){
                    return rw;
                }
            }
            return null;
        }
    }

}
