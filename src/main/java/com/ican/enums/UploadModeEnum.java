package com.ican.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 上传模式枚举
 *
 * @author ican
 */
@Getter
@AllArgsConstructor
public enum UploadModeEnum {

    /**
     * 本地
     */
    LOCAL("local", "localUploadStrategyImpl",null),
    /**
     * cos
     */
    COS("cos", "cosUploadStrategyImpl",null),

    /**
     * 阿里巴巴oss
     */
    OSS_ALIBABA("oss", "ossAlibabaUploadStrategyImpl","alibaba"),
    /**
     * 七牛云oss
     */
    OSS_QNIUYUN("oss", "ossQiniuyunUploadStrategyImpl","qiniuyun")

    ;



    /**
     * 模式
     */
    private final String mode;

    /**
     * 策略
     */
    private final String strategy;
    /**
     * OSS所属公司
     */
    private final String company;

    /**
     * 获取策略
     *
     * @param mode 模式
     * @return 搜索策略
     */
    public static String getStrategy(String mode,String company) {
        for (UploadModeEnum value : UploadModeEnum.values()) {
            if (value.getMode().equals(mode)) {
                if (!mode.equals("oss")){
                    return value.getStrategy();
                }else {
                    if (value.getCompany().equals(company)){
                        return value.getStrategy();
                    }
                }

            }
        }
        return null;
    }
}