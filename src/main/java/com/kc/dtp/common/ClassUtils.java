package com.kc.dtp.common;


import com.google.common.collect.Lists;
import com.kc.dtp.bean.vo.ParamVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author: Kyle
 */
@Slf4j
public class ClassUtils {

    /**
     * Parse parameter for dubbo invoker
     * @param paramList
     * @return
     */
    public static Pair<List<String>, List<Object>> parseParameter(List<ParamVO> paramList) {
        List<String> typeList = Lists.newLinkedList();
        List<Object> valueList = Lists.newLinkedList();

        paramList.stream().forEach(paramVO -> {
            typeList.add(paramVO.getType());
            valueList.add(adaptValue(paramVO.getValue()));
        });

        return Pair.of(typeList, valueList);
    }


    /**
     * Adapt value for dubbo invoker
     * @param value
     * @return
     */
    private static Object adaptValue(String value) {
        return null;
    }
}
