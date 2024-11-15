package ru.netology.DiplomCloudStorage.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.DiplomCloudStorage.exception.InputDataException;
import ru.netology.DiplomCloudStorage.model.dto.request.FileDataApply;
import ru.netology.DiplomCloudStorage.model.entity.StorageFile;
import ru.netology.DiplomCloudStorage.model.entity.User;
import ru.netology.DiplomCloudStorage.repository.AuthRepository;
import ru.netology.DiplomCloudStorage.repository.FileRepository;
import ru.netology.DiplomCloudStorage.repository.LoginRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class FileService {
    private final FileRepository fileRepository;
    private final AuthRepository authRepository;
    private final LoginRepository loginRepository;


    @Transactional
    public void uploadFile(String token, String fileName, MultipartFile file) {
        final  User user = getUserFromToken(token);
        try {
            fileRepository.save(new StorageFile(fileName, LocalDateTime.now(), file.getSize(), file.getBytes(), user));
            log.info("Successfully uploaded file. User: {}", user.getUsername());
        } catch (IOException e) {
            log.error("Upload file: Input data exception");
            throw new InputDataException("Upload file: Input data exception");
        }
    }

    @Transactional
    public void deleteFile(String token, String filename) {
        final  User user = getUserFromToken(token);
        fileRepository.deleteByUserAndFilename(user, filename);
        StorageFile storageFile = fileRepository.findByUserAndFilename(user, filename);
        if (storageFile != null) {
            log.error("Delete file: Input data exception");
            throw new InputDataException("Input data exception");
        }
        log.info("Successfully deleted file. User: {}", user.getUsername());
    }

    @Transactional
    public byte[] downloadFile(String token, String filename) {
        final  User user = getUserFromToken(token);
        StorageFile storageFile = fileRepository.findByUserAndFilename(user, filename);
        if (storageFile == null) {
            log.error("Download file: Input data exception");
            throw new InputDataException("Download file: Input data exception");
        }
        log.info("Downloaded file. User: {}", user.getUsername());
        return storageFile.getFileContent();
    }

    @Transactional
    public void updateFilename(String token, String fileName, FileDataApply fileDataApply) {
        final  User user = getUserFromToken(token);
        fileRepository.updateFilenameByUser(user, fileName, fileDataApply.getFilename());
        StorageFile storageFile = fileRepository.findByUserAndFilename(user, fileName);
        if (storageFile != null) {
            log.error("ERROR: Input data exception");
            throw new InputDataException("ERROR: Input data exception");
        }
        log.info("Successfully updated file name. User: {}", user.getUsername());
    }

    @Transactional
    public List<StorageFile> getAllFiles(String token, Integer limit) {
        final  User user = getUserFromToken(token);
        log.info("Successfully fetched all files. User: {}", user.getUsername());
        return fileRepository.findAllByUser(user);
    }

    public User getUserFromToken(String token) {
        if (token.startsWith("Bearer ")) {
            String authTokenBearer = token.split(" ")[1];
            String userName = authRepository.getUsernameByToken(authTokenBearer);
            return loginRepository.findByUsername(userName);
        }
        return null;
    }
}
