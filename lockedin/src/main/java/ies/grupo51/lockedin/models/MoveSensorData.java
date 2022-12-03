package ies.grupo51.lockedin.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("movesensordata")
public class MoveSensorData {
    
    @Id
    private long id;

    private long inmate_id;
    private MoveSensor move_sensor;

    private static long counter = 0;

    public MoveSensorData() {
        this.id = 0;
    }

    public MoveSensorData(long id, long inmate_id, MoveSensor move_sensor) {
        this.id = id;
        this.inmate_id = inmate_id;
        this.move_sensor = move_sensor;
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
    public void setMove_sensor(MoveSensor move_sensor) {
        this.move_sensor = move_sensor;
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
    public MoveSensor getMove_sensor() {
        return move_sensor;
    }

    @Override
    public String toString() {
        return String.format(
            "MoveSensorData [ID: %d, Inmate ID: %d, New MoveSensor: %d]", 
            this.id, this.inmate_id, this.move_sensor);
    }
}
