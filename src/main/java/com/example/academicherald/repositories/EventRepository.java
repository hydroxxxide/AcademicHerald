package com.example.academicherald.repositories;

import com.example.academicherald.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> getAllByRdtIsNull();

    Event findByIdAndRdtIsNull(Long id);
}
