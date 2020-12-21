package android.example.idp;

import android.media.Image;

import java.sql.Time;

public class ReminderObject {
    String medicationName;
    Time time;
    Image Image;
    public ReminderObject(String medicationName, Time time, Image Image){
        this.medicationName=medicationName;
        this.time=time;
        this.Image=Image;
    }
    public String getString(){
        return medicationName;
    }
}
