package com.spingbootcours.Tahapart.service;

import com.spingbootcours.Tahapart.models.Cour;
import com.spingbootcours.Tahapart.repository.CourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourServiceImpl implements CourService {

    @Autowired
    private CourRepository courRepository;

    @Override
    public List<Cour> findAll() {
        return courRepository.findAll();
    }

    @Override
    public Cour findById(Long id) {
        Optional<Cour> optional = courRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public Cour save(Cour cour) {
        return courRepository.save(cour);
    }

    @Override
    public void deleteById(Long id) {
        courRepository.deleteById(id);
    }
}

