package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.models.Area;
import ies.grupo51.lockedin.repositories.AreaRepository;

@Service
public class AreaService {

    @Autowired
    private AreaRepository repository;

    public Area saveArea(Area area){
        return repository.save(area);
    }

    public List<Area> saveAreas(Set<Area> areas) {
        return repository.saveAll(areas);
    }

    public List<Area> getAreas() {
        return repository.findAll();
    }

    public Area getAreaById(long id) {
        return repository.findById(id).orElse(null);
    }

    public Area updateArea(Area area) {
        Area existingArea = repository.findById(area.getId()).orElse(null);
        
        if (existingArea == null){ return null; }

        existingArea.setName(area.getName());
        existingArea.setConnections(area.getConnections());
        existingArea.setArea_logs(area.getArea_logs());
        existingArea.setAccess(area.getAccess());
        existingArea.setCapacity(area.getCapacity());

        return repository.save(existingArea);
    }
}
