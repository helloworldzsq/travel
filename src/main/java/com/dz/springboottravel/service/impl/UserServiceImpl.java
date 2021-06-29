package com.dz.springboottravel.service.impl;

import com.dz.springboottravel.pojo.User;
import com.dz.springboottravel.mapper.UserMapper;
import com.dz.springboottravel.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
