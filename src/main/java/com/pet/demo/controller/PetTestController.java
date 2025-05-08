package com.pet.demo.controller;

import com.pet.demo.entity.Pet;
import com.pet.demo.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/PetTest")
public class PetTestController {
    @Autowired
    private PetService petService;

    @GetMapping("/pet")
    public String findAll(Model model){
        List<Pet> pets=petService.findAll();
        model.addAttribute("pets",pets);
        return "pet";
    }

//    @PostMapping("/save")
//    public String savePet(@RequestParam(value = "petPic") MultipartFile file,HttpServletRequest request){
////        if (file.isEmpty()) {
////            System.out.println("文件为空空");
////        }
//        String fileName = file.getOriginalFilename();  // 文件名
//        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
//        String filePath = "D://UploadFile//"; // 上传后的路径
//        fileName = UUID.randomUUID() + suffixName; // 新文件名
//        File dest = new File(filePath + fileName);
//        if (!dest.getParentFile().exists()) {
//            dest.getParentFile().mkdirs();
//        }
//        try {
//            file.transferTo(dest);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String filename = "/upload/" + fileName;
////        System.out.println(filename);
//        Pet pet = new Pet();
//        pet.setPetName(request.getParameter("petName"));
//        pet.setPetDetail(request.getParameter("petDetail"));
//        pet.setPetSex(request.getParameter("petSex"));
//        pet.setPetState(request.getParameter("petState"));
//        pet.setPetSub(request.getParameter("petSub"));
//        pet.setPetType(request.getParameter("petType"));
//        pet.setPetBir(request.getParameter("petBir"));
//        pet.setPetPic(filename);
//        //判断进行添加还是修改操作
//
//        if(StringUtils.isEmpty(request.getParameter("petId"))){
//            petService.save(pet);
//        }else {
//            pet.setPetId(request.getParameter("petId"));
//            petService.update(pet);
//        }
//        return "redirect:/PetTest/pet";
//    }
@PostMapping("/save")
public String savePet(
        @RequestParam("images") MultipartFile[] files,
        @RequestParam("mainImage") String mainImageName,
        HttpServletRequest request) throws IOException {

    // ✅ 新增宠物时，先生成 petId
    String petId = request.getParameter("petId");
    if (StringUtils.isEmpty(petId)) {
        petId = UUID.randomUUID().toString();
    }

    // ✅ 构建宠物对象
    Pet pet = new Pet();
    pet.setPetId(petId);
    pet.setPetName(request.getParameter("petName"));
    pet.setPetSex(request.getParameter("petSex"));
    pet.setPetSub(request.getParameter("petSub"));
    pet.setPetType(request.getParameter("petType"));
    pet.setPetBir(request.getParameter("petBir"));
    pet.setPetDetail(request.getParameter("petDetail"));
    pet.setPetState(request.getParameter("petState"));

    // ✅ 处理图片上传
    List<String> imageUrls = new ArrayList<>();
    String mainImageUrl = null;

    for (MultipartFile file : files) {
        if (!file.isEmpty()) {
            String url = processSingleFile(file, petId); // ✅ 使用已生成的 petId
            imageUrls.add(url);
            if (file.getOriginalFilename().equals(mainImageName)) {
                mainImageUrl = url;
            }
        }
    }

    // ✅ 设置主图（如果没有指定，使用第一张）
    if (mainImageUrl == null && !imageUrls.isEmpty()) {
        mainImageUrl = imageUrls.get(0);
    }

    pet.setPetPic(mainImageUrl);
    pet.setPetPics(imageUrls);
    String id=petId;
//    System.out.println(id);
    // ✅ 保存宠物信息
    if (StringUtils.isEmpty(request.getParameter("petId"))) {
        petService.save(pet);
        petService.addImagesToPet(id, imageUrls);
//        System.out.println(id);
    } else {
        petService.update(pet);
        petService.addImagesToPet(id, imageUrls);

    }

    return "redirect:/PetTest/pet";
}


    @PostMapping("/{petId}/uploadImages")
    public String uploadImages(
            @PathVariable String petId,
            @RequestParam("images") MultipartFile[] files) throws IOException {

        // 处理图片上传
        List<String> newImageUrls = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String path = processSingleFile(file, petId);
                newImageUrls.add(path);
            }
        }


        // 更新图片关联
        petService.addImagesToPet(petId, newImageUrls);

        return "redirect:/PetTest/pet"; // 返回宠物详情页
    }
    private String processSingleFile(MultipartFile file, String petId) throws IOException {
//        // 创建宠物专属目录
//        String NAME=petId.substring(0, Math.min(petId.length(), 6));
//
//        String petFolder = "D://UploadFile//" + NAME + "//";
//        new File(petFolder).mkdirs();
//
//        String fileName = UUID.randomUUID() +
//                file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//        File dest = new File(petFolder + fileName);
//
//        file.transferTo(dest);
//
//        return "/upload/" + NAME + "/" + fileName; // 返回包含路径的URL
        // 宠物动态目录
        String NAME = petId.substring(0, Math.min(petId.length(), 6));
        String petFolder = "D://UploadFile//" + NAME + "//";
        new File(petFolder).mkdirs();

        String fileName = UUID.randomUUID() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        File dest = new File(petFolder + fileName);

        file.transferTo(dest);
        return "/upload/" + NAME + "/" + fileName;

    }



    @GetMapping("/delete")
    public String deletePet(String petId){
        petService.delete(petId);
        return "redirect:/PetTest/pet";
    }


    @GetMapping("/findByName")
    public String findByName(Model model,@RequestParam(name = "searchName",required = false) String searchName){
        String name='%'+searchName+'%';
        List<Pet> pets=petService.findByName(name);
        model.addAttribute("pets",pets);
        return "pet";
    }
}
