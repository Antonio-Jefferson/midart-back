package midart.api.midart.service;

import lombok.RequiredArgsConstructor;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3Service {

    @Value("${aws.s3.audio.bucket}")
    private String bucketName;

    private final S3Client s3Client;

    public String uploadFileIntoS3(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is null or empty");
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        byte[] fileBytes = file.getBytes();

        try {
            PutObjectRequest objectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();

            s3Client.putObject(objectRequest, RequestBody.fromBytes(fileBytes));

            return fileName;
        } catch (Exception e) {
            throw new IOException("Error uploading file to S3", e);
        }
    }


}
