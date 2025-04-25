package com.pet.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event {
    private String eventId;
    private String eventName;
    private String eventDescription;
    private String eventDate;
    private String eventLocation;
    private String eventApplyNum;
    private String eventImage;

    private boolean isSignUp;//是否已报名
}
