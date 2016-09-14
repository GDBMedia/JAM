package net.gdbmedia.jam.utils;

import net.gdbmedia.jam.Constants;
import net.gdbmedia.jam.R;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Laker77 on 9/12/2016.
 */
public class DateUtils {
    public static String formatDate(String date, String source){
        String formattedDate = date;
        try{
            SimpleDateFormat sdfSource = new SimpleDateFormat(source);
            Date newdate = null;
            try {
                newdate = sdfSource.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            formattedDate = new SimpleDateFormat(Constants.DATE_FORMAT_OUTPUT_YEAR).format(newdate);


        }catch(NullPointerException e){
            e.printStackTrace();
        }

        return formattedDate;
    }
    public static String formatDate(long unix){
        Date date = new Date(unix);
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_OUTPUT_YEAR);
        return sdf.format(date);
    }
    public static long setToUnix(String date, String source){
        long timestamp = 0;
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat(source);
            Date parsedDate = dateFormat.parse(date);
            timestamp = parsedDate.getTime();
        }catch(Exception e){//this generic but you can control another types of exception
        }
      return timestamp;
    }
}
