package cn.boot.mybatisplus.service.impl;

import cn.boot.mybatisplus.dao.UserRoleMapper;
import cn.boot.mybatisplus.dataobject.UserRole;
import cn.boot.mybatisplus.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @program: nacos-st
 * @author: maht
 * @create: 2020-07-02 15:51
 **/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
