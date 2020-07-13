package cn.boot.st.web.context;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * User Security 上下文
 * @program: mall
 * @author: maht
 * @create: 2020-07-13 18:34
 **/
@Data
@Accessors(chain = true)
public class UserSecurityContext {
    /**
     * 用户编号
     */
    private Integer userId;
}
