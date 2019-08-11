package com.kc.dtp.model;

import com.kc.dtp.bean.ProviderVO;
import com.kc.dtp.discovery.bean.ProviderConfig;
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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApi {
    private Long id;
    private Long userId;
    private String apiName;
    private String apiUrl;
    private ProviderVO providerVO;
    private Date createTime;
    private Date updateTime;
}
