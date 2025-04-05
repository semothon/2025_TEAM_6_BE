package org.semothon.survey.core.util;

import org.springframework.web.multipart.MultipartFile;

public interface FileConversionService {
    byte[] convertFileToPng(MultipartFile file) throws Exception;
}
