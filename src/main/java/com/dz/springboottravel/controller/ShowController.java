package com.dz.springboottravel.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dz.springboottravel.pojo.Program;
import com.dz.springboottravel.pojo.User;
import com.dz.springboottravel.service.ProgramService;
import com.dz.springboottravel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class ShowController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProgramService programService;
    //进入首页
    @RequestMapping("/")
    public String index(Model model){
        return "redirect:/page1";
    }
    @RequestMapping("/page{num}")
    public String page(@PathVariable("num")int num,Model model){
        List<Program> lists = programService.list();
        Date date = new Date();
        List<Program> programs = new LinkedList<>();
        //开始时间超过本地时间的取消展示
        for (Program list : lists) {
           if (list.getStartTime().compareTo(date)>0)
               programs.add(list);
        }
        //查出一共有多少页
        int size = programs.size();
        int pageNum=0;
        if (size<=8){
            pageNum=1;
        }
        else{
            pageNum=size%8==0?size/8:size/8+1;
        }
        Page<Program> page = new Page<>(num,8);
        programService.page(page);
        List<Program> records = page.getRecords();
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("num",num);
        model.addAttribute("programs",records);
        model.addAttribute("date",date);
        return "index";
    }
    //去展示界面   这里的id是项目的id
    @RequestMapping("/toshow{id}")
    public String toshow(@PathVariable("id")Long id,HttpSession session,Model model){
        session.setAttribute("id",id);
        for (Program program : programService.list()) {
            if (program.getId()==id){
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String start = simpleDateFormat.format(program.getStartTime());
                String end = simpleDateFormat.format(program.getEndTime());
                String time=start+"-"+end;
                model.addAttribute("time",time);
                model.addAttribute("program",program);
            }
        }
        return "tour/show";
    }
    @RequestMapping("/submit")
    public String tosubmit(String name,String email,String telephone, HttpSession session, Model model){
        String userName =(String)session.getAttribute("userName");
        //获得项目id
        long id =(long)session.getAttribute("id");
        //没有登录，先登录
        if (userName==null || userName.equals("")) {
            for (Program program : programService.list()) {
                if (program.getId()==id){
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    String start = simpleDateFormat.format(program.getStartTime());
                    String end = simpleDateFormat.format(program.getEndTime());
                    String time=start+"-"+end;
                    model.addAttribute("time",time);
                    model.addAttribute("program",program);
                }
            }
            model.addAttribute("msg", "请先登录！");
            return "tour/show";
        }
        //更新用户信息
        List<User> lists = userService.list();
        for (User list : lists) {
            if (list.getUsername().equals(userName)){
                 list.setName(name);
                 list.setEmail(email);
                 list.setTelephone(telephone);
                 userService.updateById(list);
                 //第一个id是用户的id，第二个是项目的id
                 session.setAttribute("user"+list.getId()+id,list);
            }
        }
        for (Program program : programService.list()) {
            if (program.getId()==id){
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String start = simpleDateFormat.format(program.getStartTime());
                String end = simpleDateFormat.format(program.getEndTime());
                String time=start+"-"+end;
                model.addAttribute("time",time);
                model.addAttribute("program",program);
            }
        }
        model.addAttribute("msg","提交成功！");
        return "tour/show";
    }
}
