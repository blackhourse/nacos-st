package cn.boot.st.jwt;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @program: nacos-st
 * @author: maht
 * @create: 2020-07-06 17:12
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserToken {
    private Long id;

    private String mobile;

    private String name;

    private String password;

    private Integer isDelete;
}
