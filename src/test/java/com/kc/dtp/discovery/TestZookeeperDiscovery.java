package com.kc.dtp.discovery;

import com.alibaba.fastjson.JSON;
import com.kc.dtp.discovery.impl.zk.ZKReader;
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
    private ZKReader zkReader;

    @Test
    public void testReadAll() {
        try {
            ZKDataHolder zkDataHolder = zkReader.recursiveRead("/dubbo/cn.touna.finance.repay.proxy.api.SpecialclearTrialServiceProxy");
            System.out.println(JSON.toJSON(zkDataHolder));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
