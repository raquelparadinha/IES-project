package ies.grupo51.lockedin.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("movesensor")
public class MoveSensor {
    
    @Id
    private UUID id;
    private Area entry;
    private Area exit;
    private List<MoveSensorData> logs;

    private static long counter = 0;

    public MoveSensor() {
        this.id = UUID.randomUUID();
        this.logs = new ArrayList<>();
    }
    
    public MoveSensor(String name, Area entry, Area exit) {
        this.id = UUID.randomUUID();
        this.entry = entry;
        this.exit = exit;
        this.logs = new ArrayList<>();
    }

    // GETS

    public UUID getId() {
        return id;
    }
    public Area getEntry() {
        return entry;
    }
    public Area getExit() {
        return exit;
    }
    public List<MoveSensorData> getLogs() {
        return logs;
    }
    public static long getCounter() {
        return counter;
    }

    // SETS

    public void setId(UUID id) {
        this.id = id;
    }
    public void setEntry(Area entry) {
        this.entry = entry;
    }
    public void setExit(Area exit) {
        this.exit = exit;
    }
    public void setLogs(List<MoveSensorData> logs) {
        this.logs = logs;
    }
    public static void setCounter(long counter) {
        MoveSensor.counter = counter;
    }

    // CUSTOM FUNCTIONS FOR MODEL

    public void addNewLog(MoveSensorData log){
        this.logs.add(log);
    }

    public MoveSensorData getLastLog(){
        return this.logs.get(-1);
    }
    
    @Override
    public String toString() {
        return String.format(
            "MoveSensor [ID: %s, Entry: %s, Exit: %s]", 
            this.id.toString(), this.entry, this.exit);
    }

}
