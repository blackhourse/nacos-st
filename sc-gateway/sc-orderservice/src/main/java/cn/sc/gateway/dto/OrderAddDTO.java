package cn.sc.gateway.dto;

import java.io.Serializable;

/**
 * 用户添加 DTO
 */
public class OrderAddDTO implements Serializable {

    /**
     * 昵称
     */
    private String name;
    /**
     * 性别
     */
    private Integer gender;

    public String getName() {
        return name;
    }

    public OrderAddDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public OrderAddDTO setGender(Integer gender) {
        this.gender = gender;
        return this;
    }

}
