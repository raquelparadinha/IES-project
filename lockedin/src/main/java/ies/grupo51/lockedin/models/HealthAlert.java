package ies.grupo51.lockedin.models;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("alert")
public class HealthAlert extends Alert {
    
    private List<String> symptoms;
    private long healthLogId;

    private static long counter = 0;

    public HealthAlert() {
        super();
    }

    public HealthAlert(long id, String type, String information) {
        super(id, type, information);
    }

    public HealthAlert(long id, String type, String information, List<String> symptoms, long healthLogId) {
        super(id, type, information);
        this.symptoms = symptoms;
        this.healthLogId = healthLogId;
    }

    // SETS

    public void setHealthLogId(long healthLogId) {
        this.healthLogId = healthLogId;
    }
    public static void setCounter(long counter) {
        HealthAlert.counter = counter;
    }
    public void setSymptoms(List<String> symptoms) {
        this.symptoms = symptoms;
    }

    // GETS

    public long getHealthLogId() {
        return healthLogId;
    }
    public static long getCounter() {
        return counter;
    }
    public List<String> getSymptoms() {
        return symptoms;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
            "HealthLog ID: %d]",
            this.healthLogId);
    }
}

