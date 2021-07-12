package com.dz.springboottravel.service.impl;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.dz.springboottravel.mapper.ProgramMapper;
import com.dz.springboottravel.mapper.UserMapper;
import com.dz.springboottravel.pojo.BaseBudget;
import com.dz.springboottravel.pojo.Budget;
import com.dz.springboottravel.mapper.BudgetMapper;
import com.dz.springboottravel.pojo.DetailBudget;
import com.dz.springboottravel.pojo.User;
import com.dz.springboottravel.service.BudgetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dz.springboottravel.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dz
 * @since 2021-07-07
 */
@Service
public class BudgetServiceImpl extends ServiceImpl<BudgetMapper, Budget> implements BudgetService {
     @Autowired
     private BudgetMapper budgetMapper;
     @Autowired
     private UserMapper userMapper;
     @Autowired
     private ProgramService programService;
    @Override  //id为项目的id
    public List<Budget> budgetList(Long id) {
        List<Budget> list = new ArrayList<>();
        List<Budget> budgets = budgetMapper.selectList(null);
        for (Budget budget : budgets) {
            if (budget.getPid()==id)
                list.add(budget);
        }
        return list;
    }

    @Override
    public List<User> queryUsers(Budget budget) {
        List<User> users = new LinkedList<>();
        String people = budget.getPeople();
        //选中用户姓名的集合
        String[] split = people.split(",");
        for (String s : split) {
            User user = userMapper.selectUserByName(s);
            users.add(user);
        }
        return users;
    }

    @Override
    public List<BaseBudget> toAccount(Long id) {
        //找到该项目的账单
        List<Budget> budgets = budgetList(id);
        //找到参与该项目的所有人
        List<User> users = programService.joinedList(id);
        int[] res = new int[1000];
        for (Budget budget : budgets) {
            String peoples = budget.getPeople();
            String[] names = peoples.split(",");
            Integer cost = budget.getCost();
            for (int i = 0; i < names.length; i++) {
                User user = userMapper.selectUserByName(names[i]);
                Long id1 =user.getId();
                res[Math.toIntExact(id1)]+=cost/names.length;
            }
        }
        LinkedList<BaseBudget> lists = new LinkedList<>();
        for (User user : users) {
            BaseBudget budget = new BaseBudget(user.getName(), res[Math.toIntExact(user.getId())]);
            lists.add(budget);
        }
        return lists;
    }

    @Override
    public List<DetailBudget> DetailBudget(Long id) {
        List<Budget> budgets = budgetList(id);
        List<DetailBudget> detailBudgets = new LinkedList<>();
        for (Budget budget : budgets) {
            DetailBudget detailBudget = new DetailBudget(budget.getPlace(), budget.getDate(), budget.getCost(), budget.getPeople());
           detailBudgets.add(detailBudget);
        }
        return detailBudgets;
    }


    @Override
    public void excelExport(HttpServletResponse response,Long id) throws IOException {
        List<BaseBudget> list = toAccount(id);
        List<DetailBudget> budgets = DetailBudget(id);
        String fileName = "用户账单";
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + new String( fileName.getBytes("gb2312"), "ISO8859-1" ) + ".xls");
        ServletOutputStream out = response.getOutputStream();
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLS,true);
        Sheet sheet = new Sheet(1,0,BaseBudget.class);
        Sheet sheet1 = new Sheet(2, 0, DetailBudget.class);
        //设置自适应宽度
        sheet.setAutoWidth(Boolean.TRUE);
        sheet1.setAutoWidth(Boolean.TRUE);
        sheet.setSheetName("用户账单");
        sheet1.setSheetName("账单汇总");
        writer.write(list,sheet);
        writer.write(budgets, sheet1);
        writer.finish();
        out.flush();
        response.getOutputStream().close();
        out.close();
    }
}
