package com.pet.demo.service;

import com.pet.demo.entity.Pet;

import java.util.List;

public interface PetService {
    List<Pet> findAll();
    List<Pet> findPet(String petState);
    void save(Pet pet);
    void delete(String id);
    void update(Pet pet);
    Pet findOne(String id);
    List<Pet> findByName(String petName);
//    void saveWithImages(Pet pet);
//    void updateWithImages(Pet pet);
    Pet getPetWithImages(String petId);
    void addImagesToPet(String petId, List<String> imageUrls);
}
