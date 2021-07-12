package com.dz.springboottravel.service;

import com.dz.springboottravel.pojo.BaseBudget;
import com.dz.springboottravel.pojo.Budget;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dz.springboottravel.pojo.DetailBudget;
import com.dz.springboottravel.pojo.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dz
 * @since 2021-07-07
 */
public interface BudgetService extends IService<Budget> {

    public List<Budget> budgetList(Long id);
    //查找勾选过的人
    public List<User> queryUsers(Budget budget);
    //将表单汇总  项目id
    public List<BaseBudget> toAccount(Long id);
//   添加详细行程信息
    public List<DetailBudget> DetailBudget(Long id);
    //将查询出的数据导入到excel表格中  项目id
    void excelExport(HttpServletResponse response,Long id) throws IOException;
}
