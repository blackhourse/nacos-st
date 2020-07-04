package cn.boot.mybatisplus.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "tb_role")
public class Role {
    private Long id;

    private String code;

    private String name;

    private Integer isDelete;

}