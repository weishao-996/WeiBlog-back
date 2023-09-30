package com.ican.utils;

import com.ican.enums.OssTypeEnum;

public class EnumUtils {

    public static OssTypeEnum getByCode(String code) {
        for (OssTypeEnum ossTypeEnum : OssTypeEnum.values()) {
            if (ossTypeEnum.getCode().equals(code)) {
                return ossTypeEnum;
            }
        }
        return null; // Return null if no match is found
    }
}
