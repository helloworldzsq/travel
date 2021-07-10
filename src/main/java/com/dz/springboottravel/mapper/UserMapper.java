package com.dz.springboottravel.mapper;

import com.dz.springboottravel.pojo.User;
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
public interface UserMapper extends BaseMapper<User> {
    //根据用户名来查询对象
    public User selectUser(String name);
    //根据用户真实姓名来查询对象
    public User selectUserByName(String name);
}
