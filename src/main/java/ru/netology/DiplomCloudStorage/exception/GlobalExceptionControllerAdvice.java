package ru.netology.DiplomCloudStorage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.DiplomCloudStorage.model.dto.response.ExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionControllerAdvice {
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleBadCredentials(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(e.getMessage(), 400));
    }

    @ExceptionHandler(DeleteFileException.class)
    public ResponseEntity<ExceptionResponse> handleDeleteFile(DeleteFileException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionResponse(e.getMessage(), 500));
    }

    @ExceptionHandler(InputDataException.class)
    public ResponseEntity<ExceptionResponse> handleInputData(InputDataException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(e.getMessage(), 400));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionResponse> handleBadCredentials(UnauthorizedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionResponse(e.getMessage(), 401));
    }

    @ExceptionHandler(UploadFileException.class)
    public ResponseEntity<ExceptionResponse> handleBadCredentials(UploadFileException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionResponse(e.getMessage(), 500));
    }

    @ExceptionHandler(GettingFileListException.class)
    public ResponseEntity<ExceptionResponse> handleBadCredentials(GettingFileListException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionResponse(e.getMessage(), 500));
    }

}
