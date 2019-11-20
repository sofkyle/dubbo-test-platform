package com.kc.dtp.common;

import com.kc.dtp.bean.vo.ServiceVO;
import org.apache.dubbo.common.URL;
import static com.alibaba.dubbo.common.Constants.*;

import java.util.Arrays;

public class InterfaceParser {

    public static ServiceVO parseUrl(URL url) {
        String group = url.getParameter(GROUP_KEY);
        String version = url.getParameter(VERSION_KEY);
        String timeout = url.getParameter(TIMEOUT_KEY);
        String protocol = url.getProtocol() + "://";
        String interfaceName = url.getServiceInterface();
        String method = url.getParameter(METHODS_KEY);

        return ServiceVO.builder()
                .group(group)
                .version(version)
                .timeout(timeout)
                .protocol(protocol)
                .name(interfaceName)
                .methods(Arrays.asList(method.split(",")))
                .build();
    }
}
