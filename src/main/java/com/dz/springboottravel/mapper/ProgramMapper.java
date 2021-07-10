package com.dz.springboottravel.mapper;

import com.dz.springboottravel.pojo.Program;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dz
 * @since 2021-05-23
 */
@Mapper
@Repository
public interface ProgramMapper extends BaseMapper<Program> {

}
