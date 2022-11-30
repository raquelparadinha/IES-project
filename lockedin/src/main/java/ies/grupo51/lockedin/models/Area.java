package ies.grupo51.lockedin.models;

import java.util.Set;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("area")
public class Area {
    
    @Id
    private long id;

    private String name;
    private Set<Area> connections;
    private int capacity;
    private Boolean reserved;
    private ArrayList<MoveSensorData> area_logs;

    private static long counter = 0;


    public Area() {
        this.id = Area.counter++;
    }

    public Area(String name, Set<Area> connections, int capacity, Boolean reserved) {
        this.id = Area.counter++;
        this.name = name;
        this.connections = connections;
        this.capacity = capacity;
        this.reserved = reserved;
    }

    // SETS

    public void setName(String name) {
        this.name = name;
    }
    public void setConnections(Set<Area> connections) {
        this.connections = connections;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }
    public ArrayList<MoveSensorData> getArea_logs() {
        return area_logs;
    }

    // GETS

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Set<Area> getConnections() {
        return connections;
    }
    public int getCapacity() {
        return capacity;
    }
    public Boolean getReserved() {
        return reserved;
    }
    public void setArea_logs(ArrayList<MoveSensorData> area_logs) {
        this.area_logs = area_logs;
    }

    // CUSTOM FUNCTIONS FOR MODEL

    public void addNewLog(MoveSensorData log){
        this.area_logs.add(log);
    }

    public MoveSensorData getLastLog(){
        return this.area_logs.get(-1);
    }

    @Override
    public String toString() {
        return String.format(
            "Area [ID: %d, Name: %s, Connections: %s, Capacity: %d, Reserved: %s]",
            id, name, connections, capacity, reserved?"YES":"NO");
    }
}