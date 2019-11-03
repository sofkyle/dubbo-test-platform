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
    private String registerProtocol;
    private String address;
    private String group;
    private String rpcProtocol;
    private String serviceName;
    private String methodName;
    private Date createTime;
    private Date updateTime;
}
