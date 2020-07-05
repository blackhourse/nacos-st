package cn.boot.st.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageEntity implements Serializable {

    /**
     * 页数
     */
    private int page;

    /**
     * 页大小
     */
    private int size;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序类型（0：升序 1：降序）
     */
    private int sortType;

}