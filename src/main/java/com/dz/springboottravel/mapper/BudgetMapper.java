package com.dz.springboottravel.mapper;

import com.dz.springboottravel.pojo.Budget;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dz
 * @since 2021-07-07
 */
@Mapper
@Repository
public interface BudgetMapper extends BaseMapper<Budget> {

}
