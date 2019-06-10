package com.kc.dtp.discovery.impl.zk;

import static com.kc.dtp.constant.StringConstant.EMPTY;
import static com.kc.dtp.discovery.constant.DubboDirectory.DIR_SPLIT;

import com.kc.dtp.discovery.bean.ZKDataHolder;
import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: Kyle
 * @Description: ZK read utils
 */
@Component
public class ZKReader {
    private static final Logger logger = LoggerFactory.getLogger(ZKReader.class);

    @Resource
    private CuratorFramework curator;

    /**
     * Recursive read
     * @param path
     * @return zkDataHolder
     * @throws Exception
     */
    public ZKDataHolder recursiveRead(String path) throws Exception {
        logger.info("resolving path {}", path);
        ZKDataHolder zkDataHolder = new ZKDataHolder();

        // set path
        // decode
        zkDataHolder.setPath(URLDecoder.decode(path,"UTF-8"));

        // set data
        byte[] dataBytes = curator.getData().forPath(path);
        if (Objects.nonNull(dataBytes)) {
            zkDataHolder.setData(new String(dataBytes));
        }

        // get all child nodes
        List<String> childnodes = curator.getChildren().forPath(path);

        if (Objects.nonNull(childnodes) && !childnodes.isEmpty()) {
            List<ZKDataHolder> childHolders = new LinkedList<>();
            for (String nodePath : childnodes) {
                String split = path.equals(DIR_SPLIT) ? EMPTY : DIR_SPLIT;
                ZKDataHolder childHolder = recursiveRead(path + split + nodePath);
                childHolders.add(childHolder);
            }
            zkDataHolder.setChilds(childHolders);
        } else {
            zkDataHolder.setChilds(null);
        }

        return zkDataHolder;
    }
}
