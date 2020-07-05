package cn.boot.st.base;

import lombok.Data;

import java.util.Date;

/**
 * DO父类
 *
 */
@Data
public class Entity {

    private Date addTime;

    private Date updateTime;

}