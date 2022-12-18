package ies.grupo51.lockedin.models;

import java.time.Instant;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("workLog")
public class WorkLog {

    @Id
    private long id;

    private long inmateId;
    private long workStationId;
    private Date timestamp;
    private int quota;

    private static long counter = 0;

    public WorkLog(){
        this.id = 0;
    }

    public WorkLog(long id, long inmateId, long workStationId, int quota) {
        this.id = id;
        this.inmateId = inmateId;
        this.workStationId = workStationId;
        this.timestamp = Date.from(Instant.now());
        this.quota = quota;
    }

    // SETS

    public static void setCounter(long counter) {
        WorkLog.counter = counter;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setInmateId(long inmateId) {
        this.inmateId = inmateId;
    }
    public void setQuota(int quota) {
        this.quota = quota;
    }
    public void setWorkStationId(long workStationId) {
        this.workStationId = workStationId;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    // GETS

    public static long getCounter() {
        return counter;
    }
    public long getId() {
        return id;
    }
    public long getInmateId() {
        return inmateId;
    }
    public int getQuota() {
        return quota;
    }
    public long getWorkStationId() {
        return workStationId;
    }
    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format(
            "WorkLog [ID: %d, Inmate Id: %d, WorkStation Id: %d, Timestamp: $s, Quota: %d]", 
            this.id, this.inmateId, this.workStationId, this.timestamp.toString(), this.quota);
    }
}
