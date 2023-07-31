package io.concertconnect.concerteventmanagement.repository;

import io.concertconnect.concerteventmanagement.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    @Query(value = "SELECT MAX(id) FROM Event")
    Integer getMaxId(); //기본키로 설정한 id의 타입이 Integer이기때문에 매개변수에 Integer



}
