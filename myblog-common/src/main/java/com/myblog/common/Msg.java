package com.myblog.common;

import java.util.Arrays;
import java.util.List;

/**
 * 常量定义
 *
 * @author lmd
 * @version 1.0.0
 * @date 2019-09-19 09:02:33
 */
public interface Msg {
    Integer PAY_GOLD_OK = 210;

    Integer OK = 200;
    String TEXT_OK = "请求成功";
    String TEXT_LOGIN_OK = "登录成功";
    String TEXT_LOGOUT_OK = "注销成功";
    String TEXT_SAVE_OK = "保存成功";
    String TEXT_UPDATE_OK = "修改成功";
    String TEXT_CANCEL_OK = "取消成功";
    String TEXT_DELETE_OK = "删除成功";
    String TEXT_QUERY_OK = "查询成功";

    Integer CANCEL = 210;
    String TEXT_CANCEL = "商户已注销，请联系客服重新开通";

    Integer FAIL = 300;
    String TEXT_LOGOUT_FAIL = "注销失败";
    String TEXT_OLD_PASSWORD_FAIL = "原密码不正确";

    Integer PARAM_FAIL = 310;
    String TEXT_PARAM_FAIL = "参数不全";
    String TEXT_PARAM_FORMAT_FAIL = "参数值格式错误";
    String TEXT_PARAM_VALUE_FAIL = "参数取值错误";

    Integer IMAGE_FAIL = 320;
    String TEXT_IMAGE_FAIL = "图片格式无法识别";

    Integer SAVE_FAIL = 330;
    String TEXT_SAVE_FAIL = "保存失败";

    Integer DATA_FAIL = 340;
    String TEXT_DATA_FAIL = "数据不存在";
    String TEXT_DATA_REPEAT_FAIL = "数据重复";
    String TEXT_USER_DATA_FAIL = "用户不存在";

    Integer LOGIN_FAIL = 410;
    String TEXT_LOGIN_FAIL = "用户名或密码不正确";
    String TEXT_IS_SEAL_UP_FAIL = "账号被封停";
    String TEXT_NOT_LOGIN_FAIL = "请先登录";

    Integer LOGIN_LIMIT_FAIL = 420;
    String TEXT_LIMIT_FAIL = "用户名密码多次输入错误，已限制您的登录";

    Integer TOKEN_FAIL = 430;
    String TEXT_TOKEN_INVALID_FAIL = "请重新登录";

    Integer AUTHORITY_FAIL = 440;
    String TEXT_AUTHORITY_FAIL = "权限不足";


    Integer PASSWORD_FAIL = 460;
    String TEXT_PASSWORD_INIT_FAIL = "当前账户密码为初始密码,请修改当前密码";
    String TEXT_MODIFY_PASSWORD_UNSAFE_FAIL = "密码安全度过低,请重新设置当前密码";

    Integer FILE_FAIL = 470;
    String TEXT_EXCEL_DOWNLOAD_FAIL = "excel下载异常";
    String TEXT_EXCEL_UPLOAD_FAIL = "excel上传异常";
    String TEXT_EXCEL_ANALYSIS_FAIL = "excel解析异常";

    Integer SYSTEM_FAIL = 500;
    String TEXT_SYSTEM_FAIL = "不受火冀望的，比比皆是。因此，你毋须畏惧黑暗,大啖食粮之刻已到";

    Integer REQUEST_FAIL = 501;
    String TEXT_REQUEST_FAIL = "请求方式错误，当前请求方式为[#nowReq#],实际支持请求方式为[#req#]";

}
