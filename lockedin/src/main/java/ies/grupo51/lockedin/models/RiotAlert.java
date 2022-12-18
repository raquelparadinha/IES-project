package ies.grupo51.lockedin.models;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("alert")
public class RiotAlert extends Alert {
    
    private long areaId;
    private List<Long> inmateIds;

    private static long counter = 0;

    public RiotAlert() {
        super();
    }

    public RiotAlert(long id, String type, String information) {
        super(id, type, information);
    }

    public RiotAlert(long id, String type, String information, long areaId, List<Long> inmateIds) {
        super(id, type, information);
        this.areaId = areaId;
        this.inmateIds = inmateIds;
    }

    // SETS

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }
    public static void setCounter(long counter) {
        RiotAlert.counter = counter;
    }
    public void setInmateIds(List<Long> inmateIds) {
        this.inmateIds = inmateIds;
    }

    // GETS

    public long getAreaId() {
        return areaId;
    }
    public static long getCounter() {
        return counter;
    }
    public List<Long> getInmateIds() {
        return inmateIds;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
            "Area ID: %d]",
            this.areaId);
    }
}
