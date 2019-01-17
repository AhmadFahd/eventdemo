package com.ahmadfahd.ServicesImplementation;

import com.ahmadfahd.Services.FileService;
import com.ahmadfahd.entity.FileEntity;
import com.ahmadfahd.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public class FileServiceImp implements FileService {


    @Autowired
    private FileRepository fileRepository;

    @Override
    public FileEntity storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            FileEntity dbFile = new FileEntity(fileName, file.getContentType(), file.getBytes());

            return fileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Override
    public FileEntity getFile(String fileId) {
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found with id " + fileId));
    }
}
