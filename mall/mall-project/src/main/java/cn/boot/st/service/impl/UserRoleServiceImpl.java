package cn.boot.st.service.impl;

import cn.boot.st.dao.UserRoleMapper;
import cn.boot.st.dataobject.UserRole;
import cn.boot.st.service.UserRoleService;
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
