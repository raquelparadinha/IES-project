package ies.grupo51.lockedin.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("alert")
public class EstrilhoAlert extends Alert {
    
    private long areaId;

    private static long counter = 0;

    public EstrilhoAlert() {
        super();
    }

    public EstrilhoAlert(long id, String type, String information) {
        super(id, type, information);
    }

    public EstrilhoAlert(long id, String type, String information, long areaId) {
        super(id, type, information);
        this.areaId = areaId;
    }

    // SETS

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }
    public static void setCounter(long counter) {
        EstrilhoAlert.counter = counter;
    }

    // GETS

    public long getAreaId() {
        return areaId;
    }
    public static long getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
            "Area ID: %d]",
            this.areaId);
    }
}
