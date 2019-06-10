package com.kc.dtp.discovery.bean;

import lombok.Data;

import java.util.List;

/**
 * @Author: Kyle
 * @Description: zk data config DTO, contains
 */
@Data
public class ZKDataHolder {
    /**
     * ZK path
     */
    private String path;

    /**
     * ZK data
     */
    private String data;

    /**
     * child
     */
    private List<ZKDataHolder> childs;
}
