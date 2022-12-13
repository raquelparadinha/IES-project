package ies.grupo51.lockedin.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("moveSensorLog")
public class MoveSensorLog {
    
    @Id
    private long id;

    private long inmateId;
    private long sensorId;

    private static long counter = 0;

    public MoveSensorLog() {
        this.id = 0;
    }

    public MoveSensorLog(long id, long inmateId, long sensorId) {
        this.id = id;
        this.inmateId = inmateId;
        this.sensorId = sensorId;
    }

    // SETS

    public static void setCounter(long counter) {
        MoveSensorLog.counter = counter;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setInmateId(long inmateId) {
        this.inmateId = inmateId;
    }
    public void setSensorId(long sensorId) {
        this.sensorId = sensorId;
    }

    // GETS

    public static long getCounter() {
        return counter;
    }
    public long getId() {
        return id;
    }
    public long getInmateId() {
        return inmateId;
    }
    public long getSensorId() {
        return sensorId;
    }

    @Override
    public String toString() {
        return String.format(
            "MoveSensorLog [ID: %d, Inmate ID: %d, Sensor ID: %d]", 
            this.id, this.inmateId, this.sensorId);
    }
}
