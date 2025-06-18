/**
 * Created By Arun Singh
 * Date:18-06-2025
 * Time:10:42
 * Project Name:E-Commerce
 */

package com.nontech.ecom.serviceImpl;

import com.nontech.ecom.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile image) throws IOException {
        String originalFileName = image.getOriginalFilename();
        String randomId = UUID.randomUUID().toString();
        String fileName = randomId.concat(originalFileName.substring(originalFileName.lastIndexOf(".")));

        String filePath = path + File.separator + fileName;
        File folder = new File(path);

        if(!folder.exists())
            folder.mkdir();

        Files.copy(image.getInputStream(), Paths.get(filePath));

        return fileName;
    }
}
