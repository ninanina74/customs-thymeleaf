package com.example.customs.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class StorageService {
  private final Path root;
  public StorageService(@Value("${app.upload.dir}") String dir) throws IOException {
    this.root = Path.of(dir);
    Files.createDirectories(root);
  }
  public StoredFile store(MultipartFile file) throws IOException {
    String original = StringUtils.cleanPath(file.getOriginalFilename());
    String key = UUID.randomUUID() + "_" + original;
    Path target = root.resolve(key);
    Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
    return new StoredFile(original, file.getContentType(), file.getSize(), target.toString());
  }
  public record StoredFile(String fileName, String contentType, long size, String path) {}
}
