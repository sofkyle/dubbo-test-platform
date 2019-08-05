package com.kc.dtp.model;

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
    private String apiUrl;
    private Date createTime;
    private Date updateTime;
}
