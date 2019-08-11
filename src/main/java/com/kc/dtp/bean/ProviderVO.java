package com.kc.dtp.bean;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProviderVO {
    private String serviceName;
    private String group;
    private String version;
    private List<ProviderDetailVO> providerDetailVOList;
}
