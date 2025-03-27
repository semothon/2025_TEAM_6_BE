package org.semothon.survey.core.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.semothon.survey.core.util.S3UploadOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class S3Config {

    @Value("${aws.accessKeyId}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.region}")
    private String region;

    @Bean
    public AmazonS3 amazonS3() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    @Bean
    public TransferManager transferManager(AmazonS3 amazonS3) {
        S3UploadOptions config = S3UploadOptions.defaultConfig();
        TransferManager transferManager = TransferManagerBuilder.standard()
                .withS3Client(amazonS3)
                .withMultipartUploadThreshold((long) config.partSizeInBytes()) // 조각 크기 설정
                .withExecutorFactory(() -> java.util.concurrent.Executors.newFixedThreadPool(config.maxThreads())) // 병렬 스레드 설정
                .build();
        log.debug("TransferManager 생성 완료: 조각 크기={} bytes, 최대 스레드={}", config.partSizeInBytes(), config.maxThreads());
        return transferManager;
    }
}