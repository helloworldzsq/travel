package com.dz.springboottravel.controller;

import com.dz.springboottravel.pojo.User;
import com.dz.springboottravel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class settingController {
    @Autowired
    private UserService userService;
    //去设置界面
    @RequestMapping("/tosetting")
    public String toSetting(Model model, HttpSession session){
        String userName =(String)session.getAttribute("userName");
        User user = userService.selectUser(userName);
        model.addAttribute("user",user);
        return "home/setting";
    }
    @RequestMapping("/toSetUpdate{id}")
    public String toupdate(@PathVariable("id")long id,Model model){
        User user = userService.getById(id);
        model.addAttribute("user",user);
        return "home/setting-update";
    }
    @RequestMapping("/SetUpdate")
    public String update(User user,Model model){
        userService.updateById(user);
        model.addAttribute("user",user);
        return "home/setting";
    }
}
