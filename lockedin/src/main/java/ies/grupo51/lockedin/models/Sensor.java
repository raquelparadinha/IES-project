package ies.grupo51.lockedin.models;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Sensor")
public class Sensor {
    
    @Id
    private long id;
    private String name;
    private Set<Area> connections;

    private ArrayList<SensorData> longs;
    private SensorData lastlog;

    private Boolean biometrical;

    public Boolean getBiometrical() {
        return biometrical;
    }
    public Set<Area> getConnections() {
        return connections;
    }
    public long getId() {
        return id;
    }
    public SensorData getLastlog() {
        return lastlog;
    }
    public ArrayList<SensorData> getLongs() {
        return longs;
    }
    public String getName() {
        return name;
    }
    public void setBiometrical(Boolean biometrical) {
        this.biometrical = biometrical;
    }
    public void setConnections(Set<Area> connections) {
        this.connections = connections;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setLastlog(SensorData lastlog) {
        this.lastlog = lastlog;
    }
    public void setLongs(ArrayList<SensorData> longs) {
        this.longs = longs;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return String.format(
            "Sensor [ID: %d, Name: %d, Connections: %d, Biometrical: %s]", 
            this.id, this.name, this.connections, this.biometrical?"YES":"NO");
    }

}
