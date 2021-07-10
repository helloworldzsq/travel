package com.dz.springboottravel.controller;

import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.dz.springboottravel.pojo.Program;
import com.dz.springboottravel.pojo.User;
import com.dz.springboottravel.service.ProgramService;
import com.dz.springboottravel.service.UserService;
import com.dz.springboottravel.service.impl.ProgramServiceImpl;
import com.dz.springboottravel.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

@Controller
public class UserMangerController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProgramService programService;
    //去申请者管理界面   这里的id是项目的id
    @GetMapping("/tomanage/{id}")
    public String touserManage(@PathVariable("id")long id, HttpSession session, Model model){

        List<User> users = programService.wantedList(id);
        List<User> joinedList = programService.joinedList(id);
        System.out.println(joinedList==null);
        List<User> refusedList = programService.refusedList(id);
        System.out.println(refusedList==null);
//        项目id
        for (User user : users) {
            if (joinedList.contains(user)) {
                session.setAttribute("accept"+user.getId()+id,user.getId());
                model.addAttribute("msg1","已同意");
            }
            else if (refusedList.contains(user)) {
                session.setAttribute("refuse" + user.getId() + id, user.getId());
                model.addAttribute("msg2", "已拒绝");
            }
            else
                model.addAttribute("msg","hello");
        }
        model.addAttribute("pid",id);
        model.addAttribute("users",users);
        return "home/usermanage";
    }
    //申请者的id，项目id    接受申请者
    @RequestMapping("/accept/{id}/{pid}")
    public String accept(@PathVariable("id")long id,@PathVariable("pid")long pid,HttpSession session,Model model){
        programService.addAccept(id,pid);
        return "redirect:/tomanage/"+pid;
    }
    //申请者的id,项目pid   拒绝
    @GetMapping("/deleteuser/{id}/{pid}")
    public String deleteuser(@PathVariable("id")long id,@PathVariable("pid")long pid, HttpSession session,Model model){
        programService.addRefused(id,pid);
        return "redirect:/tomanage/"+pid;
    }
    //去消息界面
    @RequestMapping("/tomessage")
    public String tomessage(HttpSession session,Model model){
        //获取用户
        String userName =(String)session.getAttribute("userName");
        User user = userService.selectUser(userName);
        Long id = user.getId();
        List<String> list = programService.messageList(id);
        if (list.size()==0 || list==null)
            model.addAttribute("msg","暂无消息");
        model.addAttribute("msg",list);
        return "home/message";
    }
}
