package ies.grupo51.lockedin.models;

import java.util.Set;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("area")
public class Area {
    
    @Id
    private long id;

    private String name;
    private Set<Area> connections;
    private int capacity;
    private Boolean access;
    private List<MoveSensorData> area_logs;

    private static long counter = 0;


    public Area() {
        this.id = 0;
    }

    public Area(long id, String name, Set<Area> connections, int capacity, Boolean access) {
        this.id = id;
        this.name = name;
        this.connections = connections;
        this.capacity = capacity;
        this.access = access;
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
    public void setAccess(Boolean access) {
        this.access = access;
    }
    public List<MoveSensorData> getArea_logs() {
        return area_logs;
    }
    public static void setCounter(long counter) {
        Area.counter = counter;
    }
    public void setId(long id) {
        this.id = id;
    }

    // GETS

    public static long getCounter() {
        return counter;
    }
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
    public Boolean getAccess() {
        return access;
    }
    public void setArea_logs(List<MoveSensorData> area_logs) {
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
            "Area [ID: %d, Name: %s, Connections: %s, Capacity: %d, Access: %s]",
            this.id, this.name, this.connections, this.capacity, this.access?"YES":"NO");
    }
}