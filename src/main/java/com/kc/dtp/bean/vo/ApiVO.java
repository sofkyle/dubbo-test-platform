package com.kc.dtp.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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
    private String registryGroup;
    private String rpcProtocol;
    private String serviceGroup;
    private String serviceName;
    private String methodName;
    private List<ParamVO> paramList;
    private Date createTime;
    private Date updateTime;
}
