package ies.grupo51.lockedin.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Healthcheck {

    @Id
    private long id;

    private long inmate_id;
    private Biometrics biometrics;

    private static long counter = 100;

    public Healthcheck() {
        this.id = Healthcheck.counter++;
    }

    public Healthcheck(long inmate_id, Biometrics biometrics) {
        this.id = Healthcheck.counter++;
        this.inmate_id = inmate_id;
        this.biometrics = biometrics;
    }

    // SETS 

    public void setBiometrics(Biometrics biometrics) {
        this.biometrics = biometrics;
    }
    public static void setCounter(long counter) {
        Healthcheck.counter = counter;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setInmate_id(long inmate_id) {
        this.inmate_id = inmate_id;
    }
    
    // GETS

    public Biometrics getBiometrics() {
        return biometrics;
    }
    public static long getCounter() {
        return counter;
    }
    public long getId() {
        return id;
    }
    public long getInmate_id() {
        return inmate_id;
    }

    @Override
    public String toString() {
        return String.format(
            "HealthSensorData [ID: %d, Inmate ID: %d, Biometrics: %s]",
            this.id, this.inmate_id, this.biometrics.toString());
    }
}
