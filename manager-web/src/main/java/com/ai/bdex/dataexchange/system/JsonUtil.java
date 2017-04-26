/*package com.ai.bdex.dataexchange.system;

import java.text.SimpleDateFormat;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
*//**
 *JsonUtil
 *//*
public class JsonUtil {
    private static ObjectMapper mapper = new ObjectMapper();
    
    static{
        //设置日期（Date、Timestamp）的格式
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        //序列化设置,序列化成json字符串的时候不包含null值,针对Map类
        mapper.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES, false);
        //序列化设置,序列化成json字符串的时候不包含null值,针对POJO类
        mapper.setSerializationConfig(mapper.getSerializationConfig().withSerializationInclusion(Inclusion.NON_NULL));
        //反序列化设置,忽略JSON字符串中存在而Java对象实际没有的属性，否则json字符串中的属性如果bean里面没有的话会报错
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        //允许反序列化不带双引号的key
        mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        //序列化的结果不带双引号
       //mapper.configure(org.codehaus.jackson.JsonGenerator.Feature.QUOTE_FIELD_NAMES, true);
    }
    
    private JsonUtil(){}

    public static ObjectMapper getObjectMapper(){
        return mapper;
    }
    
    *//**
     * 将json字符串转换成JsonNode
     * @param json
     * @return
     *//*
    public static JsonNode toJsonNode(String json){
        try {
            return mapper.readTree(json);
        } catch (Exception e) {
            throw new RuntimeException("JsonUtil.toJsonNode发生错误", e);
        }
    }

    *//**
     * 将Object转化成JSON String
     * @param obj
     * @return
     *//*
    public static String toJSONString(Object obj){
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("JsonUtil.toJSONString发生错误", e);
        }
    }
    
    *//**
     * 将Object转化成JSON byte[]
     * @param obj
     * @return
     *//*
    public static byte[] toJSONBytes(Object obj){
        try {
            return mapper.writeValueAsBytes(obj);
        } catch (Exception e) {
            throw new RuntimeException("JsonUtil.toJSONBytes发生错误", e);
        }
    }

    *//**
     * 将json字符串转换到Object
     * @param json json字符串
     * @param clazz 返回类型
     * @return 转换结果
     *//*
    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;  
            return mapper.readValue(json, clazz);
            
        } catch (Exception e) {
            throw new RuntimeException("JsonUtil.toObject发生错误,json串"+json, e);
        }
    }
    
    *//**
     * 将byte[]字符串转换到Object
     * @param json byte[]
     * @param clazz 返回类型
     * @return 转换结果
     *//*
    public static <T> T toObject(byte[] json, Class<T> clazz){
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("JsonUtil.toObject发生错误", e);
        }
    }
    
    *//**
     * 将JsonNode转换到Object
     * @param jsonNode jsonNode
     * @param clazz 返回类型
     * @return 转换结果
     *//*
    public static <T> T toObject(JsonNode jsonNode, Class<T> clazz){
        try {
            return mapper.readValue(jsonNode, clazz);
        } catch (Exception e) {
            throw new RuntimeException("JsonUtil.toObject发生错误", e);
        }
    }

    *//**
     * 将json字符串转换到Object List
     * @param json json字符串
     * @param clazz 返回类型
     * @return 转换结果
     *//*
    public static <T> List<T> toObjectList(String json, Class<T> clazz){
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            throw new RuntimeException("JsonUtil.toObjectList发生错误", e);
        }
    }
    
    *//**
     * 将byte[]字符串转换到Object List
     * @param json byte[]
     * @param clazz 返回类型
     * @return 转换结果
     *//*
    public static <T> List<T> toObjectList(byte[] json, Class<T> clazz){
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            throw new RuntimeException("JsonUtil.toObjectList发生错误", e);
        }
    }
    
    *//**
     * 将JsonNode转换到Object List
     * @param jsonNode jsonNode
     * @param clazz 返回类型
     * @return 转换结果
     *//*
    public static <T> List<T> toObjectList(JsonNode jsonNode, Class<T> clazz){
        try {
            return mapper.readValue(jsonNode, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            throw new RuntimeException("JsonUtil.toObjectList发生错误", e);
        }
    }

    *//**
     * 将json字符串转换到Object Array
     * @param json json字符串
     * @param clazz 返回类型
     * @return 转换结果
     *//*
    public static <T> T[] toObjectArray(String json, Class<T> clazz){
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructArrayType(clazz));
        } catch (Exception e) {
            throw new RuntimeException("JsonUtil.toObjectArray发生错误", e);
        }
    }
    
    *//**
     * 将byte[]字符串转换到Object Array
     * @param json byte[]
     * @param clazz 返回类型
     * @return Object
     *//*
    public static <T> T[] toObjectArray(byte[] json, Class<T> clazz){
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructArrayType(clazz));
        } catch (Exception e) {
            throw new RuntimeException("JsonUtil.toObjectArray发生错误", e);
        }
    }
    
    *//**
     * 将jJsonNode转换到Object Array
     * @param json jsonNode
     * @param clazz 返回类型
     * @return 转换结果
     *//*
    public static <T> T[] toObjectArray(JsonNode jsonNode, Class<T> clazz){
        try {
            return mapper.readValue(jsonNode, mapper.getTypeFactory().constructArrayType(clazz));
        } catch (Exception e) {
            throw new RuntimeException("JsonUtil.toObjectArray发生错误", e);
        }
    }
    
    *//**
     * JSON字符串特殊字符处理，比如：“\A1;1300”
     * 
     * @param s
     * @return String
     *//*
    public static String string2Json(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
            case '\\':
                sb.append("\\\\");
                break;
            case '/':
                sb.append("\\/");
                break;
            case '\b':
                sb.append("\\b");
                break;
            case '\f':
                sb.append("\\f");
                break;
            case '\n':
                sb.append("\\n");
                break;
            case '\r':
                sb.append("\\r");
                break;
            case '\t':
                sb.append("\\t");
                break;
            default:
                sb.append(c);
            }
        }
        return sb.toString();

    }
}
*/