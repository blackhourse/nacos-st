package cn.boot.mybatisplus.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "tb_user")
public class User {
    private Long id;

    private String mobile;

    private String name;

    private String password;

    private Integer isDelete;


}