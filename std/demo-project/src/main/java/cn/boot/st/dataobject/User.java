package cn.boot.st.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "tb_user")
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String mobile;

    private String name;

    private String password;

    private Integer isDelete;


}