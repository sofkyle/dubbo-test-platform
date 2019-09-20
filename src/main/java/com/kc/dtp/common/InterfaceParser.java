package com.kc.dtp.common;

import com.kc.dtp.bean.vo.InterfaceVO;
import org.apache.dubbo.common.URL;
import static com.alibaba.dubbo.common.Constants.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class InterfaceParser {

    public static InterfaceVO parseUrl(URL url) {
        String group = url.getParameter(GROUP_KEY);
        String version = url.getParameter(VERSION_KEY);
        String timeout = url.getParameter(TIMEOUT_KEY);
        String protocol = url.getProtocol() + "://";
        String interfaceName = url.getServiceInterface();
        String method = url.getParameter(METHODS_KEY);

        return InterfaceVO.builder()
                .group(group)
                .version(version)
                .timeout(timeout)
                .protocol(protocol)
                .name(interfaceName)
                .methods(Arrays.asList(method.split(",")))
                .build();
    }
}
