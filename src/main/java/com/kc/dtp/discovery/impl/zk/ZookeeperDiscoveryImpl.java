package com.kc.dtp.discovery.impl.zk;

import com.kc.dtp.discovery.Discovery;
import com.kc.dtp.discovery.bean.ZKDataHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Kyle
 * @Description: ZK discovery
 */
@Service("zookeeperDiscovery")
public class ZookeeperDiscoveryImpl implements Discovery<ZKDataHolder> {

    @Value("${zk.rootPath}")
    private String zkRootPath;

    @Resource
    private ZooKeeperReader zooKeeperReader;

    @Override
    public ZKDataHolder readRoot(String serviceName) throws Exception {
        return zooKeeperReader.readService(serviceName);
    }
}
