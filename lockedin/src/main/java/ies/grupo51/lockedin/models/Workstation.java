package ies.grupo51.lockedin.models;

import java.text.DateFormat;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("inmatejob")
public class Workstation {

    @Id
    private long id;

    private Area working_station;
    private String description;
    private DateFormat start_time;
    private int duration;
    private DateFormat end_time;

    private static long counter = 100;

    public Workstation(){
        this.id = Workstation.counter++;
    }

    public Workstation(Area working_station, DateFormat start_time, int duration, DateFormat end_time, String description) {
        this.id = Workstation.counter++;
        this.working_station = working_station;
        this.start_time = start_time;
        this.duration = duration;
        this.end_time = end_time;
        this.description = description;
    }

    // SETS

    public void setDescription(String description) {
        this.description = description;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void setEnd_time(DateFormat end_time) {
        this.end_time = end_time;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setStart_time(DateFormat start_time) {
        this.start_time = start_time;
    }
    public void setWorking_station(Area working_station) {
        this.working_station = working_station;
    }
    
    // GETS

    public String getDescription() {
        return description;
    }
    public int getDuration() {
        return duration;
    }
    public DateFormat getEnd_time() {
        return end_time;
    }
    public long getId() {
        return id;
    }
    public DateFormat getStart_time() {
        return start_time;
    }
    public Area getWorking_station() {
        return working_station;
    }

    @Override
    public String toString() {
        return String.format(
            "InmateJob [ID: %d, Working Station: %s, Start Time: %s, Duration: %d, End Time: %s, Description: %s]", 
            this.id, this.working_station.toString(), this.start_time.toString(), this.duration, this.end_time.toString(), this.description);
    }
}
