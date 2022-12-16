package ies.grupo51.lockedin.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.Area;
import ies.grupo51.lockedin.models.Inmate;
import ies.grupo51.lockedin.repositories.AreaRepository;

@Service
public class AreaService {

    @Autowired
    private AreaRepository repository;

    @Autowired
    private InmateService inmateService;

    public Area saveArea(Area area){
        return repository.save(area);
    }

    public List<Area> saveAreas(Set<Area> areas) {
        return repository.saveAll(areas);
    }

    public List<Area> getAreas() throws ResourceNotFoundException {
        List<Area> areas = repository.findAll();
        for (Area a : areas) refreshCurrentInmates(a);
        areas = repository.findAll();
        return areas;
    }

    public Area getAreaById(long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
    }

    public Area updateArea(Area area) throws ResourceNotFoundException {
        Area existingArea = repository.findById(area.getId()).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
        
        if (existingArea == null){ return null; }

        existingArea.setName(area.getName());
        existingArea.setAccess(area.getAccess());
        existingArea.setCapacity(area.getCapacity());
        existingArea.setCurrentInmateIds(area.getCurrentInmateIds());

        return repository.save(existingArea);
    }

    public Area refreshCurrentInmates(Area area) throws ResourceNotFoundException {
        Area existingArea = repository.findById(area.getId()).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
        List<Inmate> inmates = inmateService.getInmatesByAreaId(existingArea);
        List<Long> inmatesids = new ArrayList<Long>();
        for (Inmate i : inmates) inmatesids.add(i.getId());

        existingArea.setCurrentInmateIds(inmatesids);

        return repository.save(existingArea);
    }
}
