package ies.grupo51.lockedin.models;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("movesensordata")
public class MoveSensorData {
    
    @Id
    private UUID id;

    private UUID inmate_id;
    private Area new_area;

    private static long counter = 0;

    public MoveSensorData() {
        this.id = UUID.randomUUID();
    }

    public MoveSensorData(UUID inmate_id, Area new_area) {
        this.id = UUID.randomUUID();
        this.inmate_id = inmate_id;
        this.new_area = new_area;
    }

    // SETS

    public static void setCounter(long counter) {
        MoveSensorData.counter = counter;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public void setInmate_id(UUID inmate_id) {
        this.inmate_id = inmate_id;
    }
    public void setNew_area(Area new_area) {
        this.new_area = new_area;
    }

    // GETS

    public static long getCounter() {
        return counter;
    }
    public UUID getId() {
        return id;
    }
    public UUID getInmate_id() {
        return inmate_id;
    }
    public Area getNew_area() {
        return new_area;
    }

    @Override
    public String toString() {
        return String.format(
            "MoveSensorData [ID: %s, Inmate ID: %s, New Area: %d]", 
            this.id.toString(), this.inmate_id.toString(), this.new_area);
    }
}
