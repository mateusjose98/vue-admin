package com.dev.gestao.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.apache.commons.io.FilenameUtils;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

@Service
@Log4j2
public class StorageService {
    @Autowired
    private AmazonS3 s3client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public String uploadFile(MultipartFile file) {
        try {
            String originalName = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(originalName);
            String newName = originalName + "_" + UUID.randomUUID().toString() + "." + extension;
            InputStream is = file.getInputStream();
            String contentType = file.getContentType();
            return uploadFile(is, newName, contentType).toString();

        } catch (AmazonServiceException e) {
            log.error("AmazonServiceException: " + e.getErrorMessage());
            log.error("Status code: " + e.getErrorCode());
        } catch (AmazonClientException e) {
            log.error("AmazonClientException: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private URL uploadFile(InputStream is, String newName, String contentType) {
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType(contentType);
        s3client.putObject(bucketName, newName, is, meta);
        return s3client.getUrl(bucketName, newName);
    }

    public void deleteFile(final String fileName) {
        try {
            if (StringUtils.hasText(fileName)) {
                s3client.deleteObject(bucketName, fileName);
                log.info("Deleted File: ${}", fileName);
            }
        } catch (Exception e) {
            log.error("Erro ao deletar imagem no s3");
        }
    }
}
