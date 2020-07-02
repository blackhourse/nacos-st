package cn.boot.mybatisplus.dto;

import cn.boot.mybatisplus.dataobject.Role;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @program: nacos-st
 * @author: maht
 * @create: 2020-07-02 17:41
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel
public class RoleDto extends Role {
    //添加用户ID
    private Long userId;
}
