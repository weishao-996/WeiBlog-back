package com.ican.strategy.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.ican.config.properties.OssAlibabaProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * 阿里oss上传策略
 *
 * @author ican
 */
@Slf4j
@Service("ossAlibabaUploadStrategyImpl")
public class OssAlibabaUploadStrategyImpl extends AbstractUploadStrategyImpl {

    @Autowired
    private OssAlibabaProperties ossAlibabaProperties;

    @Override
    public Boolean exists(String filePath)   {
        return getOssClient().doesObjectExist(ossAlibabaProperties.getBucketName(), filePath);
    }

    @Override
    public void upload(String path, String fileName, InputStream inputStream) {
        OSS ossClient = null;
        try {
            ossClient = getOssClient();
            // 调用oss方法上传
            ossClient.putObject(ossAlibabaProperties.getBucketName(), path + fileName, inputStream);
        } catch (OSSException oe) {
            log.error("Error Message:" + oe.getErrorMessage());
            log.error("Error Code:" + oe.getErrorCode());
            log.info("Request ID:" + oe.getRequestId());
            log.info("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            log.error("Caught an ClientException, Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }


    @Override
    public String getFileAccessUrl(String filePath) {
        return ossAlibabaProperties.getUrl() + filePath;
    }

    /**
     * 获取ossClient
     *
     * @return {@link OSS} ossClient
     */
    private OSS getOssClient() {
        return new OSSClientBuilder().build(ossAlibabaProperties.getEndpoint(), ossAlibabaProperties.getAccessKeyId(), ossAlibabaProperties.getAccessKeySecret());
    }
}
