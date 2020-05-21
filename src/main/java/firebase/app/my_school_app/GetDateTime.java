package firebase.app.my_school_app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetDateTime {

    Date mDate;
    String time,date,today;

    public GetDateTime() {
        mDate= Calendar.getInstance().getTime();
    }
    public String getTime(){
        DateFormat dateFormat=new SimpleDateFormat("hh:mm:ss:a");
        return dateFormat.format(mDate);
    }
    public String getDate(){
        DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(mDate);
    }
    public String getDay(){
        DateFormat dateFormat=new SimpleDateFormat("dd");
        return dateFormat.format(mDate);
    }
    public String getMonth(){
        DateFormat dateFormat=new SimpleDateFormat("MM");
        return dateFormat.format(mDate);
    }
    public String getYear(){
        DateFormat dateFormat=new SimpleDateFormat("yyyy");
        return dateFormat.format(mDate);
    }
    public String getTimeDate(){
        DateFormat dateFormat=new SimpleDateFormat("hh:mm:ss:a dd/MM/yyyy");
        return dateFormat.format(mDate);
    }
}
