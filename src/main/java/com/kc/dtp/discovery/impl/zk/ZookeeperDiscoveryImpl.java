package com.kc.dtp.discovery.impl.zk;

import com.kc.dtp.discovery.Discovery;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Author: Kyle
 * @Description: ZK discovery
 */
@Service("zookeeperDiscovery")
public class ZookeeperDiscoveryImpl implements Discovery {

    @Override
    public List readAll() throws Exception {
        return null;
    }
}
