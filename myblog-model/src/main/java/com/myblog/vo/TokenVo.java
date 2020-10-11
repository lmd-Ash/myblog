package com.myblog.vo;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author lmd
 * @className TokenVo
 * @description
 * @date 2020/3/17
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
public class TokenVo implements Serializable {
    /**
     * 请求方法所需要的token
     */
    private String token;
    /**
     * 返回的结果
     */
    private Boolean ifSuccess;
}
