package com.dz.springboottravel.service;

import com.dz.springboottravel.pojo.Program;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dz.springboottravel.pojo.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dz
 * @since 2021-05-23
 */
public interface ProgramService extends IService<Program> {
    //将同意的人加入到数据库中
    public void addAccept(Long id,Long pid);
    //将想参加的人添加到数据库中
    public void addWanted(Long id,Long pid);
    //将拒绝的人添加待数据
    public void addRefused(Long id,Long pid);
    //查询一个项目中想加入的人
    public List<User> wantedList(Long id);
    //查询一个项目中加入的人  项目的id
    public List<User> joinedList(Long id);
    //查询一个项目中拒绝的人
    public List<User> refusedList(Long id);
    //将一个用户是否被接受或拒绝的信息列出来   用户id
    public List<String> messageList(Long id);
}
