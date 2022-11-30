package ies.grupo51.lockedin.models;

import java.text.DateFormat;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("inmate")
public class Inmate {
    
    @Id
    private long id;

    private String name;
    private DateFormat birth_date;
    private DateFormat entry_date;
    private DateFormat sentence_ending;      // years
    private InmateJob inmateJob;
    private Boolean solitary;
    private ArrayList<HealthSensorData> health_logs;
    private ArrayList<MoveSensorData> move_logs;

    private static long counter = 1000;

    public Inmate () {
        this.id = Inmate.counter++;
    }

    public Inmate (String name, DateFormat birth_date, DateFormat entry_date, DateFormat sentence_ending, InmateJob inmateJob, Boolean solitary) {
        this.id = Inmate.counter++;
        this.name = name;
        this.birth_date = birth_date;
        this.entry_date = entry_date;
        this.sentence_ending = sentence_ending;
        this.inmateJob = inmateJob;
        this.solitary = solitary;
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
    public void setInmateJob(InmateJob inmateJob) {
        this.inmateJob = inmateJob;
    }
    public void setSolitary(Boolean solitary) {
        this.solitary = solitary;
    } 
    public void setHealth_logs(ArrayList<HealthSensorData> health_logs) {
        this.health_logs = health_logs;
    }
    public void setMove_logs(ArrayList<MoveSensorData> move_logs) {
        this.move_logs = move_logs;
    }

    // GETS
    
    public ArrayList<HealthSensorData> getHealth_logs() {
        return health_logs;
    }
    public ArrayList<MoveSensorData> getMove_logs() {
        return move_logs;
    }
    public long getid() {
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
    public InmateJob getInmateJob() {
        return inmateJob;
    }
    public Boolean getSolitary() {
        return solitary;
    }

    // CUSTOM FUNCTIONS FOR MODEL

    public void addNewMoveLog(MoveSensorData log){
        this.move_logs.add(log);
    }
    public void addNewHeathLog(HealthSensorData log){
        this.health_logs.add(log);
    }

    public MoveSensorData getLastMoveLog(){
        return this.move_logs.get(-1);
    }
    public HealthSensorData getLastHealthLog(){
        return this.health_logs.get(-1);
    }

    @Override
    public String toString() {
        return String.format(
            "Inmate [ID: %d, Name: %s, Birth date: %s, Entry date: %s, Sentence End: %d, Job: %s, Solitary Confinement: %s]", 
            id, name, birth_date.toString(), entry_date.toString(), sentence_ending.toString(), inmateJob.toString(), solitary?"YES":"NO");
    }

    public long getId() {
        return id;
    }

    // public void setId(long id) {
    //     this.id = id;
    // }

    public static long getCounter() {
        return counter;
    }

    // public static void setCounter(long counter) {
    //     Inmate.counter = counter;
    // }
}