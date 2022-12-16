package ies.grupo51.lockedin.models;

import java.time.LocalDate;

import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("healthLog")
public class HealthLog {

    @Id
    private long id;

    private long inmateId;

    private LocalDate timestamp;
    private int heartBeat;
    private int stress;
    private int glicose;
    private int uricAcid;
    private int cholesterol;
    private int toxicScreen;

    private static long counter = 0;

    public HealthLog() {
        this.id = 0;
    }

    public HealthLog(long id, long inmateId, int heartBeat, int stress, int glicose, int uricAcid, int cholesterol, int toxicScreen) {
        this.id = id;
        this.timestamp = LocalDate.now();
        this.inmateId = inmateId;
        this.heartBeat = heartBeat;
        this.stress = stress;
        this.glicose = glicose;
        this.uricAcid = uricAcid;
        this.cholesterol = cholesterol;
        this.toxicScreen = toxicScreen;
    }

    public HealthLog(long id, long inmateId, JSONObject hc) {
        this.id = id;
        this.timestamp = LocalDate.now();
        this.inmateId = inmateId;
        this.heartBeat = hc.getInt("heart_beat");
        this.stress = hc.getInt("stress_level");
        this.glicose = hc.getInt("glicose_level");
        this.uricAcid = hc.getInt("uric_acid");
        this.cholesterol = hc.getInt("cholesterol");
        this.toxicScreen = hc.getInt("toxic_screen");
    }

    // GETS

    public long getId() {
        return id;
    }
    public LocalDate getTimestamp() {
        return timestamp;
    }
    public int getCholesterol() {
        return cholesterol;
    }
    public static long getCounter() {
        return counter;
    }
    public int getGlicose() {
        return glicose;
    }
    public int getHeartBeat() {
        return heartBeat;
    }
    public long getInmateId() {
        return inmateId;
    }
    public int getStress() {
        return stress;
    }
    public int getToxicScreen() {
        return toxicScreen;
    }
    public int getUricAcid() {
        return uricAcid;
    }

    // SETS

    public void setId(long id) {
        this.id = id;
    }
    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }
    public void setCholesterol(int cholesterol) {
        this.cholesterol = cholesterol;
    }
    public static void setCounter(long counter) {
        HealthLog.counter = counter;
    }
    public void setGlicose(int glicose) {
        this.glicose = glicose;
    }
    public void setHeartBeat(int heartBeat) {
        this.heartBeat = heartBeat;
    }
    public void setInmateId(long inmateId) {
        this.inmateId = inmateId;
    }
    public void setStress(int stress) {
        this.stress = stress;
    }
    public void setToxicScreen(int toxicScreen) {
        this.toxicScreen = toxicScreen;
    }
    public void setUricAcid(int uricAcid) {
        this.uricAcid = uricAcid;
    }

    @Override
    public String toString() {
        return String.format(
            "HealthLog [ID %d, Inmate ID: %d, Heart Beat: %d, Strees: %d, Glicose: %d, Uric Acid: %d, Cholesterol: %d, Toxic Screen: %d]", 
            this.id, this.inmateId, this.heartBeat, this.stress, this.glicose, this.uricAcid, this.cholesterol, this.toxicScreen);
    }
}