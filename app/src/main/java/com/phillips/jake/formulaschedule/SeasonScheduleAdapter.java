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

    ArrayList<RaceWeekend> schedule;

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
        }
    }
}
