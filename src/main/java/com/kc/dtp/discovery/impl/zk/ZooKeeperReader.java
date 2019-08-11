package com.kc.dtp.discovery.impl.zk;

import static com.kc.dtp.constant.StringConstant.EMPTY;
import static com.kc.dtp.discovery.constant.DubboDirectory.DIR_SPLIT;

import com.kc.dtp.discovery.bean.DubboConfigHolder;
import com.kc.dtp.discovery.bean.ZKDataHolder;
import com.kc.dtp.discovery.constant.DubboAttribute;
import com.kc.dtp.discovery.constant.DubboDirectory;
import com.kc.dtp.exception.ServiceNotFoundException;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.KeeperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Kyle
 * @Description: ZK read utils
 */
@Component
public class ZooKeeperReader {
    private static final Logger logger = LoggerFactory.getLogger(ZooKeeperReader.class);

    public static final Map<String, ZKDataHolder> zkDataHolderMap = new ConcurrentHashMap<>();

    public static final Map<String, DubboConfigHolder> dubboConfigHolderMap = new ConcurrentHashMap<>();

    private static final DubboConfigParserDelegator delegator = new DubboConfigParserDelegator();

    @Value("${zk.rootPath}")
    private String zkRootPath;

    @Resource
    private CuratorFramework curator;

    /**
     * read service
     *
     * @param serviceName
     * @return zkDataHolder
     * @throws Exception
     */
    public ZKDataHolder readService(String serviceName) throws Exception {
        logger.info("service name {}", serviceName);

        if (zkDataHolderMap.containsKey(serviceName)) {
            return zkDataHolderMap.get(serviceName);
        } else {
            zkDataHolderMap.put(serviceName, new ZKDataHolder());
        }

        ZKDataHolder zkDataHolder = zkDataHolderMap.get(serviceName);

        // get service path
        String servicePath = zkRootPath.concat(serviceName);
        zkDataHolder.setPath(servicePath);

        // set data

        byte[] dataBytes;
        try {
            dataBytes = curator.getData().forPath(servicePath);
        } catch (KeeperException.NoNodeException nne) {
            throw new ServiceNotFoundException(serviceName);
        }

        // get all child nodes
        List<String> childnodes = curator.getChildren().forPath(servicePath);

        if (Objects.nonNull(childnodes) && !childnodes.isEmpty()) {
            List<ZKDataHolder> childHolders = new LinkedList<>();
            for (String nodeName : childnodes) {
                ZKDataHolder childHolder = readNode(servicePath, nodeName);
                if (Objects.nonNull(childHolder)) {
                    childHolders.add(childHolder);
                }
            }
            zkDataHolder.setChilds(childHolders);
        } else {
            zkDataHolder.setChilds(null);
        }

        // parse dubbo config
        DubboConfigHolder dubboConfigHolder = dubboConfigHolderMap.get(serviceName);
        if (dubboConfigHolder == null) {
            dubboConfigHolder = new DubboConfigHolder();
            dubboConfigHolder = delegator.parse(dubboConfigHolder, zkDataHolder);

            // register dubbo config
            dubboConfigHolderMap.put(serviceName, dubboConfigHolder);
        }

        return zkDataHolder;
    }

    /**
     * Read service configuration
     *
     * @param path
     * @param nodeName
     * @return
     * @throws Exception
     */
    private ZKDataHolder readNode(String path, String nodeName) throws Exception {
        // read providers
        if (DubboDirectory.DIR_PROVIDERS.equals(nodeName)) {
            String nodePath = path.concat(DIR_SPLIT).concat(nodeName);
            List<String> childnodes = curator.getChildren().forPath(nodePath);
            List<ZKDataHolder> providers = new LinkedList<>();
            for (String providerPath : childnodes) {
                String decodePath = URLDecoder.decode(providerPath,"UTF-8");
                providers.add(ZKDataHolder.builder().path(decodePath).build());
            }
            return ZKDataHolder.builder().path(nodeName).childs(providers).build();
        } else {
            return null;
        }
    }
}
