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
        User user = userService.selectUser(userName);
        Long id = user.getId();
        //创建的项目
        List<Program> programs = userService.createdProgarms(id);
        model.addAttribute("programs",programs);
        return "home/program";
    }
    //去添加页面
    @RequestMapping("/toadd")
    public String toAdd(HttpSession session, Model model){
        String userName =(String)session.getAttribute("userName");
        User user = userService.selectUser(userName);
        model.addAttribute("uid",user.getId());
        return "home/add";
    }
    //添加项目
    @RequestMapping("/add")
    public String add(Program program,@RequestParam("picture")MultipartFile file,HttpSession session) throws IOException {
        PathUtil.savePath(program,file);
        String userName =(String)session.getAttribute("userName");
        User user = userService.selectUser(userName);
        program.setJoined(user.getId()+" ");
        programService.save(program);
        return "redirect:/toprogram";
    }
////添加项目
//@RequestMapping("/add")
//public String add(Program program,HttpSession session) throws IOException {
//    String userName =(String)session.getAttribute("userName");
//    User user = userService.selectUser(userName);
//    program.setJoined(user.getId()+" ");
//    programService.save(program);
//    return "redirect:/toprogram";
//}
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
////更新
//@RequestMapping("/update")
//public String update(Program program) throws IOException {
//    programService.updateById(program);
//    return "redirect:/toprogram";
//}
}

