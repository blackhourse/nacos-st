package cn.boot.mybatisplus.service.impl;

import cn.boot.mybatisplus.dao.RoleMapper;
import cn.boot.mybatisplus.dataobject.Role;
import cn.boot.mybatisplus.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @program: nacos-st
 * @author: maht
 * @create: 2020-07-02 16:08
 **/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
