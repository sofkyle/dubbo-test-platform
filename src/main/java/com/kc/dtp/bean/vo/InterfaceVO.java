package com.kc.dtp.bean.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class InterfaceVO {
    private String name;
    private String protocol;
    private String group;
    private String version;
    private String timeout;
    private List<String> methods;
}
