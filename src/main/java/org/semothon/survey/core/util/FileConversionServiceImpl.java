package org.semothon.survey.core.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.rendering.ImageType;
import org.jodconverter.LocalConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

@Service
public class FileConversionServiceImpl implements FileConversionService {

    @Override
    public byte[] convertFileToPng(MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            throw new IllegalArgumentException("파일 이름이 없습니다.");
        }
        String extension = FilenameUtils.getExtension(originalFilename).toLowerCase();

        switch (extension) {
            case "pdf":
                return convertPdfToPng(file);
            case "jpg":
            case "jpeg":
                return convertImageToPng(file);
            case "doc":
            case "docx":
                return convertDocToPng(file);
            default:
                throw new IllegalArgumentException("지원하지 않는 파일 형식: " + extension);
        }
    }

    private byte[] convertPdfToPng(MultipartFile file) throws Exception {
        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFRenderer renderer = new PDFRenderer(document);
            // 첫 페이지를 300 DPI로 렌더링
            BufferedImage image = renderer.renderImageWithDPI(0, 300, ImageType.RGB);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            return baos.toByteArray();
        }
    }

    private byte[] convertImageToPng(MultipartFile file) throws Exception {
        BufferedImage image = ImageIO.read(file.getInputStream());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return baos.toByteArray();
    }

    private byte[] convertDocToPng(MultipartFile file) throws Exception {
        // 임시 파일 생성: 원본 Office 문서와 변환된 PDF 파일
        File tempInputFile = File.createTempFile("input-", "." + FilenameUtils.getExtension(file.getOriginalFilename()));
        File tempPdfFile = File.createTempFile("converted-", ".pdf");
        System.out.println("1");
        try {
            // MultipartFile을 임시 파일로 저장
            file.transferTo(tempInputFile);
            System.out.println("2");
            // JODConverter를 사용하여 doc/docx를 PDF로 변환
            LocalConverter.builder().build()
                    .convert(tempInputFile)
                    .to(tempPdfFile)
                    .execute();
            System.out.println("3");
            // PDFBox를 사용해 PDF의 첫 페이지를 PNG로 변환
            try (PDDocument document = PDDocument.load(tempPdfFile)) {
                PDFRenderer renderer = new PDFRenderer(document);
                BufferedImage image = renderer.renderImageWithDPI(0, 300, ImageType.RGB);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "png", baos);
                return baos.toByteArray();
            }
        } finally {
            tempInputFile.delete();
            tempPdfFile.delete();
        }
    }
}
