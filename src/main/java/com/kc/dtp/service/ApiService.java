package com.kc.dtp.service;

import com.kc.dtp.model.UserApi;
import com.kc.dtp.repository.UserApiDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author: Kyle
 */
@Service
public class ApiService {
    @Autowired
    private UserApiDAO userApiDAO;

    public Flux<UserApi> getAllByUserId(Long userId) throws Exception {
        return Flux.just(userApiDAO.selectByUserId(userId));
    }
}
