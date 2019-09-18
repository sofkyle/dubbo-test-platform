package com.kc.dtp.bean;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class InterfaceVO {
    private String name;
    private String group;
    private String version;
    private List<String> methods;
}
