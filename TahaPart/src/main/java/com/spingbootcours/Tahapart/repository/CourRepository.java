package com.spingbootcours.Tahapart.repository;

import com.spingbootcours.Tahapart.models.Cour;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourRepository extends CrudRepository<Cour, Long> {
    List<Cour> findAll();
}

