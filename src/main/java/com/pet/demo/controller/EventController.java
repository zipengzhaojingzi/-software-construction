package com.pet.demo.controller;

import com.pet.demo.entity.Event;
import com.pet.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/Event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public String findAll(Model model) {
        List<Event> events = eventService.findAll();
        model.addAttribute("events", events);

        return "events";
    }

//    @GetMapping("/publish")
//    public String listEvents(Model model) {
//        // 假设有一个方法可以获取所有活动列表
//        List<Event> events = eventService.findAll();
//        model.addAttribute("events", events);
//        return "publish"; // 视图名称
//    }

    @GetMapping("/publish")
    public String showPublishPage(Model model) {
//        System.out.println("进入发布活动页面");
        List<Event> events = eventService.findAll();
        model.addAttribute("events", events);
        return "publish"; // 返回 publish.html
    }

//    private List<Event> getEventsFromDatabase() {
//        // 这里是从数据库获取事件列表的逻辑
//        return Arrays.asList(
//                new Event("1", "活动1", "活动1描述", "2023-10-01", "地点1", "/img/event1.jpg"),
//                new Event("2", "活动2", "活动2描述", "2023-10-02", "地点2", "/img/event2.jpg")
//        );
//    }

//    @PostMapping("/save")
//    public String saveEvent(@RequestParam(value = "eventImage", required = false) MultipartFile file, HttpServletRequest request) {
//        String fileName = "";
//        if (file != null && !file.isEmpty()) {
//            String originalFileName = file.getOriginalFilename();  // 文件名
//            String suffixName = originalFileName.substring(originalFileName.lastIndexOf("."));  // 后缀名
//            String filePath = "E://upload//"; // 上传后的路径
//            fileName = UUID.randomUUID() + suffixName; // 新文件名
//            File dest = new File(filePath + fileName);
//            if (!dest.getParentFile().exists()) {
//                dest.getParentFile().mkdirs();
//            }
//            try {
//                file.transferTo(dest);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            fileName = "/upload/" + fileName;
//        }
//
//        Event event = new Event();
//        event.setEventName(request.getParameter("eventName"));
//        event.setEventDescription(request.getParameter("eventDescription"));
//        event.setEventDate(request.getParameter("eventDate"));
//        event.setEventLocation(request.getParameter("eventLocation"));
//        event.setEventImage(fileName);
//
//        // 判断进行添加还是修改操作
//        if (StringUtils.isEmpty(request.getParameter("eventId"))) {
//            eventService.save(event);
//        } else {
//            event.setEventId(request.getParameter("eventId"));
//            eventService.update(event);
//        }
//        return "redirect:/Event/publish";
//    }
@PostMapping("/save")
public String saveEvent(@RequestParam(value = "eventImage", required = false) MultipartFile file, HttpServletRequest request) {
    String fileName = "";
    if (file != null && !file.isEmpty()) {
        String originalFileName = file.getOriginalFilename();  // 文件名
        String suffixName = originalFileName.substring(originalFileName.lastIndexOf("."));  // 后缀名
        String filePath = "D://UploadFile//"; // 上传后的路径
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileName = "/upload/" + fileName;
    }

    String eventId = request.getParameter("eventId");
    Event event;
    if (StringUtils.isEmpty(eventId)) {
        event = new Event();
        event.setEventApplyNum("0"); // 初始化申请人数为0
    } else {
        event = eventService.findById(eventId); // 从数据库获取现有的Event对象
        // 如果不需要更新申请人数，则不需要做任何操作
        // 如果需要更新其他字段，则继续设置其他字段
    }

    event.setEventName(request.getParameter("eventName"));
    event.setEventDescription(request.getParameter("eventDescription"));
    event.setEventDate(request.getParameter("eventDate"));
    event.setEventLocation(request.getParameter("eventLocation"));
    event.setEventImage(fileName);

    // 判断进行添加还是修改操作
//    if (StringUtils.isEmpty(request.getParameter("eventId"))) {
//        eventService.save(event);
//    } else {
//        event.setEventId(request.getParameter("eventId"));
//        eventService.update(event);
//    }
//    return "redirect:/Event/publish";
//}
    if (StringUtils.isEmpty(eventId)) {
        eventService.save(event);
    } else {
        event.setEventId(eventId);
        eventService.update(event);
    }
    return "redirect:/Event/publish";
}

//    @PostMapping("/participate/{eventId}")
//    @ResponseBody
//    public Map<String, String> participateInEvent(@PathVariable Long eventId, @RequestBody Map<String, String> requestBody) {
//        String userName = requestBody.get("userName");
//
//        // 调用服务层的方法来处理参与逻辑
//        boolean isSuccessful = eventService.addParticipant(eventId, userName);
//
//        Map<String, String> response = new HashMap<>();
//        if (isSuccessful) {
//            response.put("status", "success");
//            response.put("message", "报名成功！");
//        } else {
//            response.put("status", "failure");
//            response.put("message", "报名失败，请重试。");
//        }
//
//        return response;
//    }

    @GetMapping("/delete")
    public String deleteEvent(@RequestParam String eventId) {
        eventService.deleteById(eventId);
        return "redirect:/Event/publish";
    }

    @GetMapping("/findByEventName")
    public String findByEventName(Model model, @RequestParam(name = "searchName", required = false) String searchName) {
        String name = '%' + searchName + '%';
        List<Event> events = eventService.findByEventName(name);
        model.addAttribute("events", events);
        return "events";
    }

    @PostMapping("/signUp")
        public String signUp(@RequestParam String eventId, HttpSession session) {
        String userId = session.getAttribute("Id").toString();
        eventService.insertUserEvent(userId, eventId);
        Event byId = eventService.findById(eventId);
        String eventApplyNum = byId.getEventApplyNum();
        byId.setEventApplyNum(String.valueOf(Integer.parseInt(eventApplyNum) + 1));
        eventService.update(byId);
        return "redirect:/Event/events";
    }
}
