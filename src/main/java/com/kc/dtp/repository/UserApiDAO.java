package com.kc.dtp.repository;

import com.kc.dtp.model.UserApi;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author: Kyle
 */
@Repository
public class UserApiDAO {
    @Resource
    private DatabaseClient databaseClient;

    public Flux<UserApi> selectByUserId(Long userId) throws Exception {
        return databaseClient.execute()
                .sql("select * from t_user_api where user_id = " + userId)
                .map((r, rm) -> UserApi.builder()
                                    .apiName(r.get("api_name", String.class))
                                    .apiUrl(r.get("api_user", String.class))
                                    .createTime(r.get("create_time", Date.class))
                                    .build()
                )
                .all();
    }

    public Mono<Long> insert(UserApi userApi) throws Exception {
        return databaseClient.insert().into("t_user_api")
                .value("userId", userApi.getUserId())
                .value("apiName", userApi.getApiName())
                .value("apiUrl", userApi.getApiUrl())
                .value("create_time", new Timestamp(userApi.getCreateTime().getTime()))
                .value("update_time", new Timestamp(userApi.getUpdateTime().getTime()))
                .fetch()
                .first()
                .flatMap(m -> Mono.just((Long) m.get("ID")));
    }
}
