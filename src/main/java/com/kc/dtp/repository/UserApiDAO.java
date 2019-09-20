package com.kc.dtp.repository;

import com.kc.dtp.bean.model.UserApi;
import org.springframework.stereotype.Repository;

/**
 * @author: Kyle
 */
@Repository
public class UserApiDAO {

    public UserApi selectByUserId(Long userId) throws Exception {
        return UserApi.builder().apiName("zyf").build();
    }

    public Long insert(UserApi userApi) throws Exception {
        return 1L;
    }
}
