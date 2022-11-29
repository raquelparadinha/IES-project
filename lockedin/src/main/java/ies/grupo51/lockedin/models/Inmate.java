package ies.grupo51.lockedin.models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("inmate")
public class Inmate {
    @Id
    private long id;

    private String name;
    private LocalDate birth_date;
    private LocalDate entry_date;
    private int sentence_time;      // years
    private String work_station;
    private Boolean solitary;

    private static long counter = 1000;

    public Inmate () {
        this.id = Inmate.counter++;
    }

    public Inmate (String name, LocalDate birth_date, LocalDate entry_date,int sentence_time, String work_station, Boolean solitary) {
        this.name = name;
        this.birth_date = birth_date;
        this.entry_date = entry_date;
        this.sentence_time = sentence_time;
        this.work_station = work_station;
        this.solitary = solitary;
        this.id = Inmate.counter++;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }
    public void setEntry_date(LocalDate entry_date) {
        this.entry_date = entry_date;
    }
    public void setSentence_time(int sentence_time) {
        this.sentence_time = sentence_time;
    }
    public void setWork_station(String work_station) {
        this.work_station = work_station;
    }
    public void setSolitary(Boolean solitary) {
        this.solitary = solitary;
    }
    
    public long getid() {
        return id;
    }
    public String getName() {
        return name;
    }
    public LocalDate getBirth_date() {
        return birth_date;
    }
    public LocalDate getEntry_date() {
        return entry_date;
    }
    public int getSentence_time() {
        return sentence_time;
    }
    public String getWork_station() {
        return work_station;
    }
    public Boolean getSolitary() {
        return solitary;
    }

    @Override
    public String toString() {
        return String.format(
            "Inmate [ID: %d, Name: %s, Birth date: %s, Entry date: %s, Sentence: %d, Work station: %s, Solitary confinement: %s", 
            id, name, birth_date, entry_date, sentence_time, work_station, solitary?"YES":"NO");
    }
}