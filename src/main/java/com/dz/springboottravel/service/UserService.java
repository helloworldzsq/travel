package com.dz.springboottravel.service;

import com.dz.springboottravel.pojo.Program;
import com.dz.springboottravel.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dz
 * @since 2021-05-23
 */
public interface UserService extends IService<User> {
    //根据用户名来查询用户
    public User selectUser(String name);
    //根据用户真实姓名来查询对象
    public User selectUserByName(String name);
    //获取用户创建的所有项目  用户id
    public List<Program> createdProgarms(Long id);
    //获取用户加入的项目
    public List<Program> joinedPrograms(Long id);

}
