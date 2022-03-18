package com.example.woowabackend.Post.service;

import com.example.woowabackend.Post.StorageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
@Slf4j
public class FileSystemStorageService {
    private final Path rootLocation = Path.of("upload-dir");

    public void store(MultipartFile file, Long postId) {
        log.info("file.getContentType() :" + file.getContentType()); // image 확인 구현 필요
        if(!Files.isDirectory(rootLocation)){   // 업로드 디렉토리 확인하여 없으면 생성
            try {
                Files.createDirectories(rootLocation);
            }
            catch (IOException e) {
                throw new StorageException("Could not initialize storage", e);
            }
        }

        Path destinationFile = this.rootLocation
                .resolve(file.getOriginalFilename())
                .normalize().toAbsolutePath();
        log.info("[저장]2.저장파일명 ==> " + destinationFile.toString());

        //파일저장
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, destinationFile,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("failed to store",e);
        }
    }
}
