package ies.grupo51.lockedin.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("area")
public class Area {
    
    @Id
    private long id;

    private String name;
    private int capacity;
    private Boolean access;

    private static long counter = 0;


    public Area() {
        this.id = 0;
    }

    public Area(long id, String name, int capacity, Boolean access) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.access = access;
    }

    // SETS

    public void setName(String name) {
        this.name = name;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public void setAccess(Boolean access) {
        this.access = access;
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
    public int getCapacity() {
        return capacity;
    }
    public Boolean getAccess() {
        return access;
    }

    @Override
    public String toString() {
        return String.format(
            "Area [ID: %d, Name: %s, Capacity: %d, Access: %s]",
            this.id, this.name, this.capacity, this.access?"YES":"NO");
    }
}