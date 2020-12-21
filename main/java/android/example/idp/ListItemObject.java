package android.example.idp;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class ListItemObject {
    String name,imageId;
    Integer count;
    boolean active;
    List<Time> timings= new ArrayList<Time>();
    public ListItemObject() {
        Time time=new Time(0,0,0);
        this.name ="pill";
        this.imageId = " ";
        this.count = 0;
        this.active =false ;
        this.timings.add(0,time);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImageId() {
        return imageId;
    }
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public Time getTiming(Integer index) {
        return timings.get(index);
    }
    public void setTimings(Integer index,Time time) {
        this.timings.set(index,time);
    }
}
