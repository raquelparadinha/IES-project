package ies.grupo51.lockedin.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("prison")
public class Prison {

    @Id
    private long id;

    private String name;
    private String description;
    private String address;
    
    private static long counter = 0;
    
    public Prison() {
        this.id = 0;
    }

    public Prison(long id, String name, String description, String address){
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
    }

    // GETS
    
    public String getAddress() {
        return address;
    }
    public static long getCounter() {
        return counter;
    }
    public String getDescription() {
        return description;
    }
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    // SETS
    
    public void setAddress(String address) {
        this.address = address;
    }
    public static void setCounter(long counter) {
        Prison.counter = counter;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
            "Prison [ID: %d, Name: %s, Description: %s, Address: %s]", 
            this.id, this.name, this.description, this.address);
    }
}