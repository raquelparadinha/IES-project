package ies.grupo51.lockedin.models;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("movesensor")
public class MoveSensor {
    
    @Id
    private long id;
    private String name;
    private Set<Area> connecting_areas;
    private ArrayList<MoveSensorData> logs;

    private static long counter = 0;

    public MoveSensor() {
        this.id = MoveSensor.counter++;
    }
    
    public MoveSensor(String name, Set<Area> connecting_areas) {
        this.id = MoveSensor.counter++;
        this.name = name;
        this.connecting_areas = connecting_areas;
        this.logs = new ArrayList<>();
    }

    // GETS

    public Set<Area> getConnectingAreas() {
        return connecting_areas;
    }
    public long getId() {
        return id;
    }
    public ArrayList<MoveSensorData> getLogs() {
        return logs;
    }
    public String getName() {
        return name;
    }

    // SETS

    public void setConnectingAreas(Set<Area> connecting_areas) {
        this.connecting_areas = connecting_areas;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setLogs(ArrayList<MoveSensorData> logs) {
        this.logs = logs;
    }
    public void setName(String name) {
        this.name = name;
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
            "MoveSensor [ID: %d, Name: %d, Connecting Areas: %d]", 
            this.id, this.name, this.connecting_areas);
    }

}
