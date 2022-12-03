package ies.grupo51.lockedin.models;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("movesensordata")
public class MoveSensorData {
    
    @Id
    private UUID id;

    private UUID inmate_id;
    private MoveSensor move_sensor;

    private static long counter = 0;

    public MoveSensorData() {
        this.id = UUID.randomUUID();
    }

    public MoveSensorData(UUID inmate_id, MoveSensor move_sensor) {
        this.id = UUID.randomUUID();
        this.inmate_id = inmate_id;
        this.move_sensor = move_sensor;
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
    public void setMove_sensor(MoveSensor move_sensor) {
        this.move_sensor = move_sensor;
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
    public MoveSensor getMove_sensor() {
        return move_sensor;
    }

    @Override
    public String toString() {
        return String.format(
            "MoveSensorData [ID: %s, Inmate ID: %d, New MoveSensor: %d]", 
            this.id.toString(), this.inmate_id, this.move_sensor);
    }
}
