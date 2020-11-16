package com.loysc.zzangco.kirikiri_snu.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class WeekendFinder {
    private Calendar cal = null;
    private int year = -1;

    private ArrayList<Calendar> weekendList = null;

    public WeekendFinder(int year){
        this.year = year;

        cal = Calendar.getInstance();
        try{
            // Sets the date to the 1st day of the 1st month of the specified year
            cal.setTime(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/" + this.year));
        }catch(java.text.ParseException e){
            System.err.println("Error parsing date: " + e.getMessage());
            e.printStackTrace();
        }

        weekendList = new ArrayList(53);
    }

    public ArrayList<Calendar> findWeekends(){
        // The while loop ensures that you are only checking dates in the specified year
        Calendar tmp;
        while(cal.get(Calendar.YEAR) == this.year){
            // The switch checks the day of the week for Saturdays and Sundays
            switch(cal.get(Calendar.DAY_OF_WEEK)){
                case Calendar.SATURDAY:
                case Calendar.SUNDAY:
                    tmp = (Calendar) cal.clone();
                    weekendList.add(tmp);
                    break;
            }
            // Increment the day of the year for the next iteration of the while loop
            cal.add(Calendar.DAY_OF_YEAR, 1);
        }

        return  weekendList;
    }


}
