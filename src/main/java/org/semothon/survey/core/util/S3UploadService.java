package org.semothon.survey.core.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.Upload;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.semothon.survey.core.support.error.CoreException;
import org.semothon.survey.core.support.error.GlobalErrorType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3UploadService {

    @Value("${aws.s3.bucket}")
    private String BucketName;

    private final AmazonS3 amazonS3;
    private final TransferManager transferManager;

    // 파일로 받아서 업로드 구현
    public String uploadFile(MultipartFile file) {

        // 고유한 파일 이름 생성
        try (InputStream inputStream = new ByteArrayInputStream(file.getBytes())) {
            String originalFilename = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf('.'));
            String uniqueFileName = generateUniqueFileName(file.getOriginalFilename());
            String fileName = "images/" + originalFilename + "_" + uniqueFileName +".png";  // images/ 디렉토리에 저장

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());

            return uploadToS3(inputStream, fileName, metadata);

        } catch (Exception e) {
            log.error("S3 파일 업로드 중 오류 발생! 파일 이름: {}", file.getOriginalFilename(), e);
            throw new CoreException(GlobalErrorType.S3_UPLOAD_FAILED);
        }
    }

    // Url로 받아서 업로드 구현
    public String uploadFileByUrl(String createdImageUrl) {
        try (InputStream inputStream = new URL(createdImageUrl).openStream()) {
            String fileName = "images/" + UUID.randomUUID() + ".png";

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("image/png");

            return uploadToS3(inputStream, fileName, metadata);

        } catch (Exception e) {
            log.error("S3 파일 url 업로드 중 오류 발생! URL: {}", createdImageUrl, e);
            throw new CoreException(GlobalErrorType.S3_UPLOAD_FAILED);
        }
    }

    private String uploadToS3(InputStream inputStream, String fileName, ObjectMetadata metadata) throws InterruptedException {
        Upload upload = transferManager.upload(
                new PutObjectRequest(BucketName, fileName, inputStream, metadata)
        );
        upload.waitForCompletion();

        String fileUrl = amazonS3.getUrl(BucketName, fileName).toString();
        log.info("파일 업로드 성공: {} (버킷: {}, 크기: {} bytes)", fileName, BucketName, upload.getProgress().getBytesTransferred());

        return fileUrl;
    }

    // 고유한 파일 이름 생성 메서드
    private String generateUniqueFileName(String originalFileName) {
        String uuid = UUID.randomUUID().toString();
//        String extension = "";
//        if (originalFileName != null && originalFileName.contains(".")) {
//            extension = originalFileName.substring(originalFileName.lastIndexOf("."));
//        }
        return uuid;
    }

    // byte[] 데이터를 받아 S3에 업로드하는 메서드 추가
    public String uploadFile(byte[] fileData, String originalFilename, String contentType) {
        try (InputStream inputStream = new ByteArrayInputStream(fileData)) {
            String uniqueFileName = generateUniqueFileName(originalFilename);
            String fileName = "images/" + uniqueFileName + ".png" ;

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(contentType);
            metadata.setContentLength(fileData.length);

            return uploadToS3(inputStream, fileName, metadata);
        } catch (Exception e) {
            log.error("S3 파일 업로드 중 오류 발생! 파일 이름: {}", originalFilename, e);
            throw new CoreException(GlobalErrorType.S3_UPLOAD_FAILED);
        }
    }


    @PreDestroy
    public void shutdown() {
        log.info("TransferManager 종료 시작");
        transferManager.shutdownNow();
        log.info("TransferManager 종료 완료");
    }
}