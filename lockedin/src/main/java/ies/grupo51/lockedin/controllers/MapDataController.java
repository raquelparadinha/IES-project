package ies.grupo51.lockedin.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.Area;
import ies.grupo51.lockedin.models.MoveSensor;
import ies.grupo51.lockedin.services.AreaService;
import ies.grupo51.lockedin.services.MoveSensorService;

import java.awt.Color;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/api/map")
public class MapDataController {
    
    // Static variables

    // Services

    @Autowired private AreaService areaService;
    @Autowired private MoveSensorService moveSensorService;

    // Mappings
    @GetMapping("")
    public ResponseEntity<HashMap<String, Object>> getMapData() throws ResourceNotFoundException {
        HashMap<String, Object> data = new HashMap<>();

        // logic for area occupation colors
        // using HSB color format instead of RGB to create a range from green - yellow - red
        float angleFrom = 0, angleTo = 120; // 0 - red, 120 - green, passes through 60 - yellow
        int size = 60; // create 60 colors
        float angleRange = angleTo - angleFrom;
        float stepAngle = angleRange / size;

        Color[] colors = new Color[size]; // create array of colors
        for (int i = 0; i < size; i++) {
            float angle = angleFrom + (i * stepAngle);
            colors[i] = Color.getHSBColor(angle / 360, 1.0f, 1.0f);
        }

        // areas
        HashMap<String, Object> areas = new HashMap<>();
        for (Area a : areaService.getAreas()) {
            HashMap<String, Object> areaData = new HashMap<>();
            
            // area color
            int countInmates = a.getCurrentInmateIds().size();
            
            String hex = "#D6E4E5";
            if (countInmates != 0) {
                float f = countInmates / (float) a.getCapacity();
                int coloridx = size - (int) Math.ceil(f * size) - 1;
                if (coloridx >= size) coloridx = 59;

                Color color = colors[coloridx];
                hex = "#"+Integer.toHexString(color.getRGB()).substring(2).toUpperCase();
            }
            
            areaData.put("color", hex);
            
            areas.put(a.getName(), areaData);
        }
        data.put("areas", areas);

        // sensors
        HashMap<String, Object> sensors = new HashMap<>();
        // color for lock or not
        for (MoveSensor s : moveSensorService.getMoveSensors()) {
            String hex = (s.isActive()) ? "#2DC937" :"#CC3232";
            sensors.put(String.valueOf(s.getId() - 1), hex);
        }
        data.put("sensors", sensors);

        return ResponseEntity.ok().body(data);
    }
}
