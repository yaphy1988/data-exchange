package com.ai.bdex.dataexchange.apigateway.util.cfca.utils;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Description: json数据格式 和 对象转换工具<br/>
 */
public class JsonUtils {

    private static ObjectMapper jsonParser = new ObjectMapper();

    /**
     * 获取jackson的json转换器
     * 
     * @return jackson的json转换器
     */
    public static ObjectMapper getParser() {
        return jsonParser;
    }

    /**
     * json字符串 To 对象转换工具，这里不对json做非空处理，请在传入前处理，出错时会抛出运行时异常
     * 
     * @param json
     *            json格式字符串
     * @param clazz
     *            要转换的对象类型的Class
     * @return 转换成功的对象
     * @throws Exception
     */
    public static <T> T json2Obj(String json, Class<T> clazz) throws IOException, JsonParseException, JsonMappingException {
        return jsonParser.readValue(json, clazz);
    }

    /**
     * 对象 To json字符串 转换工具，这里不对对象做非空处理，请在传入前处理，出错时会抛出运行时异常
     * 
     * @param obj
     *            要转换的对象
     * @return 转换成功的json字符串
     * @throws Exception
     */
    public static String obj2Json(Object obj) throws Exception {
        return jsonParser.writeValueAsString(obj);
    }

    /**
     * json字符串 To List<Obj>转换工具，这里不对json做非空处理，请在传入前处理，出错时会抛出运行时异常
     * 
     * @param json
     *            要转换的字符串，格式如[{"key":"value"},{"key":"value"}]
     * @param clazz
     *            要转换的对象类型的Class
     * @return List<Obj> obj为传入对象类型的实例
     * @throws Exception
     */
    public static <T> List<T> json2List(String json, Class<T> clazz) throws Exception {
        JavaType javaType = jsonParser.getTypeFactory().constructParametrizedType(List.class, List.class, clazz);
        return jsonParser.readValue(json, javaType);
    }
}
