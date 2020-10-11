package com.myblog.common;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.List;

/**
 * 返回对象
 *
 * @author lmd
 * @version 1.0.0
 * @date 2019-09-19 09:02:33
 */
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -2492072809889519824L;

    /**
     * 定义jackson对象
     */
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * 响应状态
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    public static <T> com.myblog.common.Result<T> build(Integer code, String msg, T data) {
        return new com.myblog.common.Result<T>(code, msg, data);
    }

    public static <T> com.myblog.common.Result<T> buildOk(T data) {
        return new com.myblog.common.Result<T>(data);
    }

    public static <T> com.myblog.common.Result<T> buildOk() {
        return new com.myblog.common.Result<T>(null);
    }

    public Result() {

    }

    public static <T> com.myblog.common.Result<T> build(Integer code, String msg) {
        return new com.myblog.common.Result<T>(code, msg, null);
    }

    /**
     * build参数不全返回结果
     */
    public static <T> com.myblog.common.Result<T> buildParamFail() {
        return new com.myblog.common.Result<T>(com.myblog.common.Msg.PARAM_FAIL, com.myblog.common.Msg.TEXT_PARAM_FAIL, null);
    }

    /**
     * build参数格式错误返回结果
     */
    public static <T> com.myblog.common.Result<T> buildParamFormatFail() {
        return new com.myblog.common.Result<T>(com.myblog.common.Msg.PARAM_FAIL, com.myblog.common.Msg.TEXT_PARAM_FORMAT_FAIL, null);
    }

    /**
     * build保存成功返回结果
     */
    public static <T> com.myblog.common.Result<T> buildSaveOk(T data) {
        return new com.myblog.common.Result<T>(com.myblog.common.Msg.OK, com.myblog.common.Msg.TEXT_SAVE_OK, data);
    }

    /**
     * build保存成功返回结果
     */
    public static <T> com.myblog.common.Result<T> buildSaveOk() {
        return new com.myblog.common.Result<T>(com.myblog.common.Msg.OK, com.myblog.common.Msg.TEXT_SAVE_OK, null);
    }

    /**
     * build修改成功返回结果
     */
    public static <T> com.myblog.common.Result<T> buildUpdateOk(T data) {
        return new com.myblog.common.Result<T>(com.myblog.common.Msg.OK, com.myblog.common.Msg.TEXT_UPDATE_OK, data);
    }

    /**
     * build删除成功返回结果
     */
    public static <T> com.myblog.common.Result<T> buildDeleteOk(T data) {
        return new com.myblog.common.Result<T>(com.myblog.common.Msg.OK, com.myblog.common.Msg.TEXT_DELETE_OK, data);
    }

    /**
     * build保存失败返回结果
     */
    public static <T> com.myblog.common.Result<T> buildSaveFail() {
        return new com.myblog.common.Result<T>(com.myblog.common.Msg.SAVE_FAIL, com.myblog.common.Msg.TEXT_SAVE_FAIL, null);
    }

    /**
     * build查询成功返回结果
     */
    public static <T> com.myblog.common.Result<T> buildQueryOk(T data) {
        return new com.myblog.common.Result<T>(com.myblog.common.Msg.OK, com.myblog.common.Msg.TEXT_QUERY_OK, data);
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(T data) {
        this.code = com.myblog.common.Msg.OK;
        this.msg = com.myblog.common.Msg.TEXT_OK;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 将json结果集转化为E3Result对象
     *
     * @param jsonData json数据
     * @param clazz    E3Result中的object类型
     * @return
     */
    public static <T> com.myblog.common.Result formatToPojo(String jsonData, Class<T> clazz) {
        try {
            if (clazz == null) {
                return mapper.readValue(jsonData, com.myblog.common.Result.class);
            }
            JsonNode jsonNode = mapper.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            T obj = null;
            if (data.isObject()) {
                obj = mapper.readValue(data.traverse(), clazz);
            } else if (data.isTextual()) {
                obj = mapper.readValue(data.asText(), clazz);
            }
            return build(jsonNode.get("code").intValue(), jsonNode.get("msg").asText());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static com.myblog.common.Result format(String json) {
        try {
            return mapper.readValue(json, com.myblog.common.Result.class);
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz    集合中的类型
     * @return
     */
    public static <T> com.myblog.common.Result formatToList(String jsonData, Class<T> clazz) {
        try {
            JsonNode jsonNode = mapper.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            T obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = mapper.readValue(data.traverse(),
                        mapper.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("code").intValue(), jsonNode.get("msg").asText());
        } catch (Exception e) {
            return null;
        }
    }
}
