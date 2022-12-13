package ies.grupo51.lockedin.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("alert")
public class HealthAlert extends Alert {
    
    private long healthLogId;

    private static long counter = 0;

    public HealthAlert() {
        super();
    }

    public HealthAlert(long id, String type, long areaId) {
        super(id, type);
        this.healthLogId = areaId;
    }

    // SETS

    public void setHealthLogId(long healthLogId) {
        this.healthLogId = healthLogId;
    }
    public static void setCounter(long counter) {
        HealthAlert.counter = counter;
    }

    // GETS

    public long getHealthLogId() {
        return healthLogId;
    }
    public static long getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
            "HealthLog ID: %d]",
            this.healthLogId);
    }
}

