package com.dz.springboottravel.controller;



import com.dz.springboottravel.pojo.Program;
import com.dz.springboottravel.pojo.User;
import com.dz.springboottravel.service.ProgramService;
import com.dz.springboottravel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dz
 * @since 2021-05-18
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProgramService programService;
    //去登录页面
    @RequestMapping("/tologin")
    public String toLogin(){
        return "tour/login";
    }
    //去注册页面
    @RequestMapping("/toregister")
    public String toRegister(){
        return "tour/register";
    }
    //注册
    @RequestMapping("/register")
    public String register(User user, Model model, HttpSession session){
        List<User> list = userService.list();
        for (User user1 : list) {
            if (user1.getUsername().equals(user.getUsername())) {
                model.addAttribute("msg", "用户名已存在,请重新取名");
                return "tour/register";
            }
        }
        session.setAttribute("userName",user.getUsername());
        userService.save(user);
        return "redirect:/";
    }
    @RequestMapping("/login")
    public String login(User user,Model model,HttpSession session){
        List<User> list = userService.list();
        for (User user1 : list) {
            if (user1.getUsername().equals(user.getUsername()) &&
                user1.getPassword().equals(user.getPassword())){
                session.setAttribute("userName",user.getUsername());
                User user2 = userService.selectUser(user.getUsername());
                return "redirect:/";
            }
        }
        model.addAttribute("msg","账号或密码输入错误，请重新输入");
        return "tour/login";
    }
    //去主页
    @RequestMapping("/tohome")
    public String toHome(HttpSession session,Model model){
        String userName = (String)session.getAttribute("userName");
        User user = userService.selectUser(userName);
        Long id = user.getId();
        //创建的项目
        List<Program> createdProgarms = userService.createdProgarms(id);
        model.addAttribute("createdProgarms",createdProgarms);
        //加入的项目
        List<Program> joinedPrograms = userService.joinedPrograms(id);
        model.addAttribute("joinedPrograms",joinedPrograms);

        int createNum = createdProgarms.size();
        int joinNum = joinedPrograms.size();

        model.addAttribute("createNum",createNum);
        model.addAttribute("joinNum",joinNum);
        model.addAttribute("programs1",createdProgarms);
        model.addAttribute("programs2",joinedPrograms);
        return "home/index";
    }
    //注销
    @RequestMapping("/logout")
    public String logout(HttpSession session){
          session.removeAttribute("userName");
          return "redirect:/";
    }
}

