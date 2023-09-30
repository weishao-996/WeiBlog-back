package com.ican.config.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 七牛云oss配置属性
 *
 * @author ican
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "upload.oss.qiniuyun")
public class OssQiniuyunProperties {
    /**
     * oss域名
     */
    private String domainName;
    /**
     * bucket名称
     */
    private String bucket;

    /**
     * 访问密钥id
     */
    private String accessKey;

    /**
     * 访问密钥密码
     */
    private String secretKey;



}
