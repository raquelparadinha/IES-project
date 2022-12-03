package ies.grupo51.lockedin.models;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("inmatejob")
public class Workstation {

    @Id
    private UUID id;

    private Area working_station;
    private String description;
    private Date start_time;
    private int duration;
    private Date end_time;

    private static long counter = 100;

    public Workstation(){
        this.id = UUID.randomUUID();
    }

    public Workstation(Area working_station, Date start_time, int duration, Date end_time, String description) {
        this.id = UUID.randomUUID();
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
    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }
    public void setWorking_station(Area working_station) {
        this.working_station = working_station;
    }
    public static void setCounter(long counter) {
        Workstation.counter = counter;
    }
    
    // GETS

    public String getDescription() {
        return description;
    }
    public int getDuration() {
        return duration;
    }
    public Date getEnd_time() {
        return end_time;
    }
    public UUID getId() {
        return id;
    }
    public Date getStart_time() {
        return start_time;
    }
    public Area getWorking_station() {
        return working_station;
    }
    public static long getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return String.format(
            "InmateJob [ID: %s, Working Station: %s, Start Time: %s, Duration: %d, End Time: %s, Description: %s]", 
            this.id.toString(), this.working_station.toString(), this.start_time.toString(), this.duration, this.end_time.toString(), this.description);
    }
}
