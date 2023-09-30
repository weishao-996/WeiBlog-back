package com.ican.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * OSS枚举
 *
 * @author ican
 */
@Getter
@AllArgsConstructor
public enum OssTypeEnum  {

    /**
     * 阿里云OSS
     */
    AL("AL", "阿里云OSS"),

    /**
     * 七牛云
     */
    QNY("QNY", "七牛云OSS");

    /**
     * 编码
     */
    private final String code;

    /**
     * 名称
     */
    private final String name;

}