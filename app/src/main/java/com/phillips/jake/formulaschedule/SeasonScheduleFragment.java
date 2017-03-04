package com.phillips.jake.formulaschedule;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SeasonScheduleFragment extends Fragment {

    ArrayList<RaceWeekend> schedule;

    public SeasonScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null){
            schedule = getArguments().getParcelableArrayList(MainActivity.SCHEDULE_KEY);
            Log.d("Debug", "This happened!");
        }

        Log.d("Debug", "This happened2!");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_season_schedule, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        SeasonScheduleAdapter adapter = new SeasonScheduleAdapter(schedule);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        return view;
    }

}
