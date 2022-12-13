package ies.grupo51.lockedin.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("alert")
public class EstiaAlert extends Alert{
    
    private long workLogId;

    private static long counter = 0;

    public EstiaAlert() {
        super();
    }

    public EstiaAlert(long id, String type, String information) {
        super(id, type, information);
    }

    public EstiaAlert(long id, String type, String information, long workLogId) {
        super(id, type, information);
        this.workLogId = workLogId;
    }

    // SETS

    public void setWorkLogId(long workLogId) {
        this.workLogId = workLogId;
    }
    public static void setCounter(long counter) {
        EstiaAlert.counter = counter;
    }

    // GETS

    public static long getCounter() {
        return counter;
    }
    public long getWorkLogId() {
        return workLogId;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
            "WorkLog ID: %d]",
            this.workLogId);
    }
}
