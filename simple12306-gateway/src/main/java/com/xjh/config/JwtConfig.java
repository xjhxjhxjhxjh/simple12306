package com.xjh.config;

import com.xjh.utils.RsaUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.PublicKey;

/**
 * @author xjhxjhxjh
 * @date 2020/2/23 17:52
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "simple12306.jwt")
@Configuration
public class JwtConfig {
    // 公钥文件地址
    private String pubKeyPath;
    // cookie名称
    private String cookieName;
    // 公钥
    private PublicKey publicKey;

    /**
     * @PostContruct：在构造方法执行之后执行该方法
     */
    @PostConstruct
    public void init() {
        try {
            // 获取公钥
            this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        } catch (Exception e) {
            throw new RuntimeException("初始化公钥失败");
        }
    }
}
