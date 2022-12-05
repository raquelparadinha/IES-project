package ies.grupo51.lockedin.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("movesensordata")
public class MoveSensorData {
    
    @Id
    private long id;

    private long inmateId;
    private long moveSensorId;

    private static long counter = 0;

    public MoveSensorData() {

    }

    public MoveSensorData(long inmateId, long moveSensorId) {
        this.inmateId = inmateId;
        this.moveSensorId = moveSensorId;
    }

    // SETS

    public static void setCounter(long counter) {
        MoveSensorData.counter = counter;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setInmate_id(long inmateId) {
        this.inmateId = inmateId;
    }
    public void setMove_sensor_id(long moveSensorId) {
        this.moveSensorId = moveSensorId;
    }

    // GETS

    public static long getCounter() {
        return counter;
    }
    public long getId() {
        return id;
    }
    public long getInmate_id() {
        return inmateId;
    }
    public long getMove_sensor_id() {
        return moveSensorId;
    }

    @Override
    public String toString() {
        return String.format(
            "MoveSensorData [ID: %d, Inmate ID: %d, New MoveSensorID: %d]", 
            this.id, this.inmateId, this.moveSensorId);
    }
}
