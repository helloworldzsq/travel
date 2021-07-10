package com.dz.springboottravel.service.impl;

import com.dz.springboottravel.mapper.ProgramMapper;
import com.dz.springboottravel.pojo.Program;
import com.dz.springboottravel.pojo.User;
import com.dz.springboottravel.mapper.UserMapper;
import com.dz.springboottravel.service.ProgramService;
import com.dz.springboottravel.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dz
 * @since 2021-05-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
   @Autowired
   private UserMapper userMapper;
   @Autowired
   private ProgramMapper programMapper;
   @Autowired
   private ProgramService programService;
    @Override
    public User selectUser(String name) {
        return userMapper.selectUser(name);
    }

    @Override
    public User selectUserByName(String name) {
        return userMapper.selectUserByName(name);
    }

    @Override
    public List<Program> createdProgarms(Long id) {
        List<Program> lists = programMapper.selectList(null);
        List<Program> programs = new LinkedList<>();
        for (Program list : lists) {
            if (list.getUid()==id)
                programs.add(list);
        }
        return programs;
    }

    @Override
    public List<Program> joinedPrograms(Long id) {
        List<Program> list = new LinkedList<>();
        List<Program> programs = programMapper.selectList(null);
        User user = userMapper.selectById(id);
        for (Program program : programs) {
            //加入项目的人
            List<User> users = programService.joinedList(program.getId());
            if (users.contains(user) && program.getUid()!=id)
                list.add(program);
        }
        return list;
    }
}
