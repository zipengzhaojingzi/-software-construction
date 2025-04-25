package com.pet.demo.service;

import com.pet.demo.entity.Event;
import java.util.List;

public interface EventService {
    List<Event> findAll();
    Event findById(String eventId);
    void save(Event event);
    void deleteById(String  eventId);
    void update(Event event);
    List<Event> findByEventName(String eventName);
    // 假设有一个方法来添加参与者
//    public boolean addParticipant(Long eventId, String userName) {
//        // 这里实现具体的业务逻辑，例如将用户添加到活动的参与者列表中
//        // 可以操作数据库或者内存中的数据结构
//        // 示例：假设有一个 map 来存储参与者信息
//        // participantsMap.put(eventId, userName);
//
//        // 返回 true 表示成功，false 表示失败
//        return true;
//    }

    void insertUserEvent(String userId, String eventId);

    int findUserEvent(String userId, String eventId);
}
