package com.kc.dtp.bean;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProviderDetailVO {
    private String host;
    private int port;
    private List<String> methods;
}
