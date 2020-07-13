package cn.boot.st.web.context;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 管理员上下文
 *
 * @program: mall
 * @author: maht
 * @create: 2020-07-13 18:35
 **/
@Data
@Accessors(chain = true)
public class AdminSecurityContext {
    /**
     * 管理员编号
     */
    private Integer adminId;
    /**
     * 账号编号
     */
    private Integer accountId;
}
