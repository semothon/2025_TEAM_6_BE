package org.semothon.survey.core.util;

public record S3UploadOptions(
        int partSizeInBytes,    // 조각 크기 (바이트 단위)
        int maxThreads          // 병렬 스레드 수
) {
    public static S3UploadOptions defaultConfig() {
        return new S3UploadOptions(5 * 1024 * 1024, 5); // 5MB 조각, 최대 5개 스레드
    }
}