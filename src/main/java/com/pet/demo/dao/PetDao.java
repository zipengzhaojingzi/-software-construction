package com.pet.demo.dao;

import com.pet.demo.entity.Pet;
import com.pet.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetDao {
    List<Pet> findAll();
    List<Pet> findPet(String petState);
    void save(Pet pet);
    void delete(String id);
    void update(Pet pet);
    Pet findOne(String id);
    Pet findWithImagesById(String petId);

    List<Pet> findByName(String username);
    void batchInsertImages(@Param("petId") Long petId,
                           @Param("images") List<String> imageUrls);

    void deleteImagesByPetId(String petId);
    // 批量插入图片
    void batchInsertImages(@Param("petId") String petId,
                           @Param("images") List<String> imageUrls);
    // 更新主图（可选）
    void updateMainImage(@Param("petId") String petId,
                         @Param("mainImage") String mainImage);

}
