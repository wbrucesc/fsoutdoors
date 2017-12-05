package com.will.fsoutdoors.repos;

import com.will.fsoutdoors.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    Event findById(long id);
    List<Event> findAll();
    Event findByName(String eventName);
}
