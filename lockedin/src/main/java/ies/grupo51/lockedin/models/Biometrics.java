package ies.grupo51.lockedin.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("biometrics")
public class Biometrics {

    @Id
    private long id;

    private long inmate_id;
    private int heart_beat;
    private int stress_level;
    private int glicose_level;
    private int uric_acid;
    private int cholesterol;
    private int toxic_screen;

    private static long counter = 0;

    public Biometrics() {
        Biometrics.counter++;
    }

    public Biometrics(long inmate_id, int heart_beat, int stress_level, int glicose_level, int uric_acid, int cholesterol, int toxic_screen) {
        this.id = Biometrics.counter++;
        this.inmate_id = inmate_id;
        this.heart_beat = heart_beat;
        this.stress_level = stress_level;
        this.glicose_level = glicose_level;
        this.uric_acid = uric_acid;
        this.cholesterol = cholesterol;
        this.toxic_screen = toxic_screen;
    }

    // GETS

    public long getId() {
        return id;
    }
    public int getCholesterol() {
        return cholesterol;
    }
    public int getGlicose_level() {
        return glicose_level;
    }
    public int getHeart_beat() {
        return heart_beat;
    }
    public long getInmate_id() {
        return inmate_id;
    }
    public int getStress_level() {
        return stress_level;
    }
    public int getToxic_screen() {
        return toxic_screen;
    }
    public int getUric_acid() {
        return uric_acid;
    }

    // SETS

    public void setId(long id) {
        this.id = id;
    }
    public void setCholesterol(int cholesterol) {
        this.cholesterol = cholesterol;
    }
    public void setGlicose_level(int glicose_level) {
        this.glicose_level = glicose_level;
    }
    public void setHeart_beat(int heart_beat) {
        this.heart_beat = heart_beat;
    }
    public void setInmate_id(long inmate_id) {
        this.inmate_id = inmate_id;
    }
    public void setStress_level(int stress_level) {
        this.stress_level = stress_level;
    }
    public void setToxic_screen(int toxic_screen) {
        this.toxic_screen = toxic_screen;
    }
    public void setUric_acid(int uric_acid) {
        this.uric_acid = uric_acid;
    }

    @Override
    public String toString() {
        return String.format(
            "Inmate [ID: %d, Heart beat: %d, Strees level: %d, Glicose level: %d, Uric Acid: %d, Cholesterol: %d, Toxic screen: %d]", 
            inmate_id, heart_beat, stress_level, glicose_level, uric_acid, cholesterol, toxic_screen);
    }
}