package com.kc.dtp.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: Kyle
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiVO {
    private String protocol;
    private String address;
    private String group;
    private Date createTime;
    private Date updateTime;
}
