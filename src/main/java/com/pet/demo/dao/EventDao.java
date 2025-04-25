package com.pet.demo.dao;

import com.pet.demo.entity.Event;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventDao  {
    List<Event> findAll();
    Event findById(String eventId);
    List<Event> findByEventName(String eventName);
    void save(Event event);
    void deleteById(String eventId);
    void update(Event event);

    @Insert("insert into t_user_event(user_id, event_id) values(#{userId}, #{eventId})")
    void insertUserEvent(String userId, String eventId);

    @Select("select count(*) from t_user_event where user_id = #{userId} and event_id = #{eventId}")
    int findUserEvent(String userId, String eventId);
}
