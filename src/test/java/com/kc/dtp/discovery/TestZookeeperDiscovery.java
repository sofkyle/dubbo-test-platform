package com.kc.dtp.discovery;

import com.alibaba.fastjson.JSON;
import com.kc.dtp.discovery.impl.zk.ZooKeeperReader;
import com.kc.dtp.discovery.bean.ZKDataHolder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author: Kyle
 * @Description: Test ZK Discovery
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestZookeeperDiscovery {
    @Resource
    private ZooKeeperReader zooKeeperReader;

    @Test
    public void testReadRoot() {
        try {
            zooKeeperReader.readService("org.apache.dubbo.demo.DemoService");
            System.out.println(JSON.toJSON(ZooKeeperReader.dubboConfigHolderMap));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
