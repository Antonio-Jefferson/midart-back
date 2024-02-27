package midart.api.midart.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3Service {

    @Value("${aws.s3.audio.bucket}")
    private String bucketName;

    private final AmazonS3 amazonS3;

    public String uploadFileIntoS3(MultipartFile file) {
        try {
            amazonS3.putObject(new PutObjectRequest(bucketName, file.getOriginalFilename(), file.getInputStream(), new ObjectMetadata()));
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file to S3", e);
        }
        return file.getOriginalFilename();
    }
}
