package ies.grupo51.lockedin.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("alert")
public abstract class Alert {
    
    @Id
    private long id;

    private String type;

    private static long counter = 0;

    public Alert() {
        this.id = 0;
    }

    public Alert(long id, String type) {
        this.id = id;
        this.type = type;
    }

    // SETS

    public static void setCounter(long counter) {
        Alert.counter = counter;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setType(String type) {
        this.type = type;
    }

    // GETS

    public static long getCounter() {
        return counter;
    }
    public long getId() {
        return id;
    }
    public String getType() {
        return type;
    }
    
    @Override // Will be called as super.toString() and completed with info!
    public String toString() {
        return String.format(
            "Alert [ID: %d, Type: %s, ",
            this.id, this.type);
    }
}
