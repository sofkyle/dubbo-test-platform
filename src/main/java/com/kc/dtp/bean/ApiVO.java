package com.kc.dtp.bean;

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
    private Long id;
    private Long userId;
    private String apiName;
    private ProviderVO providerVO;
    private Date createTime;
    private Date updateTime;
}
