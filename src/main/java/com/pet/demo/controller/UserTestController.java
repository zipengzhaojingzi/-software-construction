package com.pet.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pet.demo.config.Log;
import com.pet.demo.entity.User;
import com.pet.demo.service.UserService;
import com.pet.demo.utils.ValidateImageCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/front")
public class UserTestController {
    @Autowired
    private UserService userService;


    @GetMapping("/user")
    public String findAll(Model model, @RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "searchName",required = false) String searchName){
        if(StringUtils.isEmpty(searchName)){
            PageHelper.startPage(pageNum,5);
            List<User> userList=userService.findAll();
            PageInfo<User> pageInfo = new PageInfo<>(userList);
            model.addAttribute("users",pageInfo);
            return "user";
        }
        else {
            String name='%'+searchName+'%';
            PageHelper.startPage(pageNum,5);
            List<User> users=userService.findByName(name);
            PageInfo<User> pageInfo = new PageInfo<>(users);
            model.addAttribute("users",pageInfo);
            return "user";
        }
    }

    @PostMapping("/save")
    public String save( User user){
        //        判断添加还是修改操作
        if(StringUtils.isEmpty(user.getUserId())){
            if(user.getIdentity()==null){
            user.setIdentity("user"); // 设置默认
            }
                userService.save(user);
        }else {


            userService.updateIdentity(user.getUserId(), user.getIdentity());

            userService.update(user);
        }
        return "redirect:/front/user";
    }


    @PostMapping("/infoUpdate")
    public String infoUpdate(User user, HttpSession session, RedirectAttributes attributes){
        User sessionUser = (User) session.getAttribute("user");
        sessionUser.setUserName(user.getUserName());

        sessionUser.setUserAge(user.getUserAge());
        sessionUser.setUserSex(user.getUserSex());
        sessionUser.setUserTelephone(user.getUserTelephone());
        sessionUser.setUserEmail(user.getUserEmail());
        sessionUser.setUserAddress(user.getUserAddress());
        sessionUser.setUserState(user.getUserState());

     userService.update(sessionUser);

        session.setAttribute("user",sessionUser);
        attributes.addFlashAttribute("message","保存成功");
        return "redirect:/info";
    }
//   ajax的密码修改功能
    @PostMapping("/updatePsd")
    @ResponseBody
    public String updatePsd( String oldPassword,String newPassword,String psdId
                             )  {
        User user = userService.findOne(psdId);
        String msg = "0";
        if(oldPassword.isEmpty()){
            return msg;
        }
        if(oldPassword.equals(user.getUserPassword())){
            user.setUserPassword(newPassword);
            userService.update(user);
           msg="修改成功";
        }else {
            msg="修改失败";
        }
        return msg;
    }

   @GetMapping("/findone")
   public String findone( Model model){
      User user=userService.findOne("fe7bdb28-dafb-4ea9-9add-9ba1210e8895");
       System.out.println(user);
      return "pet/success";
    }



    @GetMapping("/findByName")
    public String findByName( Model model, @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(name = "searchName",required = false) String searchName){
        String name="%"+searchName+"%";
        PageHelper.startPage(pageNum,5);
        List<User> users=userService.findByName(name);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        model.addAttribute("users",pageInfo);
        return "user";

    }


    @GetMapping("/delete")
    public String delete( String userId){
        userService.delete(userId);
        return "redirect:/front/user";
    }

    @PostMapping("/applyVolunteer")
    @ResponseBody
    public String applyVolunteer(HttpSession session) {
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) {
            return "请先登录";
        }
        try {
//            System.out.println("更新前的id：" + sessionUser.getUserId());  // 调试输出
            // 直接更新 identity 到 pending，避免全字段操作
            userService.updateIdentity(sessionUser.getUserId(), "pending");

            // 强制刷新会话中的用户数据
            User updatedUser = userService.findOne(sessionUser.getUserId());
//            System.out.println("更新后的身份：" + updatedUser.getIdentity());  // 调试输出

            session.setAttribute("user", updatedUser);

            return "申请已提交，等待管理员审核";
        } catch (Exception e) {
            e.printStackTrace();
            return "申请提交失败，请稍后重试";
        }
    }

    // 处理管理员审批
    @GetMapping("/approveVolunteer")
    public String approveVolunteer(String userId) {
        User user = userService.findOne(userId);
        user.setIdentity("volunteer");
        userService.updateIdentity(userId, "volunteer");
        return "redirect:/front/user";
    }

}
