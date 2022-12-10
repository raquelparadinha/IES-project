package ies.grupo51.lockedin.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("inmate")
public class Inmate {
    
    @Id
    private long id;

    private String name;
    private String birthdate;
    private String entryDate;
    private String sentenceEnd;
    private Boolean solitary;
    private long workstationId;
    private long healthLogId;
    private List<Long> moveLogIds;
    private List<Long> workLogIds;

    private static long counter = 1000;

    public Inmate () {
        this.id = 0;
    }

    public Inmate (long id, String name, String birthdate, String entryDate, String sentenceEnd, Boolean solitary, long workstationId, long healthLogId, List<Long> moveLogIds, List<Long> workLogIds) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.entryDate = entryDate;
        this.sentenceEnd = sentenceEnd;
        this.solitary = solitary;
        this.workstationId = workstationId;
        this.healthLogId = healthLogId;
        this.moveLogIds = moveLogIds;
        this.workLogIds = workLogIds;
    }

    // SETS

    public void setName(String name) {
        this.name = name;
    }
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
    public static void setCounter(long counter) {
        Inmate.counter = counter;
    }
    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }
    public void setHealthLogId(long healthLogId) {
        this.healthLogId = healthLogId;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setMoveLogIds(List<Long> moveLogIds) {
        this.moveLogIds = moveLogIds;
    }
    public void setSentenceEnd(String sentenceEnd) {
        this.sentenceEnd = sentenceEnd;
    }
    public void setSolitary(Boolean solitary) {
        this.solitary = solitary;
    }
    public void setWorkLogIds(List<Long> workLogIds) {
        this.workLogIds = workLogIds;
    }
    public void setWorkstationId(long workstationId) {
        this.workstationId = workstationId;
    }

    // GETS
    
    public String getBirthdate() {
        return birthdate;
    }
    public static long getCounter() {
        return counter;
    }
    public String getEntryDate() {
        return entryDate;
    }
    public long getHealthLogId() {
        return healthLogId;
    }
    public long getId() {
        return id;
    }
    public List<Long> getMoveLogIds() {
        return moveLogIds;
    }
    public String getName() {
        return name;
    }
    public String getSentenceEnd() {
        return sentenceEnd;
    }
    public Boolean getSolitary() {
        return solitary;
    }
    public List<Long> getWorkLogIds() {
        return workLogIds;
    }
    public long getWorkstationId() {
        return workstationId;
    }

    // CUSTOM FUNCTIONS FOR MODEL

    public void addMoveLogId(long id){
        this.moveLogIds.add(id);
    }
    public void addWorkLogId(long id){
        this.workLogIds.add(id);
    }

    @Override
    public String toString() {
        return String.format(
            "Inmate [ID: %s, Name: %s, Birth String: %s, Entry String: %s, Sentence End: %d, Solitary Confinement: %s, Workstation ID: $d]", 
            this.id, this.name, this.birthdate.toString(), this.entryDate.toString(), this.sentenceEnd.toString(), this.solitary?"YES":"NO", this.workstationId);
    }
}