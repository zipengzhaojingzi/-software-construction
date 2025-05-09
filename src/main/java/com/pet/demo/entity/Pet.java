package com.pet.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pet {
    private String petId;
    private String petName;
    private String petSex;
    private String petSub;
    private String petType;
    private String petBir;
    private String petDetail;
    private String petPic;
    private List<String> petPics=new ArrayList<>();
    private String petState;




}
