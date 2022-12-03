package ies.grupo51.lockedin.models;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("inmate")
public class Inmate {
    
    @Id
    private UUID id;

    private String name;
    private DateFormat birth_date;
    private DateFormat entry_date;
    private DateFormat sentence_ending;      // years
    private Boolean solitary;
    private Set<Workstation> shifts;
    private List<Healthcheck> health_logs;
    private List<MoveSensorData> move_logs;

    private static long counter = 1000;

    public Inmate () {
        this.id = UUID.randomUUID();
        this.shifts = new HashSet<>();
        this.health_logs = new ArrayList<>();
        this.move_logs = new ArrayList<>();
    }

    public Inmate (String name, DateFormat birth_date, DateFormat entry_date, DateFormat sentence_ending, Boolean solitary) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.birth_date = birth_date;
        this.entry_date = entry_date;
        this.sentence_ending = sentence_ending;
        this.solitary = solitary;
        this.shifts = new HashSet<>();
        this.health_logs = new ArrayList<>();
        this.move_logs = new ArrayList<>();
    }

    // SETS

    public void setName(String name) {
        this.name = name;
    }
    public void setBirth_date(DateFormat birth_date) {
        this.birth_date = birth_date;
    }
    public void setEntry_date(DateFormat entry_date) {
        this.entry_date = entry_date;
    }
    public void setSentence_ending(DateFormat sentence_ending) {
        this.sentence_ending = sentence_ending;
    }
    public void setShifts(Set<Workstation> shifts) {
        this.shifts = shifts;
    }
    public void setSolitary(Boolean solitary) {
        this.solitary = solitary;
    } 
    public void setHealth_logs(List<Healthcheck> health_logs) {
        this.health_logs = health_logs;
    }
    public void setMove_logs(List<MoveSensorData> move_logs) {
        this.move_logs = move_logs;
    }
    public static void setCounter(long counter) {
        Inmate.counter = counter;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    // GETS
    
    public List<Healthcheck> getHealth_logs() {
        return health_logs;
    }
    public List<MoveSensorData> getMove_logs() {
        return move_logs;
    }
    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public DateFormat getBirth_date() {
        return birth_date;
    }
    public DateFormat getEntry_date() {
        return entry_date;
    }
    public DateFormat getSentence_ending() {
        return sentence_ending;
    }
    public Set<Workstation> getShifts() {
        return shifts;
    }
    public Boolean getSolitary() {
        return solitary;
    }
    public static long getCounter() {
        return counter;
    }

    // CUSTOM FUNCTIONS FOR MODEL

    public void addShift(Workstation workstation) {
        this.shifts.add(workstation);
    }
    public void addMoveLog(MoveSensorData log){
        this.move_logs.add(log);
    }
    public void addHeathLog(Healthcheck log){
        this.health_logs.add(log);
    }

    public MoveSensorData getLastMoveLog(){
        return this.move_logs.get(-1);
    }
    public Healthcheck getLastHealthLog(){
        return this.health_logs.get(-1);
    }


    @Override
    public String toString() {
        return String.format(
            "Inmate [ID: %s, Name: %s, Birth date: %s, Entry date: %s, Sentence End: %d, Solitary Confinement: %s]", 
            id.toString(), name, birth_date.toString(), entry_date.toString(), sentence_ending.toString(), solitary?"YES":"NO");
    }

}