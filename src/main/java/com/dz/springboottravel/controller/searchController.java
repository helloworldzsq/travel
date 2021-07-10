package com.dz.springboottravel.controller;

import com.dz.springboottravel.pojo.Program;
import com.dz.springboottravel.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class searchController {
    @Autowired
    private ProgramService programService;
    @RequestMapping("/search")
    public String Search(String keyword, String time, String budget, Model model) throws ParseException {
        /**链式编程
         eq  =    地名相等
         ge  >=   开始日期大于搜索日期
         le  <=   预算小于等于搜索预算 **/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(time);
        List<Program> lists = programService.query().eq("place", keyword).ge("start_time",date).le("budget", Integer.parseInt(budget)).list();
        model.addAttribute("lists", lists);
        return "tour/search";
    }

}
