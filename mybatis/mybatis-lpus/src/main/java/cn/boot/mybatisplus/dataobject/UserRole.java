package cn.boot.mybatisplus.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "tb_user_role")
public class UserRole {
    private Long id;

    private Long userId;

    private Long roleId;


}