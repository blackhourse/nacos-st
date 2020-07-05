package cn.boot.mybatisplus.service.impl;

import cn.boot.mybatisplus.dao.UserMapper;
import cn.boot.mybatisplus.dataobject.User;
import cn.boot.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @program: nacos-st
 * @author: maht
 * @create: 2020-07-02 16:06
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
