package ies.grupo51.lockedin.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("workStation")
public class WorkStation {

    @Id
    private long id;

    private String name;
    private String description;
    private int expectedQuota;

    private static long counter = 100;

    public WorkStation(){
        this.id = 0;
    }

    public WorkStation(long id, String name, String description, int expectedQuota) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.expectedQuota = expectedQuota;
    }

    // SETS

    public static void setCounter(long counter) {
        WorkStation.counter = counter;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setExpectedQuota(int expectedQuota) {
        this.expectedQuota = expectedQuota;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    // GETS

    public static long getCounter() {
        return counter;
    }
    public String getDescription() {
        return description;
    }
    public int getExpectedQuota() {
        return expectedQuota;
    }
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format(
            "WorkStation [ID: %d, Name: %s, Description: %s, Expected Quota: %d]", 
            this.id, this.name, this.description, this.expectedQuota);
    }
}
