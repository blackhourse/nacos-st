package cn.boot.mybatisplus.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName(value = "tb_menu")
@Accessors(chain = true)
public class Menu {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String menuCode;

    private Long parentId;

    private Integer nodeType;

    private String iconUrl;

    private Integer sort;

    private String linkUrl;

    private Integer level;

    private String path;

    private Integer isDelete;

}