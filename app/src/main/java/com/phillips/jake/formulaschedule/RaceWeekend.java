package com.phillips.jake.formulaschedule;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by Jake on 3/4/2017.
 */

public class RaceWeekend implements Parcelable{

    private String country, track;
    private int fp1Time, fp2Time, fp3Time, qulayTime, raceTime, flag;

    public RaceWeekend(String country, String track, int fp1, int fp2, int fp3, int qualy, int race){
        this.country = country;
        this.track = track;
        fp1Time = fp1;
        fp2Time = fp2;
        fp3Time = fp3;
        qulayTime = qualy;
        raceTime = race;
    }

    public RaceWeekend(String country, String track, int[] times){
        this.country = country;
        this.track = track;
        fp1Time = times[0];
        fp2Time = times[1];
        fp3Time = times[2];
        qulayTime = times[3];
        raceTime = times[4];
    }

    public RaceWeekend(Parcel data){
        String[] d = new String[7];
        data.readStringArray(d);

        country = d[0];
        track = d[1];
        fp1Time = Integer.parseInt(d[2]);
        fp2Time = Integer.parseInt(d[3]);
        fp3Time = Integer.parseInt(d[4]);
        qulayTime = Integer.parseInt(d[5]);
        raceTime = Integer.parseInt(d[6]);
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

    public String getEventDates(){
        String dates;

        int start = fp1Time;
        int end = raceTime;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[]{country, track, ""+fp1Time, ""+fp2Time,
                ""+fp3Time, ""+qulayTime, ""+raceTime});
    }

    public static final Creator<RaceWeekend> CREATOR = new Creator<RaceWeekend>() {
        @Override
        public RaceWeekend createFromParcel(Parcel in) {
            return new RaceWeekend(in);
        }

        @Override
        public RaceWeekend[] newArray(int size) {
            return new RaceWeekend[size];
        }
    };
}
