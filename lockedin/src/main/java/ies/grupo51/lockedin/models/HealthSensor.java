package ies.grupo51.lockedin.models;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("healthsensor")
public class HealthSensor {
    
    @Id
    private long id;
    private String name;
    private Area area;
    private ArrayList<HealthSensorData> logs;

    private static long counter = 0;

    public HealthSensor() {
        this.id = HealthSensor.counter++;
    }

    public HealthSensor(String name, Area area) {
        this.id = HealthSensor.counter++;
        this.name = name;
        this.area = area;
        this.logs = new ArrayList<>();
    }

    // GETS

    public Area getArea() {
        return area;
    }
    public long getId() {
        return id;
    }
    public ArrayList<HealthSensorData> getLogs() {
        return logs;
    }
    public String getName() {
        return name;
    }

    // SETS

    public void setArea(Area area) {
        this.area = area;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setLogs(ArrayList<HealthSensorData> logs) {
        this.logs = logs;
    }
    public void setName(String name) {
        this.name = name;
    }

    // CUSTOM FUNCTIONS FOR MODEL

    public void addNewLog(HealthSensorData log){
        this.logs.add(log);
    }

    public HealthSensorData getLastLog(){
        return this.logs.get(-1);
    }
    
    @Override
    public String toString() {
        return String.format(
            "HealthSensor [ID: %d, Name: %s, Area: %s]", 
            this.id, this.name, this.area.toString());
    }

}
