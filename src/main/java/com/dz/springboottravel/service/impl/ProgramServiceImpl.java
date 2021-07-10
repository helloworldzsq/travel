package com.dz.springboottravel.service.impl;

import com.dz.springboottravel.mapper.UserMapper;
import com.dz.springboottravel.pojo.Program;
import com.dz.springboottravel.mapper.ProgramMapper;
import com.dz.springboottravel.pojo.User;
import com.dz.springboottravel.service.ProgramService;
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
public class ProgramServiceImpl extends ServiceImpl<ProgramMapper, Program> implements ProgramService {
    @Autowired
    private ProgramMapper programMapper;
    @Autowired
    private UserMapper userMapper;
    //将同意加入的用户 添加到项目属性中  这里的id是用户的id
    @Override
    public void addAccept(Long id, Long pid) {
        //获取项目原来的内容
        Program program = programMapper.selectById(pid);
        String joined = program.getJoined();
        if (joined==null)
            joined=id+" ";
        else
            joined=joined+id+" ";
        program.setJoined(joined);
        programMapper.updateById(program);
    }

    @Override
    public void addWanted(Long id, Long pid) {
        Program program = programMapper.selectById(pid);
        String wanted = program.getWanted();
        if (wanted==null)
            wanted=id+" ";
        else
            wanted=wanted+id+" ";
        program.setWanted(wanted);
        programMapper.updateById(program);
    }
    @Override
    public void addRefused(Long id, Long pid) {
        Program program = programMapper.selectById(pid);
        String refused = program.getRefused();
        if (refused==null)
            refused=id+" ";
        else
            refused=refused+id+" ";
        program.setRefused(refused);
        programMapper.updateById(program);
    }

    @Override   //项目id
    public List<User> wantedList(Long id) {
        List<User> lists = new LinkedList<>();
        //项目创建者本身
        Program program = programMapper.selectById(id);
        String wanted = program.getWanted();
        if (wanted==null || wanted.equals(""))
            return lists;
        String[] s = wanted.split(" ");
        for (String s1 : s) {
            long l = Long.parseLong(s1);
            User user = userMapper.selectById(l);
            lists.add(user);
        }
        return lists;
    }
    @Override
    public List<User> joinedList(Long id) {
        List<User> lists = new LinkedList<>();
        //项目创建者本身
        Program program = programMapper.selectById(id);
        String joined = program.getJoined();
        if (joined==null || joined.equals(""))
            return lists;
        String[] s = joined.split(" ");
        for (String s1 : s) {
            long l = Long.parseLong(s1);
            User user1 = userMapper.selectById(l);
            lists.add(user1);
        }
        return lists;
    }

    @Override
    public List<User> refusedList(Long id) {
        List<User> lists = new LinkedList<>();
        //项目创建者本身
        Program program = programMapper.selectById(id);
        String refused = program.getRefused();
        if (refused==null || refused.equals(""))
            return lists;
        String[] s = refused.split(" ");
        for (String s1 : s) {
            long l = Long.parseLong(s1);
            User user1 = userMapper.selectById(l);
            lists.add(user1);
        }
        return lists;
    }

    @Override  //用户id
    public List<String> messageList(Long id) {
        //查询用户
        User user = userMapper.selectById(id);
        List<String> list = new LinkedList<>();
        List<Program> programs = programMapper.selectList(null);
        for (Program program : programs) {
            Long id1 = program.getId();
            //获取加入列表
            if (program.getJoined()!=null && !program.getJoined().equals("")) {
                List<User> users = joinedList(id1);
                if (users.contains(user))
                    list.add("恭喜你，你想参加的“" + program.getTitle() + "”项目同意你加入！");
            }
            if (program.getRefused()!=null && !program.getRefused().equals("")) {
                System.out.println("进入拒绝页面");
                List<User> users1 = refusedList(id1);
                if (users1.contains(user))
                    list.add("很遗憾，你想参加的“" + program.getTitle() + "”项目拒绝了你！");
            }
        }
        return list;
    }
}
