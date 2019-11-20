package com.kc.dtp.bean.model;

import com.kc.dtp.bean.vo.ServiceVO;
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
    private ServiceVO interfaceVO;
    private Date createTime;
    private Date updateTime;
}
