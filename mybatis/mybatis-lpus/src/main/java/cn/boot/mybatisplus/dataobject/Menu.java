package cn.boot.mybatisplus.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "tb_menu")
public class Menu {
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