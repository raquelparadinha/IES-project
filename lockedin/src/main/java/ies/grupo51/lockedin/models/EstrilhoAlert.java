package ies.grupo51.lockedin.models;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("alert")
public class EstrilhoAlert extends Alert {
    
    private long areaId;
    private List<Long> inmateIds;

    public EstrilhoAlert() {
        super();
    }

    public EstrilhoAlert(long id, String type, long areaId, List<Long> inmateIds) {
        super(id, type);
        this.areaId = areaId;
        this.inmateIds = inmateIds;
    }

    // SETS

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }
    public void setInmateIds(List<Long> inmateIds) {
        this.inmateIds = inmateIds;
    }

    // GETS

    public long getAreaId() {
        return areaId;
    }
    public List<Long> getInmateIds() {
        return inmateIds;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
            "Area ID: %d, Inmate IDs: %s]",
            this.areaId, this.inmateIds.toString());
    }
}
