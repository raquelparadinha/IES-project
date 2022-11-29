package ies.grupo51.lockedin.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Biometrics")
public class Biometrics {
    @Id
    private long inmate_id;
    private int heart_beat;         // normal from 60 to 100 beats

    private int stress_level;       // 0 to 25 is a resting state
                                    // 26 to 50 is low stress
                                    // 51 to 75 is medium stress
                                    // 76 to 100 is a high stress state

    private int glicose_level;      // 99 mg/dL or lower is normal
                                    // 00 to 125 mg/dL indicates you have prediabetes
                                    // 126 mg/dL or higher indicates you have diabetes.

    private int uric_acid;          // 3.5 to 7.2 milligrams per deciliter (mg/dL)

    private int cholesterol;        // 125 to 200mg/dL

    private String toxic_screen;    // low - use of low damaging drugs
                                    // medium - clear effects of drug use
                                    // high - highly affected by drug use

    public Biometrics() {}

    public Biometrics(  long inmate_id, int heart_beat, int stress_level, int glicose_level, 
                        int uric_acid, int cholesterol, String toxic_screen) {
        this.inmate_id = inmate_id;
        this.heart_beat = heart_beat;
        this.stress_level = stress_level;
        this.glicose_level = glicose_level;
        this.uric_acid = uric_acid;
        this.cholesterol = cholesterol;
        this.toxic_screen = toxic_screen;
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
    public String getToxic_screen() {
        return toxic_screen;
    }
    public int getUric_acid() {
        return uric_acid;
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
    public void setToxic_screen(String toxic_screen) {
        this.toxic_screen = toxic_screen;
    }
    public void setUric_acid(int uric_acid) {
        this.uric_acid = uric_acid;
    }

    @Override
    public String toString() {
        return String.format(
            "Inmate [ID: %d, Heart beat: %d, Strees level: %d, Glicose level: %d, Uric Acid: %d, Cholesterol: %d, Toxic screen: %s", 
            inmate_id, heart_beat, stress_level, glicose_level, uric_acid, cholesterol, toxic_screen);
    }
}