package com.spingbootcours.Tahapart.controller;

import com.spingbootcours.Tahapart.models.Cour;
import com.spingbootcours.Tahapart.service.CourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cours")
public class CourController {

    @Autowired
    private CourService courService;

    @GetMapping
    public List<Cour> getAllCours() {
        return courService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cour> getCourById(@PathVariable Long id) {
        Cour cour = courService.findById(id);
        if (cour == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cour);
    }

    @PostMapping
    public Cour createCour(@RequestBody Cour cour) {
        return courService.save(cour);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cour> updateCour(@PathVariable Long id, @RequestBody Cour cour) {
        Cour existingCour = courService.findById(id);
        if (existingCour == null) {
            return ResponseEntity.notFound().build();
        }
        existingCour.setTitle(cour.getTitle());
        existingCour.setDescription(cour.getDescription());
        existingCour.setDuree(cour.getDuree());
        existingCour.setImage(cour.getImage());
        existingCour.setPdfFile(cour.getPdfFile());
        return ResponseEntity.ok(courService.save(existingCour));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCour(@PathVariable Long id) {
        Cour cour = courService.findById(id);
        if (cour == null) {
            return ResponseEntity.notFound().build();
        }
        courService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

