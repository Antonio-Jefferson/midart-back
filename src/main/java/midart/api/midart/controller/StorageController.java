package midart.api.midart.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import midart.api.midart.service.S3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/midart/api/v1")
@RequiredArgsConstructor
@Slf4j
public class StorageController {

    private final S3Service service;

    @PostMapping("/file/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) throws Exception {
        log.error("StorageController");
        String fileName = service.uploadFileIntoS3(file);
        return ResponseEntity.ok("SUCESSO" + fileName);
    }

}
