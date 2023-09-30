package com.ican.model.vo;

import lombok.Data;

/**
 * QQ用户信息
 *
 * @author ican
 * @date 2023/04/06 23:32
 **/
@Data
public class QqUserInfoVO {

    /**
     * 用户开放id
     */
    private String openId;

    /**
     * QQ头像
     */
    private String figureurl_qq_1;

    /**
     * 昵称
     */
    private String nickname;
}