package cn.boot.st.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel
public class MenuDto {
    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "menuCode")
    private String menuCode;

    @ApiModelProperty(value = "parentId")
    private Long parentId;

    @ApiModelProperty(value = "nodeType")
    private Integer nodeType;

    @ApiModelProperty(value = "iconUrl")
    private String iconUrl;

    private Integer sort;

    private String linkUrl;

    private Integer level;

    private String path;

    private Integer isDelete;
}
