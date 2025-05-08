package com.pet.demo.service.Impl;

import com.pet.demo.dao.PetDao;
import com.pet.demo.entity.Pet;
import com.pet.demo.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PetServiceImpl implements PetService {
    @Autowired
    private PetDao petDao;

    @Override
    public List<Pet> findAll() {
        return petDao.findAll();
    }

    @Override
    public List<Pet> findPet(String petState) {
        return petDao.findPet(petState);
    }

    @Override
    public void save(Pet pet) {
//        pet.setPetId(UUID.randomUUID().toString());
        petDao.save(pet);
    }

    @Override
    public void delete(String id) {
        petDao.delete(id);
    }

    @Override
    public void update(Pet pet) {
        petDao.update(pet);

    }

    @Override
    public Pet findOne(String id) {
        return petDao.findOne(id);
    }

    @Override
    public List<Pet> findByName(String petName) {
        return petDao.findByName(petName);
    }
    @Override
    public Pet getPetWithImages(String petId) {
        Pet pet = petDao.findWithImagesById(petId);

        // 兼容旧数据：如果新字段为空但旧字段有值
        if (pet.getPetPics().isEmpty() && pet.getPetPic() != null) {
            pet.getPetPics().add(pet.getPetPic());
        }

        return pet;
    }

    @Transactional
    @Override
    public void addImagesToPet(String petId, List<String> imageUrls) {
//        // 保存新图片到关联表
//        petDao.batchInsertImages(petId, imageUrls);
//
//        // 可选：更新主表首图（如果需要）
//        if (!imageUrls.isEmpty()) {
//            petDao.updateMainImage(petId, imageUrls.get(0));
//        }
        petDao.deleteImagesByPetId(petId);
        if (!imageUrls.isEmpty()) {
            petDao.batchInsertImages(petId, imageUrls);
        }
    }

}
