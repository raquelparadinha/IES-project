package ies.grupo51.lockedin.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ies.grupo51.lockedin.comms.Sender;
import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.Area;
import ies.grupo51.lockedin.models.Guard;
import ies.grupo51.lockedin.models.Inmate;
import ies.grupo51.lockedin.models.MoveSensor;
import ies.grupo51.lockedin.services.AreaService;
import ies.grupo51.lockedin.services.GuardService;
import ies.grupo51.lockedin.services.InmateService;
import ies.grupo51.lockedin.services.MoveSensorService;

@CrossOrigin
@RestController
@RequestMapping("/api/area")
public class AreaController {
    
    // SERVICES

    @Autowired
    private AreaService areaService;

    @Autowired
    private InmateService inmateService;

    @Autowired
    private MoveSensorService moveSensorService;

    @Autowired
    private GuardService guardService;

    @Autowired
    private Sender sender;

    // GET METHODS

    @GetMapping("")
    public ResponseEntity<List<Area>> getAreas() throws ResourceNotFoundException {
        
        return ResponseEntity.ok().body(areaService.getAreas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Area> getAreaUsingId(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(areaService.getAreaById(id));
    }

    @GetMapping("/{id}/access")
    public ResponseEntity<Area> changeAreaAcess(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Area area = areaService.getAreaById(id);
        area.setAccess(!area.getAccess());
        return ResponseEntity.ok(areaService.updateArea(area));
    }

    @GetMapping("/{id}/inmates")
    public ResponseEntity<List<Inmate>> getInmatesOfAnArea(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        List<Inmate> inmates = new ArrayList<>();
        for (long inmateid : areaService.getAreaById(id).getCurrentInmateIds()) {
            inmates.add(inmateService.getInmateById(inmateid));
        }
        inmates.sort((inmate1, inmate2) -> -Integer.compare(inmate1.getDanger(),inmate2.getDanger()) );
        return ResponseEntity.ok().body(inmates);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<HashMap<String, Object>> getAreaDetails(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        HashMap<String, Object> data = new HashMap<>();
        Area area = areaService.getAreaById(id);
        data.put("id", area.getId());
        data.put("name", area.getName());
        data.put("capacity", area.getCapacity());
        data.put("access", area.getAccess());
        List<String> connections = new ArrayList<>();
        for (MoveSensor moveSensor : moveSensorService.getMoveSensors()) {
            if (moveSensor.getEntryAreaId() == area.getId()) {
                Area connectingArea = areaService.getAreaById(moveSensor.getExitAreaId());
                connections.add(connectingArea.getName());
            }
        }
        data.put("connections", connections);

        float currentAverageDanger = 0;
        for (long inmateId : area.getCurrentInmateIds()) {
            currentAverageDanger += inmateService.getInmateById(inmateId).getDanger();
        }
        if (area.getCurrentInmateIds().size() != 0) {
            data.put("currentDangerLevel", currentAverageDanger/area.getCurrentInmateIds().size());
        } else {
            data.put("currentDangerLevel", 0);
        }

        List<String> guards = new ArrayList<>();
        for (Guard guard : guardService.getGuards()) {
            if (guard.getAreaId() == area.getId()) {
                guards.add(guard.getName());
            }
        }
        data.put("guards", guards);
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/{id}/lock")
    public ResponseEntity<Area> lockArea(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Area a = areaService.getAreaById(id);
        
        if(!a.getAccess()) {
            return ResponseEntity.badRequest().body(a);
        }

        a.setAccess(false);
        areaService.updateArea(a);
        for (MoveSensor ms : moveSensorService.getMoveSensorsByEntryAreaId(id)) {
            if (ms.isActive() && areaService.getAreaById(ms.getExitAreaId()).getAccess()) {
                ms.setActive(false);
                moveSensorService.updatMoveSensor(ms);
                sender.lockSensor(ms.getId());
            }
        }
        for (MoveSensor ms : moveSensorService.getMoveSensorsByExitAreaId(id)) {
            if (ms.isActive() && areaService.getAreaById(ms.getEntryAreaId()).getAccess()) {
                ms.setActive(false);
                moveSensorService.updatMoveSensor(ms);
                sender.lockSensor(ms.getId());
            }
        }
        return ResponseEntity.ok().body(a);
    }

    @GetMapping("/{id}/unlock")
    public ResponseEntity<Area> unlockArea(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Area a = areaService.getAreaById(id);
        
        if(a.getAccess()) {
            return ResponseEntity.badRequest().body(a);
        }

        a.setAccess(true);
        areaService.updateArea(a);
        for (MoveSensor ms : moveSensorService.getMoveSensorsByEntryAreaId(id)) {
            if (!ms.isActive() && areaService.getAreaById(ms.getExitAreaId()).getAccess()){
                ms.setActive(true);
                moveSensorService.updatMoveSensor(ms);
                sender.unlockSensor(ms.getId());
            }
        }
        for (MoveSensor ms : moveSensorService.getMoveSensorsByExitAreaId(id)) {
            if (!ms.isActive() && areaService.getAreaById(ms.getEntryAreaId()).getAccess()) {
                ms.setActive(true);
                moveSensorService.updatMoveSensor(ms);
                sender.unlockSensor(ms.getId());
            }
        }
        return ResponseEntity.ok().body(a);
    }
}
