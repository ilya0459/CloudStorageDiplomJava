package ru.netology.DiplomCloudStorage.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.DiplomCloudStorage.model.dto.request.FileDataApply;
import ru.netology.DiplomCloudStorage.model.dto.response.FileJsonName;
import ru.netology.DiplomCloudStorage.model.entity.StorageFile;
import ru.netology.DiplomCloudStorage.model.entity.User;
import ru.netology.DiplomCloudStorage.service.AuthService;
import ru.netology.DiplomCloudStorage.service.FileService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CloudControllers {
    private final AuthService authService;
    private final FileService fileService;

    @PostMapping(value = "/file")
    public ResponseEntity<?> uploadFile(
            @RequestHeader("auth-token") String authToken,
            @RequestParam("filename") String filename,
            @RequestParam("file") MultipartFile file) {
        //User user = authService.getUserFromToken(authToken);
        fileService.uploadFile(authToken, filename, file);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @DeleteMapping("/file")
    public ResponseEntity<?> deleteFile(@RequestHeader("auth-token") String authToken, @RequestParam("filename") String filename) {
        //User user = authService.getUserFromToken(authToken);
        fileService.deleteFile(authToken, filename);
        return ResponseEntity.ok("File deleted successfully");
    }

    @GetMapping("/file")
    public ResponseEntity<byte[]> downloadFile(@RequestHeader("auth-token") String authToken, @RequestParam("filename") String filename) {
        //User user = authService.getUserFromToken(authToken);
        byte[] file = fileService.downloadFile(authToken, filename);
        return ResponseEntity.ok(file);
    }

    @PutMapping("/file")
    public ResponseEntity<?> updateFilename(@RequestHeader("auth-token") String authToken,
                                            @RequestParam("filename") String filename,
                                            @RequestBody FileDataApply fileDataApply) {
        //User user = authService.getUserFromToken(authToken);
        fileService.updateFilename(authToken, filename, fileDataApply);
        return ResponseEntity.ok("File name updated successfully");
    }

    @GetMapping("/list")
    public ResponseEntity<List<FileJsonName>> getAllFiles(@RequestHeader("auth-token") String authToken, @RequestParam("limit") Integer limit) {
        //User user = authService.getUserFromToken(authToken);
        List<StorageFile> storageFiles = fileService.getAllFiles(authToken, limit);
        List<FileJsonName> fileJsonNames = storageFiles.stream()
                .map(storageFile -> new FileJsonName(storageFile.getFilename(), storageFile.getSize()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(fileJsonNames);
    }
}