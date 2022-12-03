package ies.grupo51.lockedin.models;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("prison")
public class Prison {

    @Id
    private UUID id;

    private String name;
    private Set<Area> areas;
    private int num_areas;
    private Set<Guard> guards;
    private int num_guards;
    private Set<Inmate> inmates;
    private int num_inmates;
    private Warden warden;
    
    private static long counter = 0;
    
    public Prison() {
        this.id = UUID.randomUUID();
        this.areas = new HashSet<>();
        this.num_areas = areas.size();
        this.guards = new HashSet<>();
        this.num_guards = guards.size();
        this.inmates = new HashSet<>();
        this.num_inmates = inmates.size();
    }

    public Prison(String name){
        this.id = UUID.randomUUID();
        this.name = name;
        this.areas = new HashSet<>();
        this.num_areas = areas.size();
        this.guards = new HashSet<>();
        this.num_guards = guards.size();
        this.inmates = new HashSet<>();
        this.num_inmates = inmates.size();
    }

    // GETS
    
    public Set<Area> getAreas() {
        return areas;
    }
    public Set<Guard> getGuards() {
        return guards;
    }
    public int getNum_areas() {
        return num_areas;
    }
    public int getNum_guards() {
        return num_guards;
    }
    public UUID getId() {
        return id;
    }
    public Set<Inmate> getInmates() {
        return inmates;
    }
    public String getName() {
        return name;
    }
    public Warden getWarden() {
        return warden;
    }
    public static long getCounter() {
        return counter;
    }
    public int getNum_inmates() {
        return num_inmates;
    }

    // SETS
    
    public void setAreas(Set<Area> areas) {
        this.areas = areas;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public void setNum_areas(int num_areas) {
        this.num_areas = num_areas;
    }
    public void setNum_guards(int num_guards) {
        this.num_guards = num_guards;
    }
    public void setNum_inmates(int num_inmates) {
        this.num_inmates = num_inmates;
    }
    public void setGuards(Set<Guard> guards) {
        this.guards = guards;
    }
    public void setInmates(Set<Inmate> inmates) {
        this.inmates = inmates;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setWarden(Warden warden) {
        this.warden = warden;
    }
    public static void setCounter(long counter) {
        Prison.counter = counter;
    }

    // CUSTOM FUNCTIONS FOR MODEL

    public void addNewArea(Area area){
        this.areas.add(area);
        this.num_areas = areas.size();
    }
    public void addNewGuard(Guard guard){
        this.guards.add(guard);
        this.num_guards = guards.size();
    }
    public void addNewInmate(Inmate inmate){
        this.inmates.add(inmate);
        this.num_inmates = inmates.size();
    }

    @Override
    public String toString() {
        return String.format(
            "Prison [ID: %d, Areas: %d, Guards: %d, Inmates: %d, Warden: %s]", 
            this.id, this.num_areas, this.num_guards, this.num_inmates, this.warden.toString());
    }
}