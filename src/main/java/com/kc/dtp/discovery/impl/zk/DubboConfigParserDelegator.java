package com.kc.dtp.discovery.impl.zk;

import static com.kc.dtp.discovery.constant.DubboAttribute.ATTR_INTERFACE;
import static com.kc.dtp.discovery.constant.DubboAttribute.ATTR_METHODS;
import static com.kc.dtp.constant.StringConstant.COMMA;

import com.kc.dtp.discovery.bean.DubboConfigHolder;
import com.kc.dtp.discovery.bean.ProviderConfig;
import com.kc.dtp.discovery.bean.ZKDataHolder;
import com.kc.dtp.discovery.constant.DubboDirectory;
import org.apache.dubbo.common.URL;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Kyle
 * @Description: Utils for wrap dubbo config
 */
public class DubboConfigParserDelegator {

    /**
     * Wrap dubbo config
     *
     * @param dubboConfigHolder
     * @param zkDataHolder
     * @return dubboConfigHolder
     */
    public DubboConfigHolder parse(DubboConfigHolder dubboConfigHolder, ZKDataHolder zkDataHolder) {
        // Get path
        String path = zkDataHolder.getPath();

        // Get providers
        ZKDataHolder providerHolder = zkDataHolder.getChilds().stream().
                filter(child -> child.getPath().equals(DubboDirectory.DIR_PROVIDERS)).findFirst().get();
        // Get all providers url
        List<String> providerUrl = providerHolder.getChilds().stream().
                map(ZKDataHolder::getPath).collect(Collectors.toList());

        // Set providers config
        List<ProviderConfig> providerConfigs = new LinkedList<>();
        for (String urlStr : providerUrl) {
            URL url = URL.valueOf(urlStr);
            ProviderConfig providerConfig = new ProviderConfig();

            providerConfig.setHost(url.getHost());
            providerConfig.setPort(url.getPort());
            providerConfig.setServiceName(url.getParameter(ATTR_INTERFACE));
            providerConfig.setMethods(Arrays.asList(url.getParameter(ATTR_METHODS).split(COMMA)));

            providerConfigs.add(providerConfig);
        }

        dubboConfigHolder.setProviders(providerConfigs);

        // TODO: add consumer config
        return dubboConfigHolder;
    }
}
