package com.phillips.jake.formulaschedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RaceWeekendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race_weekend);

        if(findViewById(R.id.frame_view) != null){
            if(savedInstanceState != null){
                return;
            }

            Intent i = getIntent();
            Bundle bundle = new Bundle();
            if(i.getExtras() != null){
                bundle.putAll(i.getExtras());
            }

            RaceWeekendFragment rwFrag = new RaceWeekendFragment();
            rwFrag.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_view, rwFrag)
                    .commit();
        }
    }
}
