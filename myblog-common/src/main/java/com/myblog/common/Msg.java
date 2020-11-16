package com.myblog.common;

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
    String TEXT_REGISTER_OK = "注册成功";
    String TEXT_LOGIN_OK = "登录成功";
    String TEXT_LOGOUT_OK = "注销成功";
    String TEXT_SAVE_OK = "保存成功";
    String TEXT_UPDATE_OK = "修改成功";
    String TEXT_CANCEL_OK = "取消成功";
    String TEXT_DELETE_OK = "删除成功";
    String TEXT_QUERY_OK = "查询成功";
    String TEXT_LIKE_OK = "点赞成功";

    Integer CANCEL = 210;
    String TEXT_CANCEL = "商户已注销，请联系客服重新开通";

    Integer FAIL = 300;
    String TEXT_REGISTER_FAIL = "注册失败";
    String TEXT_LOGOUT_FAIL = "注销失败";
    String TEXT_OLD_PASSWORD_FAIL = "原密码不正确";
    String TEXT_EMAIL_EXIST = "邮箱已经被注册";
    String TEXT_LIKE_FAIL = "点赞失败，请重试";

    Integer PARAM_FAIL = 310;
    String TEXT_PARAM_FAIL = "参数不全";
    String TEXT_PARAM_FORMAT_FAIL = "参数值格式错误";
    String TEXT_PARAM_VALUE_FAIL = "参数取值错误";
    String TEXT_EMAIL_NULL_FAIL = "邮箱不能为空";
    String TEXT_PASSWORD_NULL_FAIL = "密码不能为空";
    String TEXT_OLD_PASSWORD_NULL_FAIL = "密码不能为空";

    Integer IMAGE_FAIL = 320;
    String TEXT_IMAGE_FAIL = "图片格式无法识别";

    Integer SAVE_FAIL = 330;
    String TEXT_SAVE_FAIL = "保存失败";

    Integer DATA_FAIL = 340;
    String TEXT_DATA_FAIL = "数据不存在";
    String TEXT_DATA_REPEAT_FAIL = "数据重复";
    String TEXT_USER_DATA_FAIL = "用户不存在,请先注册";

    Integer BLOG_FAIL = 350;
    String TEXT_BLOG_FAIL = "博客不存在";
    String TEXT_BLOG_DELETE_FAIL = "删除博客失败，请刷新页面";
    String TEXT_BLOG_TITLE_FAIL = "博客标题不能为空";
    String TEXT_BLOG_TYPE_FAIL = "请选择博客分类";
    String TEXT_BLOG_KEYWORD_FAIL = "博客关键词不能为空";
    String TEXT_BLOG_ABSTRACT_FAIL = "请填写博客摘要";

    Integer COMMENT_FAIL = 360;
    String TEXT_COMMENT_TYPE_FAIL = "评论类型未传";
    String TEXT_COMMENT_CONTENT_FAIL = "评论内容不能为空";
    String TEXT_COMMENT_FAIL = "评论已被删除";
    String TEXT_COMMENT_DELETE_FAIL = "删除评论失败，请刷新页面";
    String TEXT_COMMENT_ID_FAIL = "评论id未传";

    Integer LOGIN_FAIL = 410;
    String TEXT_LOGIN_FAIL = "用户名或密码不正确";
    String TEXT_IS_SEAL_UP_FAIL = "账号被封停";
    String TEXT_NOT_LOGIN_FAIL = "请先登录";

    Integer LOGIN_LIMIT_FAIL = 420;
    String TEXT_LIMIT_FAIL = "用户名密码多次输入错误，已限制您的登录";

    Integer TOKEN_FAIL = 430;
    String TEXT_TOKEN_INVALID_FAIL = "请重新登录";

    Integer PASSWORD_FAIL = 460;
    String TEXT_PASSWORD_INIT_FAIL = "当前账户密码为初始密码,请修改当前密码";
    String TEXT_MODIFY_PASSWORD_UNSAFE_FAIL = "密码安全度过低,请重新设置当前密码";

    Integer SYSTEM_FAIL = 500;
    String TEXT_SYSTEM_FAIL = "不受火冀望的，比比皆是。\n" +
            "你们人类不消说，我们亦是如此。\n" +
            "放眼看看这座城市吧！\n" +
            "互为同道中人的事实，显而易见。\n" +
            "因此你毋需畏惧黑暗，大啖食粮之刻已至";

    Integer REQUEST_FAIL = 501;
    String TEXT_REQUEST_FAIL = "请求方式错误，当前请求方式为[#nowReq#],实际支持请求方式为[#req#]";

    Integer SQL_FAIL = 505;
    String TEXT_SQL_FAIL = "sql语法错误";

    /**
     * 不受火之冀望的人，比比皆是。
     * 你们火主不消说，我们也是如此。
     * 看看米狄尔门口梯子上的血迹吧，
     * 互为同道中人的事实，显而易见。
     * 太阳骑士渴望余火，被米狄尔吞噬了理智，
     * 然而他与所追求之物还隔着天堑之路。
     * 即使是讨伐深渊者，也无法肉身抵抗坠渊侵蚀。
     * 因此，你毋需畏惧黑暗，大啖食粮之刻已至。
     */

}
