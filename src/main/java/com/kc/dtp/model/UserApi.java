package com.kc.dtp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

/**
 * @author: Kyle
 */
@Table("t_user_api")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApi {
    @Id
    private Long id;
    private Long userId;
    private String apiName;
    private String apiUrl;
    private Date createTime;
    private Date updateTime;
}
