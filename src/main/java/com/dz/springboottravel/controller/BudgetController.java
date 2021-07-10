package com.dz.springboottravel.controller;

import com.dz.springboottravel.mapper.BudgetMapper;
import com.dz.springboottravel.pojo.BaseBudget;
import com.dz.springboottravel.pojo.Budget;
import com.dz.springboottravel.pojo.User;
import com.dz.springboottravel.service.BudgetService;
import com.dz.springboottravel.service.ProgramService;
import com.dz.springboottravel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class BudgetController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProgramService programService;
    @Autowired
    private BudgetService budgetService;
    //项目id
    @RequestMapping("/tobudget/{id}")
    public String toBudget(@PathVariable("id")Long id, Model model){
        List<Budget> budgets = budgetService.budgetList(id);
        model.addAttribute("budgets",budgets);
        model.addAttribute("id",id);
        return "home/budgetmanger";
    }
//    去添加行程界面   项目id
    @RequestMapping("/toaddBudget/{id}")
    public String toAddBudget(@PathVariable("id")Long id,Model model){
        List<User> users = programService.joinedList(id);
        model.addAttribute("users",users);
        model.addAttribute("id",id);
        return "home/addBudget";
    }
    @RequestMapping("/addBudget")
    public String addBudget(Budget budget){
        budgetService.save(budget);
        Long pid = budget.getPid();
        return "redirect:/tobudget/"+pid;
    }
    //修改行程  budget的id
    @RequestMapping("/toUpdatebudget{id}")
    public String toUpdateBudget(@PathVariable("id")Long id,Model model){
        Budget budget = budgetService.getById(id);
        Long pid = budget.getPid();
        //加入项目的人
        List<User> users = programService.joinedList(pid);
        model.addAttribute("users",users);
        model.addAttribute("budget",budget);
        return "home/updateBudget";
    }
    @RequestMapping("/updateBudget")
    public String updateBudget(Budget budget){
        budgetService.updateById(budget);
        Long pid = budget.getPid();
        return "redirect:/tobudget/"+pid;
    }
    //id 为 budget的id
    @RequestMapping("/deletebudget{id}")
    public String deleteBudget(@PathVariable("id")Long id){
        Long pid = budgetService.getById(id).getPid();
        budgetService.removeById(id);
        return "redirect:/tobudget/"+pid;
    }
    //导出数据到excle表里面
    @RequestMapping("/toAccount/{id}")
    public void excelExport(HttpServletResponse response,@PathVariable("id")Long id) throws IOException {
        budgetService.excelExport(response,id);
    }
}
