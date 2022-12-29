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
import ies.grupo51.lockedin.services.AreaService;
import ies.grupo51.lockedin.services.InmateService;

import java.awt.Color;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/api/map")
public class MapDataController {
    
    // Static variables


    // Services

    @Autowired private AreaService areaService;
    @Autowired private InmateService inmateService;

    // Mappings
    @GetMapping("")
    public ResponseEntity<HashMap<String, Object>> getMapData() throws ResourceNotFoundException {
        HashMap<String, Object> data = new HashMap<>();
        int totalinmates = (int) inmateService.getInmateCount();
        data.put("totalinmates", totalinmates);


        HashMap<String, Object> areas = new HashMap<>();
        for (Area a : areaService.getAreas()) {
            HashMap<String, Object> areaData = new HashMap<>();
            
            int countInmates = a.getCurrentInmateIds().size();
            float f = 255 * (countInmates / (float) totalinmates);
            int r = (int) f;
            
            Color color = new Color(r, 255-r, 0);
            String hex = "#"+Integer.toHexString(color.getRGB()).substring(2).toUpperCase();
            areaData.put("color", hex);
            
            areaData.put("hoverColor", "");
            
            data.put("a", hex);
            areas.put(a.getName(), areaData);
        }
        data.put("areas", areas);
        System.out.println(data.toString());

        return ResponseEntity.ok().body(data);
    }
}
