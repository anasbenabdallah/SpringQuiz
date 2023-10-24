package com.spingbootcours.Tahapart.service;
import com.spingbootcours.Tahapart.models.Cour;
import java.util.List;

public interface CourService {
    List<Cour> findAll();
    Cour findById(Long id);
    Cour save(Cour cour);
    void deleteById(Long id);
}