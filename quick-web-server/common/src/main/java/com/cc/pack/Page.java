package com.cc.pack;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页传输类
 * @author cyc cyc0990@163.com
 */
@Data
public class Page<T>{

    /**
     * 分页结果总条数
     */
    private long total;
    /**
     * 默认分页条数
     */
    private int size = 10;
    /**
     * 当前页
     */
    private int current = 1;
    /**
     * 排序方式
     */
    private String orderMethod;
    /**
     * 排序字段
     */
    private String orderField;
    /**
     * 查询参数
     */
    private Map<String, Object> conditions = new HashMap<>();
    /**
     * 查询记录结果
     */
    private List<T> records;

}
