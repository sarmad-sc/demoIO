package com.example.demoIO.repository;

import com.example.demoIO.entity.Entity;
import org.springframework.data.repository.CrudRepository;

public interface Repository extends CrudRepository<Entity, Long> {

}
