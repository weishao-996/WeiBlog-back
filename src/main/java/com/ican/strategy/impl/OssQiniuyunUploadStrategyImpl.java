package com.ican.strategy.impl;


import com.ican.config.properties.OssQiniuyunProperties;
import com.ican.constant.OssConstant;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * 七牛云oss上传策略
 *
 * @author WEI
 */
@Slf4j
@Service("ossQiniuyunUploadStrategyImpl")
public class OssQiniuyunUploadStrategyImpl extends AbstractUploadStrategyImpl{

    @Autowired
    private OssQiniuyunProperties ossQiniuyunProperties;
    @Override
    public Boolean exists(String filePath) throws QiniuException {
        // 构建七牛云授权对象
        Auth auth = Auth.create(ossQiniuyunProperties.getAccessKey(), ossQiniuyunProperties.getSecretKey());
        // 创建配置对象
        Configuration cfg = new Configuration();
        // 创建BucketManager对象
        BucketManager bucketManager = new BucketManager(auth,cfg);
        try {
            // 调用stat方法检查文件是否存在
            bucketManager.stat(ossQiniuyunProperties.getBucket(), filePath);
            // 如果上述操作没有抛出异常，则文件存在
            return true;
        } catch (QiniuException e) {
            // 如果文件不存在，会抛出612异常
            if (e.response != null && e.response.statusCode == OssConstant.QNY_NOT_EXIST) {
                return false;
            } else {
                // 其他异常情况，抛出异常
                throw e;
            }
        }
    }

    @Override
    public void upload(String path, String fileName, InputStream inputStream) throws IOException {
        // 构建七牛云授权对象
        Auth auth = Auth.create(ossQiniuyunProperties.getAccessKey(), ossQiniuyunProperties.getSecretKey());
        // 创建配置对象
        Configuration cfg = new Configuration();
        // 创建上传管理器
        UploadManager uploadManager = new UploadManager(cfg);

        try {
            // 生成上传凭证
            String uploadToken = auth.uploadToken(ossQiniuyunProperties.getBucket());
            // 上传文件
            Response response = uploadManager.put(inputStream, path+fileName, uploadToken, null, null);
            // 处理上传结果
            if (response.isOK()) {
                System.out.println("文件上传成功");
            } else {
                System.err.println("文件上传失败：" + response.bodyString());
            }
        } catch (QiniuException e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    @Override
    public String getFileAccessUrl(String filePath) {
        return ossQiniuyunProperties.getDomainName() + filePath;
    }


}
