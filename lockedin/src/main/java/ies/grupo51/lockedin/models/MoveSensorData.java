package ies.grupo51.lockedin.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MoveSensorData {
    
    @Id
    private long id;

    private long inmate_id;
    private Area new_area;

    private static long counter = 0;

    public MoveSensorData() {
        this.id = MoveSensorData.counter++;
    }

    public MoveSensorData(long inmate_id, Area new_area) {
        this.id = MoveSensorData.counter++;
        this.inmate_id = inmate_id;
        this.new_area = new_area;
    }

    // SETS

    public static void setCounter(long counter) {
        MoveSensorData.counter = counter;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setInmate_id(long inmate_id) {
        this.inmate_id = inmate_id;
    }
    public void setNew_area(Area new_area) {
        this.new_area = new_area;
    }

    // GETS

    public static long getCounter() {
        return counter;
    }
    public long getId() {
        return id;
    }
    public long getInmate_id() {
        return inmate_id;
    }
    public Area getNew_area() {
        return new_area;
    }

    @Override
    public String toString() {
        return String.format(
            "MoveSensorData [ID: %d, Inmate ID: %d, New Area: %d]", 
            this.id, this.inmate_id, this.new_area);
    }
}
