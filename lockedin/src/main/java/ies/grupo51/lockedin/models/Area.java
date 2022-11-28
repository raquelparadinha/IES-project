package ies.grupo51.lockedin.models;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("area")
public class Area {
    @Id
    private long id;

    private String name;
    private ArrayList<Area> connections;
    private int capacity;
    private Boolean reserved;
    private static long counter = 1;

    public Area() {
        this.id = Area.counter++;
        this.connections = new ArrayList<>();
    }

    public Area(String name, ArrayList<Area> connections, int capacity, Boolean reserved) {
        this.name = name;
        this.connections = connections;
        this.capacity = capacity;
        this.reserved = reserved;
        this.id = Area.counter++;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setConnections(ArrayList<Area> connections) {
        this.connections = connections;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public ArrayList<Area> getConnections() {
        return connections;
    }
    public int getCapacity() {
        return capacity;
    }
    public Boolean getReserved() {
        return reserved;
    }

    @Override
    public String toString() {
        String result = String.format(
            "Area [ID: %d, Name: %s, Connections: ",
            id, name);

        for (Area conn : connections) {
            result += String.format("%s, ", conn.getName());
        }
        
        result += String.format(
            " Capacity: %d, Reserved: %s]",
            capacity, reserved?"YES":"NO");    
    
        return result;
    }
}
