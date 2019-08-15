package com.kc.dtp.invoker.bean;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class InvokerParamDTO {
    private List<String> types;
    private List<Object> values;
}
