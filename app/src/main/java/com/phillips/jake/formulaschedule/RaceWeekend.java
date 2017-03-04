package com.phillips.jake.formulaschedule;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by Jake on 3/4/2017.
 */

public class RaceWeekend {

    private String country, track;
    private int fp1Time, fp2Time, fp3Time, qulayTime, raceTime;

    public RaceWeekend(String country, String track, int fp1, int fp2, int fp3, int qualy, int race){
        this.country = country;
        this.track = track;
        fp1Time = fp1;
        fp2Time = fp2;
        fp3Time = fp3;
        qulayTime = qualy;
        raceTime = race;
    }

    public String getCountry(){
        return country;
    }

    public String getTrack(){
        return track;
    }

    public int[] getTimes(){
        return new int[] {fp1Time, fp2Time, fp3Time, qulayTime, raceTime};
    }

    public String getEventDates(int start, int end){
        String dates;

        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String startMonth, endMonth, startDay, endDay;
        Calendar local = new GregorianCalendar(TimeZone.getDefault());
        local.setTimeInMillis(start * 1000L);

        startMonth = monthNames[local.get(Calendar.MONTH)];
        startDay = "" + local.get(Calendar.DAY_OF_MONTH);

        local.setTimeInMillis(end * 1000L);

        endMonth = monthNames[local.get(Calendar.MONTH)];
        endDay = "" + local.get(Calendar.DAY_OF_MONTH);

        if(startMonth.equals(endMonth)){
            dates = startMonth + " " + startDay + "-" + endDay;
        }
        else{
            dates = startMonth + " " + startDay + " - " + endMonth + " " + endDay;
        }

        return dates;
    }

}
