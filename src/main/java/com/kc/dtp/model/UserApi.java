package com.kc.dtp.model;

import com.kc.dtp.bean.InterfaceVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    private InterfaceVO interfaceVO;
    private Date createTime;
    private Date updateTime;
}
