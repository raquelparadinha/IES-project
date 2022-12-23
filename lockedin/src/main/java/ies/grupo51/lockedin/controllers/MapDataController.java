package ies.grupo51.lockedin.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.Area;
import ies.grupo51.lockedin.services.AreaService;
import ies.grupo51.lockedin.services.InmateService;

@Controller
@RestController
@RequestMapping("/api/map")
public class MapDataController {
    
    // Static variables

    private static final String color1 = "00FFFF";
    private static final String color2 = "0000FF";

    // Services

    @Autowired private AreaService areaService;
    @Autowired private InmateService inmateService;

    // Mappings
    @GetMapping("")
    public ResponseEntity<JSONObject> getMapData() throws ResourceNotFoundException {
        JSONObject data = new JSONObject();
        int totalinmates = (int) inmateService.getInmateCount();
        data.put("totalinmates", totalinmates);

        JSONObject areas = new JSONObject();
        for (Area a : areaService.getAreas()) {
            int countInmates = a.getCurrentInmateIds().size();
            float percent = countInmates / totalinmates;

            String red = Integer.toHexString(0);
            String green = Integer.toHexString((int) (255.0 * percent));
            String blue = Integer.toHexString(255);
            if (red.length() == 1) red = "0" + red;
            if (green.length() == 1) green = "0" + green;
            if (blue.length() == 1) blue = "0" + blue;
            String hex = "#" + red + green + blue;

            JSONObject areaData = new JSONObject();
            areaData.put("color", hex);
            areaData.put("hoverColor", "");
            areas.put(a.getName(), areaData);
        }
        data.put("areas", areas);
        System.out.println(data.toString());

        return ResponseEntity.ok().body(data);
    }
}
