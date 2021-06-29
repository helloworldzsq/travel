package com.dz.springboottravel.controller;
import com.dz.springboottravel.pojo.Program;
import com.dz.springboottravel.pojo.User;
import com.dz.springboottravel.service.ProgramService;
import com.dz.springboottravel.service.UserService;
import com.dz.springboottravel.util.PathUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dz
 * @since 2021-05-21
 */
@Controller
public class ProgramController {
    @Autowired
    private ProgramService programService;
    @Autowired
    private UserService userService;
    //去项目管理页面
    @RequestMapping("/toprogram")
    public String toProgram(HttpSession session,Model model){
        String userName = (String)session.getAttribute("userName");
        long res=0;
        for (User user : userService.list()) {
            if (user.getUsername().equals(userName))
                res=user.getId();
        }
        List<Program> lists = programService.list();
        List<Program> programs = new LinkedList<>();
        for (Program list : lists) {
            if (list.getUid()==res)
                programs.add(list);
        }
        model.addAttribute("programs",programs);
        return "home/program";
    }
    //去添加页面
    @RequestMapping("/toadd")
    public String toAdd(HttpSession session, Model model){
        long uid = 0;
        String userName =(String)session.getAttribute("userName");
        System.out.println(userName);
        List<User> list = userService.list();
        for (User user : list) {
            if (user.getUsername().equals(userName))
                uid=user.getId();
        }
        model.addAttribute("uid",uid);
        return "home/add";
    }
    //添加项目
    @RequestMapping("/add")
    public String add(Program program,@RequestParam("picture")MultipartFile file) throws IOException {
        PathUtil.savePath(program,file);
        programService.save(program);
        return "redirect:/toprogram";
    }
    //删除项目
    @RequestMapping("/delete{id}")
    public String delete(@PathVariable("id")Integer id){
         programService.removeById(id);
         return "redirect:/toprogram";
    }
    //去修改页面
    @RequestMapping("/toUpdate{id}")
    public String toUpdate(@PathVariable("id")Integer id,Model model){
        Program program = programService.getById(id);
        model.addAttribute("program",program);
        return "home/update";
    }
    //更新
    @RequestMapping("/update")
    public String update(Program program,@RequestParam("picture")MultipartFile file) throws IOException {
        PathUtil.savePath(program,file);
        programService.updateById(program);
        return "redirect:/toprogram";
    }
    //去申请者管理界面   这里的id是项目的id
    @GetMapping("/tomanage/{id}")
    public String touserManage(@PathVariable("id")long id,HttpSession session,Model model){
        List<User> users = new LinkedList<>();
        List<User> lists = userService.list();
        for (User list : lists) {
            User user =(User)session.getAttribute("user" + list.getId()+id);
            if (user!=null)
                users.add(user);
        }
        session.setAttribute("users",users);
        model.addAttribute("pid",id);
        model.addAttribute("users",users);
        return "home/usermanage";
    }
    //申请者的id，项目id    接受申请者
    @RequestMapping("/accept/{id}/{pid}")
    public String accept(@PathVariable("id")long id,@PathVariable("pid")long pid,HttpSession session,Model model){
        model.addAttribute("msg","已接受");
        List<User> users =(List<User>)session.getAttribute("users");
        //将消息存在session中
        session.setAttribute("message"+id+pid,"accept");
        Program program = programService.getById(pid);
        session.setAttribute("program"+id+pid,program);
        model.addAttribute("users",users);
        return "home/usermanage";
    }
    //申请者的id,项目pid   拒绝并删除申请者
    @GetMapping("/deleteuser/{id}/{pid}")
    public String deleteuser(@PathVariable("id")long id,@PathVariable("pid")long pid, HttpSession session,Model model){
        List<User> users =(List<User>)session.getAttribute("users");
        User user = userService.getById(id);
        users.remove(user);
        session.setAttribute("message"+id+pid,"refuse");
        model.addAttribute("users",users);
        return "home/usermanage";
    }
    //去消息界面
    @RequestMapping("/tomessage")
    public String tomessage(HttpSession session,Model model){
        String userName =(String)session.getAttribute("userName");
        long id=0;
        for (User user : userService.list()) {
            if (user.getUsername().equals(userName))
                id=user.getId();
        }
        for (Program program : programService.list()) {
            String message =(String)session.getAttribute("message" + id + program.getId());
            if (message!=null) {
                if (message.equals("refuse")) {
                    model.addAttribute("msg", "很遗憾，你想参加的“" + program.getTitle() + "”项目拒绝了你！");
                    return "home/message";
                }
                else if (message.equals("accept")) {
                    model.addAttribute("msg", "恭喜你，你想参加的“" + program.getTitle() + "”项目同意你加入！");
                    return "home/message";
                }
            }
        }
        model.addAttribute("msg","暂无消息！");
        return "home/message";
    }
}

