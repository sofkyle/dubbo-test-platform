package com.kc.dtp.discovery.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Kyle
 * @Description: zk data config DTO, contains
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
