package cn.sc.gateway.dto;

import java.io.Serializable;

/**
 * 用户信息 DTO
 */
public class OrderDTO implements Serializable {

    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 昵称
     */
    private String name;
    /**
     * 性别
     */
    private Integer gender;

    public Integer getId() {
        return id;
    }

    public OrderDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public OrderDTO setGender(Integer gender) {
        this.gender = gender;
        return this;
    }
}
