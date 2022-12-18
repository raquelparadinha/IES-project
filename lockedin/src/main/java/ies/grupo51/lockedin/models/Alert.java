package ies.grupo51.lockedin.models;

import java.time.Instant;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("alert")
public abstract class Alert {
    
    @Id
    private long id;

    private String type;
    private String information;
    private Date timestamp;
    private Boolean seen;

    private static long counter = 0;

    public Alert() {
        this.id = 0;
        this.timestamp = Date.from(Instant.now());
        this.seen = false;
    }

    public Alert(long id, String type, String information) {
        this.id = id;
        this.type = type;
        this.information = information;
        this.timestamp = Date.from(Instant.now());
        this.seen = false;
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
    public void setSeen(Boolean seen) {
        this.seen = seen;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    public void setInformation(String information) {
        this.information = information;
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
    public Boolean getSeen() {
        return seen;
    }
    public Date getTimestamp() {
        return timestamp;
    }
    public String getInformation() {
        return information;
    }
    
    @Override // Will be called as super.toString() and completed with info!
    public String toString() {
        return String.format(
            "Alert [ID: %d, Type: %s, Timestamp: %s, ",
            this.id, this.type, this.timestamp.toString());
    }
}
