package ies.grupo51.lockedin.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("moveSensor")
public class MoveSensor {
    
    @Id
    private long id;

    private long entryAreaId;
    private long exitAreaId;
    private List<Long> moveLogIds;

    private static long counter = 0;

    public MoveSensor() {
        this.id = 0;
    }
    
    public MoveSensor(long id, long entryAreaId, long exitAreaId, List<Long> moveLogIds) {
        this.id = id;
        this.entryAreaId = entryAreaId;
        this.exitAreaId = exitAreaId;
        this.moveLogIds = moveLogIds;
    }

    // GETS

    public static long getCounter() {
        return counter;
    }
    public long getEntryAreaId() {
        return entryAreaId;
    }
    public long getExitAreaId() {
        return exitAreaId;
    }
    public long getId() {
        return id;
    }
    public List<Long> getMoveLogIds() {
        return moveLogIds;
    }

    // SETS

    public static void setCounter(long counter) {
        MoveSensor.counter = counter;
    }
    public void setEntryAreaId(long entryAreaId) {
        this.entryAreaId = entryAreaId;
    }
    public void setExitAreaId(long exitAreaId) {
        this.exitAreaId = exitAreaId;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setMoveLogIds(List<Long> moveLogIds) {
        this.moveLogIds = moveLogIds;
    }

    // CUSTOM FUNCTIONS FOR MODEL

    public void addMoveLogIds(long id){
        this.moveLogIds.add(id);
    }

    @Override
    public String toString() {
        return String.format(
            "MoveSensor [ID: %d, Entry Area ID: %d, Exit Area ID: %d]", 
            this.id, this.entryAreaId, this.exitAreaId);
    }

}
