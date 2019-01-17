package com.ahmadfahd.Services;

import com.ahmadfahd.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    FileEntity storeFile(MultipartFile file);
    FileEntity getFile(String fileId);
}
