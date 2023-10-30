package com.example.demoIO.service;

import com.example.demoIO.entity.Entity;
import com.example.demoIO.repository.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceImp implements com.example.demoIO.service.Service {
    private final Repository repository;

    public ServiceImp(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void saveAll(List<Entity> entities) {
        repository.saveAll(entities);
    }
}
