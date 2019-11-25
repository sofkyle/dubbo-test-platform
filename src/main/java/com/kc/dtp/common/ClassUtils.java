package com.kc.dtp.common;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.kc.dtp.bean.vo.ParamVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author: Kyle
 */
@Slf4j
public class ClassUtils {

    private static final int INT_DEFAULT = 0;
    private static final double DOUBLE_DEFAULT = 0.0d;
    private static final boolean BOOLEAN_DEFAULT = false;
    private static final char CHAR_DEFAULT = '\u0000';
    private static final float FLOAT_DEFAULT = 0.0f;
    private static final byte BYTE_DEFAULT = 0;
    private static final long LONG_DEFAULT = 0L;
    private static final short SHORT_DEFAULT = 0;
    private static final int[] INT_ARRAY_DEFAULT = null;
    private static final double[] DOUBLE_ARRAY_DEFAULT = null;
    private static final boolean[] BOOLEAN_ARRAY_DEFAULT = null;
    private static final char[] CHAT_ARRAY_DEFAULT = null;
    private static final float[] FLOAT_ARRAY_DEFAULT = null;
    private static final byte[] BYTE_ARRAY_DEFAULT = null;
    private static final long[] LONG_ARRAY_DEFAULT = null;
    private static final short[] SHORT_ARRAY_DEFAULT = null;

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
            valueList.add(adaptValue(paramVO.getType(), paramVO.getValue()));
        });

        return Pair.of(typeList, valueList);
    }

    /**
     * Adapt value for dubbo invoker
     * @param type
     * @param value
     * @return
     */
    private static Object adaptValue(String type, String value) {
        if (Objects.equals(type, "int")) {
            
            return StringUtils.isBlank(value) ? INT_DEFAULT : Integer.parseInt(value);
        } else if(Objects.equals(type, "int[]")) {
            
            return StringUtils.isBlank(value) ? INT_ARRAY_DEFAULT : JSON.parseObject(value, new TypeReference<int[]>() {}.getType());
        } else if (Objects.equals(type, "double")) {
            
            return StringUtils.isBlank(value) ? DOUBLE_DEFAULT : Double.parseDouble(value);
        }  else if (Objects.equals(type, "double[]")) {
            
            return StringUtils.isBlank(value) ? DOUBLE_ARRAY_DEFAULT : JSON.parseObject(value, new TypeReference<double[]>() {}.getType());
        } else if (Objects.equals(type, "short")) {
            
            return StringUtils.isBlank(value) ? SHORT_DEFAULT : Short.parseShort(value);
        } else if (Objects.equals(type, "short[]")) {
            
            return StringUtils.isBlank(value) ? SHORT_ARRAY_DEFAULT : JSON.parseObject(value, new TypeReference<short[]>() {}.getType());
        } else if (Objects.equals(type, "float")) {
            
            return StringUtils.isBlank(value) ? FLOAT_DEFAULT : Float.parseFloat(value);
        } else if (Objects.equals(type, "float[]")) {
            
            return StringUtils.isBlank(value) ? FLOAT_ARRAY_DEFAULT : JSON.parseObject(value, new TypeReference<float[]>() {}.getType());
        } else if (Objects.equals(type, "long")) {
            
            return StringUtils.isBlank(value) ? LONG_DEFAULT : Long.parseLong(value);
        } else if (Objects.equals(type, "long[]")) {
            
            return StringUtils.isBlank(value) ? LONG_ARRAY_DEFAULT : JSON.parseObject(value, new TypeReference<long[]>() {}.getType());
        } else if (Objects.equals(type, "byte")) {
            
            return StringUtils.isBlank(value) ? BYTE_DEFAULT : Byte.parseByte(value);
        } else if (Objects.equals(type, "byte[]")) {
            
            return StringUtils.isBlank(value) ? BYTE_ARRAY_DEFAULT : JSON.parseObject(value, new TypeReference<byte[]>() {}.getType());
        } else if (Objects.equals(type, "boolean")) {
            
            return StringUtils.isBlank(value) ? BOOLEAN_DEFAULT : Boolean.parseBoolean(value);
        } else if (Objects.equals(type, "boolean[]")) {
            
            return StringUtils.isBlank(value) ? BOOLEAN_ARRAY_DEFAULT : JSON.parseObject(value, new TypeReference<boolean[]>() {}.getType());
        } else if (Objects.equals(type, "char")) {
            
            return StringUtils.isBlank(value) ? CHAR_DEFAULT : value.charAt(0);
        } else if (Objects.equals(type, "char[]")) {
            
            return StringUtils.isBlank(value) ? CHAT_ARRAY_DEFAULT : JSON.parseObject(value, new TypeReference<char[]>() {}.getType());
        } else if (Objects.equals(type, "java.lang.String")
                || Objects.equals(type, "String")
                || Objects.equals(type, "string")) {
            
            return StringUtils.isBlank(value) ? null : value;
        } else if (Objects.equals(type, "java.lang.String[]")
                || Objects.equals(type, "String[]")
                || Objects.equals(type, "string[]")) {
            
            return StringUtils.isBlank(value) ? null : JSON.parseObject(value, new TypeReference<String[]>() {}.getType());
        } else if (Objects.equals(type, "java.lang.Integer")
                || Objects.equals(type, "Integer")
                || Objects.equals(type, "integer")) {
            
            return StringUtils.isBlank(value) ? null : Integer.valueOf(value);
        } else if (Objects.equals(type, "java.lang.Integer[]")
                || Objects.equals(type, "Integer[]")
                || Objects.equals(type, "integer[]")) {
            
            return StringUtils.isBlank(value) ? null : JSON.parseObject(value, new TypeReference<Integer[]>() {}.getType());
        } else if (Objects.equals(type, "java.lang.Double")
                || Objects.equals(type, "Double")) {
            
            return StringUtils.isBlank(value) ? null : Double.valueOf(value);
        } else if (Objects.equals(type, "java.lang.Double[]")
                || Objects.equals(type, "Double[]")) {
            
            return StringUtils.isBlank(value) ? null : JSON.parseObject(value, new TypeReference<Double[]>() {}.getType());
        } else if (Objects.equals(type, "java.lang.Short")
                || Objects.equals(type, "Short")) {
            
            return StringUtils.isBlank(value) ? null : Short.valueOf(value);
        } else if (Objects.equals(type, "java.lang.Short[]")
                || Objects.equals(type, "Short[]")) {
            
            return StringUtils.isBlank(value) ? null : JSON.parseObject(value, new TypeReference<Short[]>() {}.getType());
        } else if (Objects.equals(type, "java.lang.Long")
                || Objects.equals(type, "Long")) {
            
            return StringUtils.isBlank(value) ? null : Long.valueOf(value);
        } else if(Objects.equals(type, "java.lang.Long[]")
                || Objects.equals(type, "Long[]")) {
            
            return StringUtils.isBlank(value) ? null : JSON.parseObject(value, new TypeReference<Long[]>() {}.getType());
        } else if (Objects.equals(type, "java.lang.Float")
                || Objects.equals(type, "Float")) {
            
            return StringUtils.isBlank(value) ? null : Float.valueOf(value);
        } else if (Objects.equals(type, "java.lang.Float[]")
                || Objects.equals(type, "Float[]")) {
            
            return StringUtils.isBlank(value) ? null : JSON.parseObject(value, new TypeReference<Float[]>() {}.getType());
        } else if (Objects.equals(type, "java.lang.Byte")
                || Objects.equals(type, "Byte")) {
            
            return StringUtils.isBlank(value) ? null : Byte.valueOf(value);
        } else if (Objects.equals(type, "java.lang.Byte[]")
                || Objects.equals(type, "Byte[]")) {
            
            return StringUtils.isBlank(value) ? null : JSON.parseObject(value, new TypeReference<Byte[]>() {}.getType());
        } else if (Objects.equals(type, "java.lang.Boolean")
                || Objects.equals(type, "Boolean")) {
            
            return StringUtils.isBlank(value) ? null : Boolean.valueOf(value);
        } else if (Objects.equals(type, "java.lang.Boolean[]")
                || Objects.equals(type, "Boolean[]")) {
            
            return StringUtils.isBlank(value) ? null : JSON.parseObject(value, new TypeReference<Boolean[]>() {}.getType());
        } else {
            if (type.endsWith("[]")) {
                List<?> list = null;
                if (!StringUtils.isBlank(value)) {
                    list = JSON.parseObject(value, new TypeReference<List<?>>() {}.getType());
                }
                
                return list == null ? null : list.toArray();
            } else {
                try {
                    Class<?> clazz = Class.forName(type);
                    
                    return StringUtils.isBlank(value) ? null : JSON.parseObject(value, clazz);
                } catch (ClassNotFoundException e) {
                    //不是jdk或者lib下的类，使用通用map格式反序列化值
                    Object obj = null;
                    if (!StringUtils.isBlank(value)) {
                        //使用通用map格式反序列化值
                        obj = JSON.parseObject(value, new TypeReference<HashMap<String, Object>>() {}.getType());
                        if (obj == null) {
                            //枚举类型的类走字符串序列化
                            obj = JSON.parseObject(value, String.class);
                        }
                    }
                    return obj;
                }
            }
        }
    }
}
